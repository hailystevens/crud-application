package org.example;

import org.example.database.dao.OrderDetailsDAO;
import org.example.database.entity.OrderDetails;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderDetailsMain {

    private OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            Integer orderId = promptForOrderId();
            List<OrderDetails> orderDetailsList = orderDetailsDAO.findByOrderId(orderId);
            printOrderDetails(orderDetailsList);

            if (!orderDetailsList.isEmpty()) {
                Integer orderDetailsId = promptForOrderDetailsId();
                OrderDetails orderDetails = orderDetailsDAO.findById(orderDetailsId);

                if (orderDetails != null) {
                    int newQuantity = promptForNewQuantity();
                    updateOrderDetailsQuantity(orderDetails, newQuantity);
                } else {
                    System.out.println("Order details not found.");
                }
            }

            System.out.println("Would you like to modify another order's details? (yes/no)");
            String continueSearch = scanner.nextLine().trim().toLowerCase();
            if (!continueSearch.equals("yes")) {
                break;
            }
        }
    }

    private Integer promptForOrderId() {
        while (true) {
            try {
                System.out.print("Enter an order ID: ");
                int orderId = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character
                return orderId;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private void printOrderDetails(List<OrderDetails> orderDetailsList) {
        if (orderDetailsList == null || orderDetailsList.isEmpty()) {
            System.out.println("No order details found for the given order ID.");
            return;
        }

        System.out.println("Order Details ID | Product ID | Quantity Ordered | Price Each | Order Line Number");
        System.out.println("====================================================================================");
        for (OrderDetails orderDetails : orderDetailsList) {
            System.out.printf("%d | %d | %d | %s | %d%n",
                    orderDetails.getId(),
                    orderDetails.getProductId(),
                    orderDetails.getQuantityOrdered(),
                    orderDetails.getPriceEach(),
                    orderDetails.getOrderLineNumber());
        }
        System.out.println("\n");
    }

    private Integer promptForOrderDetailsId() {
        while (true) {
            try {
                System.out.print("Enter the order details ID to modify: ");
                int orderDetailsId = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character
                return orderDetailsId;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private int promptForNewQuantity() {
        while (true) {
            try {
                System.out.print("Enter the new quantity ordered: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character
                return quantity;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private void updateOrderDetailsQuantity(OrderDetails orderDetails, int newQuantity) {
        orderDetails.setQuantityOrdered(newQuantity);
        orderDetailsDAO.update(orderDetails);
        System.out.println("Order details updated successfully!");
    }

    public static void main(String[] args) {
        OrderDetailsMain odm = new OrderDetailsMain();
        odm.run();
    }
}

