# Supermarket Checkout App

This is a simple Supermarket Checkout App implemented in Java, following the Model-View-Controller (MVC) design pattern.

## Overview

The app consists of the following classes:

- **Product.java**: Represents a product with attributes such as ID, name, and price.

- **DataGetter.java**: Retrieves product data from a MySQL database using Connector/J.

- **GUI.java**: Creates the graphical user interface (GUI) for the application using JavaFX.

- **ShoppingCart.java**: Handles user-selected products, allowing for adding or removing products, and calculates the total price.

## MVC Pattern

The application follows the MVC design pattern:

- **Model**:
  - **Product.java**: Represents the data structure for products.
  - **DataGetter.java**: Retrieves product data from the database.

- **View**:
  - **GUI.java**: Implements the graphical user interface using JavaFX.

- **Controller**:
  - **ShoppingCart.java**: Controls the interaction between the model and the view. Handles user input, updates the model, and refreshes the view accordingly.

## Used Libraries

- **JavaFX**: Used for creating the graphical user interface.
- **Connector/J**: Used for MySQL database connectivity.
