package org.example;

import org.example.database.dao.PaymentDAO;
import org.example.database.entity.Payment;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PaymentMain {

    private PaymentDAO paymentDAO = new PaymentDAO();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            Integer customerId = promptForCustomerId();
            List<Payment> payments = paymentDAO.findByCustomerId(customerId);
            printPayments(payments);

            if (!payments.isEmpty()) {
                Integer paymentId = promptForPaymentId();
                Payment payment = paymentDAO.findById(paymentId);

                if (payment != null) {
                    BigDecimal newAmount = promptForNewAmount();
                    updatePaymentAmount(payment, newAmount);
                } else {
                    System.out.println("Payment not found.");
                }
            }

            System.out.println("Would you like to modify another payment? (yes/no)");
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

    private void printPayments(List<Payment> payments) {
        if (payments == null || payments.isEmpty()) {
            System.out.println("No payments found for the given customer ID.");
            return;
        }

        System.out.println("Payment ID | Check Number | Payment Date | Amount");
        System.out.println("==================================================");
        for (Payment payment : payments) {
            System.out.printf("%d | %s | %s | %s%n",
                    payment.getId(),
                    payment.getCheckNumber(),
                    payment.getPaymentDate(),
                    payment.getAmount());
        }
        System.out.println("\n");
    }

    private Integer promptForPaymentId() {
        while (true) {
            try {
                System.out.print("Enter the payment ID to modify: ");
                int paymentId = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character
                return paymentId;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private BigDecimal promptForNewAmount() {
        while (true) {
            try {
                System.out.print("Enter the new amount: ");
                BigDecimal amount = scanner.nextBigDecimal();
                scanner.nextLine(); // Clear the newline character
                return amount;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid amount.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private void updatePaymentAmount(Payment payment, BigDecimal newAmount) {
        payment.setAmount(newAmount);
        paymentDAO.update(payment);
        System.out.println("Payment updated successfully!");
    }

    public static void main(String[] args) {
        PaymentMain pm = new PaymentMain();
        pm.run();
    }
}
