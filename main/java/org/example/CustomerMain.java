package org.example;

import org.example.database.dao.CustomerDAO;
import org.example.database.entity.Customer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerMain {

    private CustomerDAO customerDAO = new CustomerDAO();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            Integer customerId = promptForCustomerId();
            Customer customer = customerDAO.findById(customerId);

            if (customer != null) {
                printCustomerDetails(customer);
                String field = promptForFieldToUpdate();
                if (field != null) {
                    updateCustomerField(customer, field);
                    customerDAO.update(customer);
                    System.out.println("Customer details updated successfully!");
                }
            } else {
                System.out.println("Customer not found.");
            }

            System.out.println("Would you like to modify another customer? (yes/no)");
            String continueSearch = scanner.nextLine().trim().toLowerCase();
            if (!continueSearch.equals("yes")) {
                break;
            }
        }
    }

    private Integer promptForCustomerId() {
        while (true) {
            try {
                System.out.print("Enter a customer ID: ");
                int customerId = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character
                return customerId;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private void printCustomerDetails(Customer customer) {
        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Customer Name: " + customer.getCustomerName());
        System.out.println("Contact Last Name: " + customer.getContactLastname());
        System.out.println("Contact First Name: " + customer.getContactFirstname());
        System.out.println("Phone: " + customer.getPhone());
        System.out.println("Address Line 1: " + customer.getAddressLine1());
        System.out.println("Address Line 2: " + customer.getAddressLine2());
        System.out.println("City: " + customer.getCity());
        System.out.println("State: " + customer.getState());
        System.out.println("Postal Code: " + customer.getPostalCode());
        System.out.println("Country: " + customer.getCountry());
        System.out.println("Credit Limit: " + customer.getCreditLimit());
    }

    private String promptForFieldToUpdate() {
        System.out.println("Which field would you like to update?");
        System.out.println("1. Customer Name");
        System.out.println("2. Contact Last Name");
        System.out.println("3. Contact First Name");
        System.out.println("4. Phone");
        System.out.println("5. Address Line 1");
        System.out.println("6. Address Line 2");
        System.out.println("7. City");
        System.out.println("8. State");
        System.out.println("9. Postal Code");
        System.out.println("10. Country");
        System.out.println("11. Credit Limit");
        System.out.print("Enter the number of the field you want to update (or 'exit' to cancel): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "1":
                return "customerName";
            case "2":
                return "contactLastname";
            case "3":
                return "contactFirstname";
            case "4":
                return "phone";
            case "5":
                return "addressLine1";
            case "6":
                return "addressLine2";
            case "7":
                return "city";
            case "8":
                return "state";
            case "9":
                return "postalCode";
            case "10":
                return "country";
            case "11":
                return "creditLimit";
            case "exit":
                return null;
            default:
                System.out.println("Invalid choice. Please try again.");
                return promptForFieldToUpdate();
        }
    }

    private void updateCustomerField(Customer customer, String field) {
        System.out.print("Enter the new value for " + field + ": ");
        String newValue = scanner.nextLine().trim();
        switch (field) {
            case "customerName":
                customer.setCustomerName(newValue);
                break;
            case "contactLastname":
                customer.setContactLastname(newValue);
                break;
            case "contactFirstname":
                customer.setContactFirstname(newValue);
                break;
            case "phone":
                customer.setPhone(newValue);
                break;
            case "addressLine1":
                customer.setAddressLine1(newValue);
                break;
            case "addressLine2":
                customer.setAddressLine2(newValue);
                break;
            case "city":
                customer.setCity(newValue);
                break;
            case "state":
                customer.setState(newValue);
                break;
            case "postalCode":
                customer.setPostalCode(newValue);
                break;
            case "country":
                customer.setCountry(newValue);
                break;
            case "creditLimit":
                try {
                    customer.setCreditLimit(Double.parseDouble(newValue));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format for credit limit.");
                }
                break;
            default:
                System.out.println("Unknown field. No updates made.");
        }
    }

    public static void main(String[] args) {
        CustomerMain cm = new CustomerMain();
        cm.run();
    }
}


