
/**
 * @brief : Supermarket checkout program
 * @author : Reda Targaoui
 * @date : 23/05/2022
 */

package SupermarketCheckout;

public class Product {
	// Variables :
	private int Id; // Product's id
	private String name; // Product's name
	private double price; // Product's price
	
	/**
	  * @brief constructor
	*/
	public Product(int id, String name, double price) {
		this.Id = id;
		this.name = name;
		this.price = price;
	}
	
	/**
	  * @brief get product's id
	*/
	public int getId() {
		return this.Id;
	}
	
	/**
	  * @brief get product's name
	*/
	public String getName() {
		return this.name;
	}
	
	/**
	  * @brief get product's price
	*/
	public double getPrice() {
		return this.price;
	}
}
