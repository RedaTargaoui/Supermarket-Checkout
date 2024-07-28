/**
 * Admin login view controller
 * @author Reda TARGAOUI
 */
package com.supermarketcheckout.Controller;

import com.supermarketcheckout.Main;
import com.supermarketcheckout.Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminLoginViewController {
    // Attributes:
    private List<Product> products;
    // JavaFX components:
    public Stage homeStage;
    public Stage stage;
    @FXML
    private GridPane GridPane;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Label errorLabel;

    public static void initScene(Stage homeStage, List<Product> products) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("View/AdminLogin-View.fxml"));
        Parent root = fxmlLoader.load();

        AdminLoginViewController controller = fxmlLoader.getController();

        controller.stage = new Stage();
        controller.stage.setTitle("Login");

        Scene scene;
        if (controller.stage.getScene() == null) {
            scene = new Scene(root);
        } else {
            scene = new Scene(root, controller.stage.getScene().getWidth(), controller.stage.getScene().getHeight());
        }
        controller.stage.setScene(scene);

        controller.stage.show();

        // Set products list:
        controller.setProducts(products);
        // Set home stage:
        controller.setHomeStage(homeStage);

        controller.GridPane.requestFocus();
    }

    /**
     * Login when enter button is clicked
     */
    @FXML
    public void login() throws IOException {
        // Get user inputs:
        String username = usernameInput.getText();
        String password = passwordInput.getText();

        // Check all cases:
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter a valid username/password");
        } else if (!username.equals("admin") || !password.equals("admin")) {
            errorLabel.setText("Invalid username/password");
        } else if (username.equals("admin") && password.equals("admin")) {
            // Hide this stage:
            this.stage.close();
            // Open Admin console view:
            AdminConsoleViewController.initScene(this.homeStage, this.products);
        }
    }

    /**
     * Set products list
     * @param products products list
     */
    private void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Set home stage
     * @param homeStage stage
     */
    public void setHomeStage(Stage homeStage) {
        this.homeStage = homeStage;
    }
}
