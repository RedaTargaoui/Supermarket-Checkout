/**
 * Admin console view controller
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
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
