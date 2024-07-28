/**
 * Represents the shopping cart class
 * @author Reda TARGAOUI
 */
package com.supermarketcheckout.Model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    // Attributes :
    private final List<Product> products;
    private double totalPrice;

    /**
     * Initialize object
     */
    public ShoppingCart() {
        this.products = new ArrayList<>();
        this.totalPrice = 0;
    }

    /**
     * Add product to products list
     * @param product product
     */
    public void addProduct(Product product) {
        this.products.add(product);
        this.totalPrice += product.getPrice();
    }

    /**
     * Remove product from products list
     * @param product product to remove
     */
    public void removeProduct(Product product) {
        this.products.remove(product);
        this.totalPrice -= product.getPrice();
    }

    /**
     * Get products list
     * @return products list
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Get total price
     * @return total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Clear products list and initialize totalPrice
     */
    public void clearShoppingCart() {
        this.products.clear();
        this.totalPrice = 0;
    }
}
