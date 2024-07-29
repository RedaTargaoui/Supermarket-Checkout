# Supermarket Checkout App

This is a simple Supermarket Checkout App implemented in Java, following the Model-View-Controller 
(MVC) design pattern, and a graphical user interface.

## Overview

The app consists of 2 principal views, **Home view** which shows all the products and user can select 
the products he wants and then pay at the end, **Admin Console view** where an admin can log in and 
can add product, edit product and delete a product.

## Files:

The app consists of the following principal classes:

- **Product.java**: Represents a product with attributes such as ID, name, and price.

- **DataHandler.java**: Handles data reading and writing into data file

- **ShoppingCart.java**: Handles user-selected products, allowing for adding or removing products, and calculates the total price.

- **Controllers**: FXML Views controllers

## Used

- **Java 17**
- **JavaFX**.
- **SceneBuilder**.
