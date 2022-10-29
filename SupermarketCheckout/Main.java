
/**
 * Title : Supermarket checkout program
 * Author : Reda Targaoui
 * Date : 23/05/2022
 */

package SupermarketCheckout;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// To scan user's input : 
		Scanner Input;
		int Choice;
		// Create an array of all products :
		ArrayList<Product> allProducts = new ArrayList<Product>();
		// To read file (data.txt contains products and their prices) :
		BufferedReader file = new BufferedReader(new FileReader("data.txt"));
		String line = null;
		while ( (line = file.readLine()) != null ) {
			String[] temp = line.split(",");
			int id = Integer.parseInt(temp[0]);
			String name = temp[1];
			double price = Double.parseDouble(temp[2]);
			allProducts.add(new Product(id, name, price));
		}
		file.close();
		// Display all products's array :
		System.out.println("---------------------------- All Products --------------------------- ");
		System.out.printf("%-6s%-20s%6s\n", "ID","Name","Price");
		for (int i = 0; i < allProducts.size(); i++) {
			System.out.printf("%-6d%-20s%6.2f\n", allProducts.get(i).getId(), allProducts.get(i).getName(), allProducts.get(i).getPrice());
		}
		// Create checkout :
		Checkout check = new Checkout();
		// if loop = true we continue , else we stop : 
		boolean loop = true;
		while ( loop == true ) {
			Input = new Scanner(System.in);
			System.out.println("-> Checkout : Press 1 to add a product, 2 to remove a product, 0 to exit : ");
			Choice = Input.nextInt();
			// if user want to add a product :
			if ( Choice == 1 ) {
				Input = new Scanner(System.in);
				System.out.println("->Add : Print product's ID  : ");
				int ProdID = Input.nextInt();
				check.Add(allProducts.get(ProdID-1));
				check.Display();
				loop = true;
			}
			// if user wants to remove a product :
			if ( Choice == 2 ) {
				Input = new Scanner(System.in);
				System.out.println("->Delete : Print product's ID : ");
				int ProdID = Input.nextInt();
				check.delete(allProducts.get(ProdID-1));
				check.Display();
				loop = true;
			}
			// if user wants to exit :
			if ( Choice == 0 ) {
				check.Display();
				loop = false;
			}
		}
	}
}
