/**
 * Product model class
 * @author : Reda TARGAOUI
 */
package com.supermarketcheckout.Model;

import java.io.Serializable;

public class Product implements Serializable {
	// Attributes :
	private int id;
	private String name;
	private double price;

	/**
	 * Initialize object
	 */
	public Product() {
		this.id = 0;
	}

	/**
	 * Initialize object
	 * @param id product id
	 * @param name product name
	 * @param price product price
	 */
	public Product(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	/**
	 * Get product id
	 * @return product id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Set product id
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get product name
	 * @return product name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set name
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get product price
	 * @return product price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Set price
	 * @param price price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
