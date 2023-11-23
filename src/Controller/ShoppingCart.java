/**
 * Represents the shopping cart class
 * @author Reda TARGAOUI
 */
package Controller;

import Model.Product;
import java.util.ArrayList;

public class ShoppingCart {
    // Attributes :
    private final ArrayList<String> products;
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
     * @param productName product name
     * @param productPrice product price
     */
    public void addProduct(String productName, double productPrice) {
        this.products.add(productName);
        this.totalPrice += productPrice;
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
    public ArrayList<String> getProducts() {
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
