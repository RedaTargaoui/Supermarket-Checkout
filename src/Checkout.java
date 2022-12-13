
/**
 * @brief : Supermarket checkout program
 * @author : Reda Targaoui
 * @date : 23/05/2022
 */

package SupermarketCheckout;

import java.util.ArrayList;

public class Checkout {
	// Variables :
	private ArrayList<Product> products; // Array to stock every product chosen by the user
	private double totalPrice; // The total price of the chosen products
	
	/**
	  * @brief Constructor 
	*/
	public Checkout() {
		products = new ArrayList<Product>();
		this.totalPrice = 0;
	}
	
	/**
	  * @brief Add a product to the list
	  * @param Product p
	*/
	public void Add(Product p) {
		products.add(p);
		this.totalPrice += p.getPrice();
	}
	
	/**
	  * @brief Delete product from the list 
	  * @param Product p
	*/
	public void delete(Product p) {
		products.remove(p);
		this.totalPrice -= p.getPrice();
	}
	
	/**
	  * @brief Display list
	*/
	public void Display() {
		System.out.println("---------------------------- The Checkout --------------------------- ");
		System.out.printf("%-6s%-20s%6s\n", "ID", "Name", "Price");
		for (int i = 0; i < products.size(); i++) {
			System.out.printf("%-6d%-20s%6.2f\n", products.get(i).getId(), products.get(i).getName(), products.get(i).getPrice());
		}
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("      Total price  :      " + this.totalPrice + "$");
	}
}
