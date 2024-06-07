package org.example;

import org.example.database.dao.UserDAO;
import org.example.database.entity.User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserMain {

    private UserDAO userDAO = new UserDAO();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("1. List all users");
            System.out.println("2. Add new user");
            System.out.println("3. Update existing user");
            System.out.println("4. Delete user");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            switch (choice) {
                case 1:
                    listAllUsers();
                    break;
                case 2:
                    addNewUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
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

    private void listAllUsers() {
        List<User> users = userDAO.findAll();
        printUsers(users);
    }

    private void printUsers(List<User> users) {
        if (users == null || users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("User ID | Email | Full Name");
        System.out.println("===========================");
        for (User user : users) {
            System.out.printf("%d | %s | %s%n",
                    user.getId(),
                    user.getEmail(),
                    user.getFullName());
        }
        System.out.println("\n");
    }

    private void addNewUser() {
        User user = new User();

        System.out.print("Enter email: ");
        user.setEmail(scanner.nextLine().trim());

        System.out.print("Enter password: ");
        user.setPassword(scanner.nextLine().trim());

        System.out.print("Enter full name: ");
        user.setFullName(scanner.nextLine().trim());

        userDAO.insert(user);
        System.out.println("User added successfully!");
    }

    private void updateUser() {
        Integer userId = promptForUserId();
        User user = userDAO.findById(userId);

        if (user != null) {
            String field = promptForFieldToUpdate();
            if (field != null) {
                updateUserField(user, field);
                userDAO.update(user);
                System.out.println("User updated successfully!");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    private void deleteUser() {
        Integer userId = promptForUserId();
        User user = userDAO.findById(userId);

        if (user != null) {
            userDAO.delete(user);
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found.");
        }
    }

    private Integer promptForUserId() {
        while (true) {
            try {
                System.out.print("Enter a user ID: ");
                int userId = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character
                return userId;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private String promptForFieldToUpdate() {
        System.out.println("Which field would you like to update?");
        System.out.println("1. Email");
        System.out.println("2. Password");
        System.out.println("3. Full Name");
        System.out.print("Enter the number of the field you want to update (or 'exit' to cancel): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "1":
                return "email";
            case "2":
                return "password";
            case "3":
                return "fullName";
            case "exit":
                return null;
            default:
                System.out.println("Invalid choice. Please try again.");
                return promptForFieldToUpdate();
        }
    }

    private void updateUserField(User user, String field) {
        System.out.print("Enter the new value for " + field + ": ");
        String newValue = scanner.nextLine().trim();
        switch (field) {
            case "email":
                user.setEmail(newValue);
                break;
            case "password":
                user.setPassword(newValue);
                break;
            case "fullName":
                user.setFullName(newValue);
                break;
            default:
                System.out.println("Unknown field. No updates made.");
        }
    }

    public static void main(String[] args) {
        UserMain um = new UserMain();
        um.run();
    }
}

