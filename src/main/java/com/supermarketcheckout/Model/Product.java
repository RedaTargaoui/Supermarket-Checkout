/**
 * Product model class
 * @author : Reda TARGAOUI
 */
package com.supermarketcheckout.Model;

import java.io.Serializable;

public class Product implements Serializable {
	// Attributes :
	private final int id;
	private final String name;
	private final double price;


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
	 * Get product name
	 * @return product name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get product price
	 * @return product price
	 */
	public double getPrice() {
		return this.price;
	}
}
