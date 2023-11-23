/**
 * Get product information from database
 * @author Reda TARGAOUI
 */
package Model;

import java.sql.*;
import java.util.ArrayList;

public class DataGetter {
    // Attributes :
    private Connection connection;
    private Statement statement;

    /**
     * Initialise connection to database
     * @param jdbcURL url to database
     * @param username username
     * @param password password
     */
    public DataGetter(String jdbcURL, String username, String password) {
        try {
            System.out.println("Connecting to database...\n");
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected successfully.\n");

            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

    /**
     * Get products from database
     * @return products list
     */
    public ArrayList<Product> getProducts() {
        try {
            ArrayList<Product> products = new ArrayList<>();

            String query = "SELECT * FROM Product";

            // Execute query :
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                // add products :
                products.add(new Product(result.getInt("id"), result.getString("name"), result.getDouble("price")));
            }

            result.close();

            return products;
        } catch (SQLException e) {
            System.out.println("Error while executing query!");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Close connection
     */
    public void closeConnection() {
        try {
            connection.close();
            System.out.println("\nConnection closed!");
        } catch (SQLException e) {
            System.out.println("\nError while closing connection!");
            e.printStackTrace();
        }
    }
}
