package org.example;

import org.example.database.dao.ProductLineDAO;
import org.example.database.entity.ProductLine;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductLineMain {

    private ProductLineDAO productLineDAO = new ProductLineDAO();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("1. List all product lines");
            System.out.println("2. Add new product line");
            System.out.println("3. Update existing product line");
            System.out.println("4. Delete product line");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            switch (choice) {
                case 1:
                    listAllProductLines();
                    break;
                case 2:
                    addNewProductLine();
                    break;
                case 3:
                    updateProductLine();
                    break;
                case 4:
                    deleteProductLine();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            System.out.println("Would you like to perform another operation? (yes/no)");
            String continueSearch = scanner.nextLine().trim().toLowerCase();
            if (!continueSearch.equals("yes")) {
                break;
            }
        }
    }

    private void listAllProductLines() {
        List<ProductLine> productLines = productLineDAO.findAll();
        printProductLines(productLines);
    }

    private void printProductLines(List<ProductLine> productLines) {
        if (productLines == null || productLines.isEmpty()) {
            System.out.println("No product lines found.");
            return;
        }

        System.out.println("Product Line ID | Product Line | Text Description | HTML Description | Image");
        System.out.println("================================================================================");
        for (ProductLine productLine : productLines) {
            System.out.printf("%d | %s | %s | %s | %s%n",
                    productLine.getId(),
                    productLine.getProductLine(),
                    productLine.getTextDescription(),
                    productLine.getHtmlDescription(),
                    productLine.getImage());
        }
        System.out.println("\n");
    }

    private void addNewProductLine() {
        ProductLine productLine = new ProductLine();

        System.out.print("Enter product line: ");
        productLine.setProductLine(scanner.nextLine().trim());

        System.out.print("Enter text description: ");
        productLine.setTextDescription(scanner.nextLine().trim());

        System.out.print("Enter HTML description: ");
        productLine.setHtmlDescription(scanner.nextLine().trim());

        System.out.print("Enter image URL: ");
        productLine.setImage(scanner.nextLine().trim());

        productLineDAO.insert(productLine);
        System.out.println("Product line added successfully!");
    }

    private void updateProductLine() {
        Integer productLineId = promptForProductLineId();
        ProductLine productLine = productLineDAO.findById(productLineId);

        if (productLine != null) {
            String field = promptForFieldToUpdate();
            if (field != null) {
                updateProductLineField(productLine, field);
                productLineDAO.update(productLine);
                System.out.println("Product line updated successfully!");
            }
        } else {
            System.out.println("Product line not found.");
        }
    }

    private void deleteProductLine() {
        Integer productLineId = promptForProductLineId();
        ProductLine productLine = productLineDAO.findById(productLineId);

        if (productLine != null) {
            productLineDAO.delete(productLine);
            System.out.println("Product line deleted successfully!");
        } else {
            System.out.println("Product line not found.");
        }
    }

    private Integer promptForProductLineId() {
        while (true) {
            try {
                System.out.print("Enter a product line ID: ");
                int productLineId = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character
                return productLineId;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private String promptForFieldToUpdate() {
        System.out.println("Which field would you like to update?");
        System.out.println("1. Product Line");
        System.out.println("2. Text Description");
        System.out.println("3. HTML Description");
        System.out.println("4. Image URL");
        System.out.print("Enter the number of the field you want to update (or 'exit' to cancel): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "1":
                return "productLine";
            case "2":
                return "textDescription";
            case "3":
                return "htmlDescription";
            case "4":
                return "image";
            case "exit":
                return null;
            default:
                System.out.println("Invalid choice. Please try again.");
                return promptForFieldToUpdate();
        }
    }

    private void updateProductLineField(ProductLine productLine, String field) {
        System.out.print("Enter the new value for " + field + ": ");
        String newValue = scanner.nextLine().trim();
        switch (field) {
            case "productLine":
                productLine.setProductLine(newValue);
                break;
            case "textDescription":
                productLine.setTextDescription(newValue);
                break;
            case "htmlDescription":
                productLine.setHtmlDescription(newValue);
                break;
            case "image":
                productLine.setImage(newValue);
                break;
            default:
                System.out.println("Unknown field. No updates made.");
        }
    }

    public static void main(String[] args) {
        ProductLineMain plm = new ProductLineMain();
        plm.run();
    }
}

