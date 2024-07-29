/**
 * Modal view controller
 * @author Reda TARGAOUI
 */
package com.supermarketcheckout.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ModalViewController {
    // JavaFX components:
    @FXML
    private Label errorLabel;
    @FXML
    private TextField newProductNameInput;
    @FXML
    private TextField newProductPriceInput;
    @FXML
    private Button submitButton;

    /**
     * Get new name text field
     * @return new name text field
     */
    public TextField getNewProductNameInput() {
        return newProductNameInput;
    }

    /**
     * Get new price text field
     * @return new price text field
     */
    public TextField getNewProductPriceInput() {
        return newProductPriceInput;
    }

    /**
     * get submit button
     * @return submit button
     */
    public Button getSubmitButton() {
        return submitButton;
    }

    /**
     * Get error label
     * @return error label
     */
    public Label getErrorLabel() {
        return errorLabel;
    }
}
