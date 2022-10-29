
/**
 * Title : Supermarket checkout program
 * Author : Reda Targaoui
 * Date : 23/05/2022
 */

package SupermarketCheckout;

public class Product {
	// Variables :
	private int Id; // Product's id
	private String name; // Product's name
	private double price; // Product's price
	
	// Constructor :
	public Product(int id, String name, double price) {
		this.Id = id;
		this.name = name;
		this.price = price;
	}
	
	// Get product's id :
	public int getId() {
		return this.Id;
	}
	
	// Get product's name :
	public String getName() {
		return this.name;
	}
	
	// Get product's price :
	public double getPrice() {
		return this.price;
	}
}
