package org.example;

import org.example.database.dao.OrderDAO;
import org.example.database.entity.Order;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderMain {

    private OrderDAO orderDAO = new OrderDAO();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            Integer customerId = promptForCustomerId();
            List<Order> orders = orderDAO.findByCustomerId(customerId);
            printOrders(orders);

            if (!orders.isEmpty()) {
                Integer orderId = promptForOrderId();
                Order order = orderDAO.findById(orderId);

                if (order != null) {
                    String newComment = promptForNewComment();
                    updateOrderComment(order, newComment);
                } else {
                    System.out.println("Order not found.");
                }
            }

            System.out.println("Would you like to search for another customer's orders? (yes/no)");
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

    private void printOrders(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            System.out.println("No orders found for the given customer ID.");
            return;
        }

        System.out.println("Order ID | Order Date | Required Date | Shipped Date | Status | Comments");
        System.out.println("=============================================================================");
        for (Order order : orders) {
            System.out.printf("%d | %s | %s | %s | %s | %s%n",
                    order.getId(),
                    order.getOrderDate(),
                    order.getRequiredDate(),
                    order.getShippedDate(),
                    order.getStatus(),
                    order.getComments());
        }
        System.out.println("\n");
    }

    private Integer promptForOrderId() {
        while (true) {
            try {
                System.out.print("Enter the order ID to modify: ");
                int orderId = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character
                return orderId;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private String promptForNewComment() {
        System.out.print("Enter the new comment for the order: ");
        return scanner.nextLine();
    }

    private void updateOrderComment(Order order, String newComment) {
        order.setComments(newComment);
        orderDAO.update(order);
        System.out.println("Order comment updated successfully!");
    }

    public static void main(String[] args) {
        OrderMain com = new OrderMain();
        com.run();
    }
}

