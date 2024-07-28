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

		stage.setOnCloseRequest(event -> {

		});

		stage.show();
	}

	public static void main(String[] args) {
		// Check DATA dir existence:
		DataHandler.checkDataDirExistence();

		/*List<Product> products = new ArrayList<>();
		products.add(new Product(1, "Apple", 0.99));
		products.add(new Product(2, "Banana", 0.59));
		products.add(new Product(3, "Orange", 1.29));

		try {
			DataHandler.writeProductsToFile(products);
			List<Product> readProducts = DataHandler.readProductsFromFile();
			for (Product product : readProducts) {
				System.out.println(product.getId() +", " + product.getName() + ", " + product.getPrice());
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}*/

		// Launch GUI:
		launch();
	}
}
