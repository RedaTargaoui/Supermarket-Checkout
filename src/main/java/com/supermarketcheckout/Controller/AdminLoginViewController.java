package com.supermarketcheckout.Controller;

import com.supermarketcheckout.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginViewController {
    // Attributes:
    public Stage stage;
    @FXML
    private GridPane GridPane;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Label errorLabel;

    public static void initScene() throws IOException {
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

        controller.GridPane.requestFocus();
    }

    /**
     * Login when enter button is clicked
     */
    @FXML
    public void login() {
        // Get user inputs:
        String username = usernameInput.getText();
        String password = passwordInput.getText();

        // Check all cases:
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter a valid username/password");
        } else if (!username.equals("admin") || !password.equals("admin")) {
            errorLabel.setText("Invalid username/password");
        } else if (username.equals("admin") && password.equals("admin")) {
            // Set isLoggedIn flag to true:
            HomeViewController.isLoggedIn = true;
            // Hide this stage:
            this.stage.close();
        }
    }
}
