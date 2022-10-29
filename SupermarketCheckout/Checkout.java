
/**
 * Title : Supermarket checkout program
 * Author : Reda Targaoui
 * Date : 23/05/2022
 */

package SupermarketCheckout;

import java.util.ArrayList;

public class Checkout {
	// Variables :
	private ArrayList<Product> products; // Array to stock every product chosen by the user
	private double totalPrice; // The total price of the chosen products
	
	// Constructor :
	public Checkout() {
		products = new ArrayList<Product>();
		this.totalPrice = 0;
	}
	
	// add product :
	public void Add(Product p) {
		products.add(p);
		this.totalPrice += p.getPrice();
	}
	
	// delete product :
	public void delete(Product p) {
		products.remove(p);
		this.totalPrice -= p.getPrice();
	}
	
	// Display the bill :
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
