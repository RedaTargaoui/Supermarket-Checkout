/**
 * Create graphical user interface(GUI)
 * @author Reda TARGAOUI
 */
package View;

import Controller.ShoppingCart;
import Model.DataGetter;
import Model.Product;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {
    // Attributes :
    private ShoppingCart shoppingCart;
    private Label totalLabel;
    private ObservableList<String> observableSelectedProducts;

    /**
     * Create GUI
     * @param primaryStage primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Supermarket Checkout App");

        // Create product table :
        TableView<Product> productTable = createProductTable();

        // Create right side content :
        VBox rightSide = createRightSide();

        // Create main layout :
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(productTable);
        borderPane.setRight(rightSide);

        Scene scene = new Scene(borderPane, 600, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Create product table
     * @return TableView
     */
    private TableView<Product> createProductTable() {
        // Create table view :
        TableView<Product> productTable = new TableView<>();

        // Set columns :
        // ID column :
        TableColumn<Product, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        // Name column :
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        // Price column :
        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        // Add columns to the table :
        productTable.getColumns().addAll(idColumn, nameColumn, priceColumn);

        // Get data from database :
        DataGetter dataGetter = new DataGetter("jdbc:mysql://localhost:3306/myDB", "root", "root");
        ArrayList<Product> products = dataGetter.getProducts();
        dataGetter.closeConnection();


        // Add data to table :
        productTable.setItems(FXCollections.observableArrayList(products));

        // Initialize shoppingCart Object :
        this.shoppingCart = new ShoppingCart();

        // Handle product selection :
        productTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Add product to shopping cart and update total price :
                shoppingCart.addProduct(newSelection.getName(), newSelection.getPrice());
                updateTotalPrice();

                // Manually update the ListView with the latest products
                observableSelectedProducts.setAll(shoppingCart.getProducts());
            }
        });

        return productTable;
    }

    /**
     * Create selected products part and total price label
     * @return VBox
     */
    private VBox createRightSide() {
        // Create selected products part :
        VBox selectedProductsVBox = new VBox(10);
        selectedProductsVBox.setPadding(new Insets(10, 10, 10, 10));

        // Create Selected Products label :
        Label selectedProductsLabel = new Label("Selected Products");
        // Use an observable list to dynamically update the ListView :
        this.observableSelectedProducts = FXCollections.observableArrayList();
        ListView<String> selectedProductsListView = new ListView<>(observableSelectedProducts);
        selectedProductsListView.setPrefHeight(150);

        // Add Label and view to the selectedProductsVBox :
        selectedProductsVBox.getChildren().addAll(selectedProductsLabel, selectedProductsListView);

        // Create bottom part (total price) :
        VBox totalPriceVBox = new VBox(10);
        totalPriceVBox.setPadding(new Insets(10, 10, 10, 10));

        // Create label :
        totalLabel = new Label("Total: $0.00");

        // Create Pay button :
        Button payButton = new Button("Pay");
        payButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
        payButton.setMinWidth(100);
        payButton.setOnAction(event -> handlePayButton());

        // Add label and button to VBox :
        totalPriceVBox.getChildren().addAll(totalLabel, payButton);

        // Combine top and bottom parts :
        VBox rightSide = new VBox(10);
        rightSide.getChildren().addAll(selectedProductsVBox, totalPriceVBox);

        return rightSide;
    }

    /**
     * Handle Pay button
     */
    private void handlePayButton() {
        // Show a confirmation dialog saying 'Paid' :
        Alert paidAlert = new Alert(Alert.AlertType.INFORMATION);
        paidAlert.setTitle("Payment Confirmation");
        paidAlert.setHeaderText(null);
        paidAlert.setContentText("Paid successfully");
        paidAlert.setGraphic(null);

        // Show the dialog :
        paidAlert.showAndWait();

        // Clear shoppingCart :
        this.shoppingCart.clearShoppingCart();
        this.observableSelectedProducts.setAll(shoppingCart.getProducts());
        this.totalLabel.setText("Total: $0.00");
    }

    /**
     * Update total price label
     */
    private void updateTotalPrice() {
        this.totalLabel.setText(String.format("Total: $%.2f", shoppingCart.getTotalPrice()));
    }
}
