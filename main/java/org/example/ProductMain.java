package org.example;

import org.example.database.dao.ProductDAO;
import org.example.database.entity.Product;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductMain {

    private ProductDAO productDAO = new ProductDAO();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        // Prompt the user to enter a product name
        String productName = inputProductSearch();

        // Use our productDAO to run the query
        List<Product> products = productDAO.findLikeName(productName);

        // Print our product menu
        printProducts(products);

        // Now validate that the user enters a proper product id
        Product selectedProduct = null;
        if (products != null && !products.isEmpty()) {
            while (selectedProduct == null) {
                // Print the list of products
                int productId = promptForProductId();

                // Handle invalid product IDs
                selectedProduct = productDAO.findById(productId);
                if (selectedProduct == null) {
                    System.out.println("You have entered an invalid product id");
                }
            }

            // Prompt for new quantity in stock
            int quantity = promptQuantityInStock();
            System.out.println(quantity);

            // Update quantity in stock
            updateQuantityInStock(selectedProduct, quantity);
        } else {
            System.out.println("No products found for the search term: " + productName);
        }
    }

    public void updateQuantityInStock(Product product, int quantity) {
        product.setQuantityInStock((int) quantity);
        productDAO.update(product);
    }

    public int promptQuantityInStock() {
        while (true) {
            try {
                System.out.println("Enter the new value for quantity in stock: ");
                int quantity = scanner.nextInt();
                return quantity;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public void printProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        // Print out the results of the query
        System.out.println("Id | Product Name | Quantity In Stock");
        System.out.println("=========================================================");
        for (Product product : products) {
            System.out.println(product.getId() + " | " + product.getProductName() + " | " + product.getQuantityInStock());
        }
        System.out.println("\n");
    }

    public int promptForProductId() {
        while (true) {
            try {
                System.out.print("Enter the product id to modify: ");
                int id = scanner.nextInt();
                return id;
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public String inputProductSearch() {
        System.out.print("Enter a product name to search for: ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        ProductMain pm = new ProductMain();
        pm.run();
    }
}
