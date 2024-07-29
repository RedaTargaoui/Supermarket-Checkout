/**
 * Admin console view controller
 * @author Reda TARGAOUI
 */
package com.supermarketcheckout.Controller;

import com.supermarketcheckout.Main;
import com.supermarketcheckout.Model.Product;
import com.supermarketcheckout.Utils.DataHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminConsoleViewController {
    // Attributes:
    private List<Product> products;
    // JavaFX components:
    private Stage stage;
    @FXML
    private GridPane GridPane;
    @FXML
    private Label nbProductsLabel;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, String> productPriceColumn;
    @FXML
    private TableView<Product> productsTable;

    /**
     * Initialize scene
     * @param stage stage
     * @param products products list
     * @throws IOException IOException
     */
    public static void initScene(Stage stage, List<Product> products) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/AdminConsole-View.fxml"));
        Parent root = fxmlLoader.load();

        AdminConsoleViewController controller = fxmlLoader.getController();
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

        stage.setOnCloseRequest(event -> {
            try {
                DataHandler.writeProductsToFile(controller.products);
                System.out.println("wrote data!");
            } catch (IOException e) {
                System.err.println("Error while writing data to file!");
            }
        });

        controller.GridPane.requestFocus();
    }

    /**
     * Initialize table
     */
    @FXML
    public void initialize() {
        // Initialize productsTable:
        // Product name column:
        if (productNameColumn !=null) {
            productNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        }
        // Price column:
        if (productPriceColumn !=null) {
            productPriceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrice() + "€"));
        }
    }

    /**
     * On go back button click action
     */
    @FXML
    private void goBackButtonOnAction() throws IOException {
        HomeViewController.initScene(this.stage, this.products);
    }

    /**
     * Add product button click
     */
    @FXML
    private void addProductButtonOnAction() throws IOException {
        // Open modal to add a product:
        Product newProduct = new Product();
        openModal(newProduct, "add");
    }

    /**
     * On edit product button click
     */
    @FXML
    private void editProductButtonOnAction() throws IOException {
        // Get the selected product:
        Product selectedProduct = this.productsTable.getSelectionModel().getSelectedItem();
        // Check if a product was actually selected:
        if (selectedProduct != null) {
            // Open modal:
            openModal(selectedProduct, "edit");
        } else {
            showAlertDialog("Please select a product to modify!");
        }
    }

    /**
     * On delete product button click
     */
    @FXML
    private void deleteProductButtonOnAction() {
        // Get the selected product:
        Product selectedProduct = this.productsTable.getSelectionModel().getSelectedItem();
        // Check if a product was actually selected:
        if (selectedProduct != null) {
            // Remove product from table:
            this.productsTable.getItems().remove(selectedProduct);
            // Remove product from list:
            products.remove(selectedProduct);
            // Update number of products label:
            this.nbProductsLabel.setText("Number of products : " + this.products.size());
        } else {
            showAlertDialog("Please select a product to delete!");
        }
    }

    /**
     * Create and show alert dialog
     * @param message the message to display
     */
    private void showAlertDialog(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(message);
        //errorAlert.setGraphic(null);
        errorAlert.showAndWait();
    }

    /**
     * Modal to add or edit a product
     * @param product product
     * @param objective add or edit
     */
    private void openModal(Product product, String objective) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/Modal-View.fxml"));
        Parent root = fxmlLoader.load();

        ModalViewController modalController = fxmlLoader.getController();
        // Set inputs:
        if (objective.equals("edit")) {
            modalController.getNewProductNameInput().setText(product.getName());
            modalController.getNewProductPriceInput().setText(String.valueOf(product.getPrice()));
        }

        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.setScene(new Scene(root));

        // Set button action:
        modalController.getSubmitButton().setOnAction(e -> {
            try {
                // Get user inputs:
                String newName = modalController.getNewProductNameInput().getText();
                String newPrice = modalController.getNewProductPriceInput().getText();
                // Check if empty or not:
                if (newName.isEmpty() || newPrice.isEmpty()) {
                    modalController.getErrorLabel().setText("Please fill all fields!");
                } else {
                    // Update product:
                    product.setName(newName);
                    product.setPrice(Double.parseDouble(newPrice));
                    if (objective.equals("add")) {
                        if (this.products.isEmpty()) {
                            product.setId(1);
                        } else {
                            product.setId(this.products.get(this.products.size() - 1).getId() + 1);
                        }
                        this.products.add(product);
                        // Update number of products label:
                        this.nbProductsLabel.setText("Number of products : " + this.products.size());
                    }
                    // Update table:
                    ObservableList<Product> sortedProducts = FXCollections.observableArrayList(this.products);
                    sortedProducts.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
                    this.productsTable.setItems(sortedProducts);
                    this.productsTable.refresh();
                    // Close stage:
                    modalStage.close();
                }
            } catch (NumberFormatException exception) {
                modalController.getErrorLabel().setText("Please enter a valid price!");
            }
        });

        modalStage.showAndWait();
    }

    /**
     * Set products list and update products table
     * @param products products list
     */
    private void setProducts(List<Product> products) {
        this.products = products;
        // Fill products table:
        if (this.productsTable != null) {
            // Display product sorted by alphabetic order:
            ObservableList<Product> sortedProducts = FXCollections.observableArrayList(this.products);
            sortedProducts.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
            this.productsTable.setItems(sortedProducts);
            // Update number of products label:
            this.nbProductsLabel.setText("Number of products : " + this.products.size());
        }
    }

    /**
     * Set stage
     * @param stage stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
