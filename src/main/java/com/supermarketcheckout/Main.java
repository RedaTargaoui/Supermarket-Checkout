package com.supermarketcheckout;

import com.supermarketcheckout.Model.Product;
import com.supermarketcheckout.Utils.DataHandler;
import com.supermarketcheckout.Controller.HomeViewController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

	/**
	 * Start the application
	 * @param stage stage
	 * @throws Exception Exception
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// Get products:
		List<Product> products = DataHandler.readProductsFromFile();

		HomeViewController.initScene(stage, products);

		stage.setTitle("Supermarket Checkout");

		stage.show();
	}

	public static void main(String[] args) {
		// Check DATA dir existence:
		DataHandler.checkDataDirExistence();

		// Launch GUI:
		launch();
	}
}
