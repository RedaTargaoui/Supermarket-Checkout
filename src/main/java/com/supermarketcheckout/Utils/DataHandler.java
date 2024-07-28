/**
 * handle data files
 * @author Reda TARGAOUI
 */
package com.supermarketcheckout.Utils;

import com.supermarketcheckout.Model.Product;

import java.io.*;
import java.util.List;

public class DataHandler {
    // Attributes:
    private static final String DATA_DIR = "data/";
    private static final String PRODUCTS_DATA_FILE = "products.dat";

    /**
     * Constructor
     */
    public DataHandler() {
    }

    /**
     * Check id data dir exist and create it if it doesn't
     */
    public static void checkDataDirExistence() {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            System.err.println("->Data directory does not exist!");
            if (!dataDir.mkdirs()) {
                System.err.println("->Couldn't create data directory!");
            }
            System.err.println("->Data directory created successfully!");
        }
    }

    /**
     * Write products to file
     * @param products list of products
     */
    public static void writeProductsToFile(List<Product> products) throws IOException {
        File file = new File(DATA_DIR + PRODUCTS_DATA_FILE);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(products);
        }
    }

    /**
     * Read products data from file
     * @return List of products
     * @throws IOException IOException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static List<Product> readProductsFromFile() throws IOException, ClassNotFoundException {
        File file = new File(DATA_DIR + PRODUCTS_DATA_FILE);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Product>) ois.readObject();
        }
    }
}
