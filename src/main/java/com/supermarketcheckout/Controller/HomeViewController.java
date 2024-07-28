/**
 * Home view controller
 * @author Reda TARGAOUI
 */
package com.supermarketcheckout.Controller;

import com.supermarketcheckout.Main;
import com.supermarketcheckout.Model.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HomeViewController {
    // Attributes:
    private ShoppingCart shoppingCart;
    private List<Product> products;
    public static boolean isLoggedIn = false;
    // JavaFX components:
    private Stage stage;
    @FXML
    private GridPane GridPane;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> priceColumn;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, String> selectedProductNameColumn;
    @FXML
    private TableColumn<Product, String> selectedProductPriceColumn;
    @FXML
    private TableView<Product> selectedProductsTable;
    @FXML
    private Label totalPriceLabel;

    /**
     * Initialize scene
     * @param stage stage
     * @param products products list
     * @return HomeViewController
     * @throws IOException IOException
     */
    public static HomeViewController initScene(Stage stage, List<Product> products) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/Home-view.fxml"));
        Parent root = fxmlLoader.load();

        HomeViewController controller = fxmlLoader.getController();
        controller.setStage(stage);

        // Set products list:
        controller.setProducts(products);

        Scene scene;
        if (stage.getScene() == null) {
            scene = new Scene(root);
        } else {
            scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        }
        stage.setScene(scene);

        controller.GridPane.requestFocus();

        return controller;
    }

    /**
     * Initialize tables and components
     */
    @FXML
    public void initialize() {
        // Initialize shopping cart:
        this.shoppingCart = new ShoppingCart();

        // Initialize productsTable:
        // Product name column:
        if (nameColumn !=null) {
            nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        }
        // Price column:
        if (priceColumn !=null) {
            priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrice() + "€"));
        }

        // Initialize selectedProductsTable:
        // Product name column:
        if (selectedProductNameColumn !=null) {
            selectedProductNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        }
        // Price column:
        if (selectedProductPriceColumn !=null) {
            selectedProductPriceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrice() + "€"));
        }
    }

    /**
     * Add a product on mouse click
     * @param event mouseEvent
     */
    @FXML
    private void productSelection(MouseEvent event) {
        // On mouse double click:
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            // Get the selected product:
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                // Add product to shopping cart:
                this.shoppingCart.addProduct(selectedProduct);
                // Add to selected products table:
                selectedProductsTable.setItems(FXCollections.observableArrayList(this.shoppingCart.getProducts()));
                // Update total price label:
                this.totalPriceLabel.setText(String.format("Total price: %.2f€", this.shoppingCart.getTotalPrice()));
            }
        }
    }

    /**
     * Set products list and update products table
     * @param products products list
     */
    private void setProducts(List<Product> products) {
        this.products = products;
        // Fill products table:
        if (productsTable != null) {
            // Display product sorted by alphabetic order:
            ObservableList<Product> sortedProducts = FXCollections.observableArrayList(this.products);
            sortedProducts.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
            productsTable.setItems(sortedProducts);
        }
    }

    /**
     * Purchase button action
     */
    @FXML
    private void purchaseButtonEvent() {
        // Show a confirmation dialog :
        Alert paidAlert = new Alert(Alert.AlertType.INFORMATION);
        paidAlert.setTitle("Payment Confirmation");
        paidAlert.setHeaderText(null);
        paidAlert.setContentText("Purchased successfully");
        paidAlert.setGraphic(null);
        // Show the dialog :
        paidAlert.showAndWait();
        // Clear shoppingCart, selected products table and total price label :
        this.shoppingCart.clearShoppingCart();
        this.selectedProductsTable.setItems(FXCollections.observableArrayList(this.shoppingCart.getProducts()));
        this.totalPriceLabel.setText(String.format("Total price: %.2f€", this.shoppingCart.getTotalPrice()));
    }

    /**
     * Admin button action
     */
    @FXML
    private void adminButtonEvent() throws IOException {
        if (!isLoggedIn) {
            AdminLoginViewController.initScene();
        } else {
            System.out.println("Already logged in");
        }
    }

    /**
     * Get stage
     * @return stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Set stage
     * @param stage stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
