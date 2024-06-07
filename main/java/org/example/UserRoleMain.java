package org.example;

import org.example.database.dao.UserRoleDAO;
import org.example.database.entity.UserRole;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserRoleMain {

    private UserRoleDAO userRoleDAO = new UserRoleDAO();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            Integer userId = promptForUserId();
            List<UserRole> userRoles = userRoleDAO.findByUserId(userId);
            printUserRoles(userRoles);

            if (!userRoles.isEmpty()) {
                Integer userRoleId = promptForUserRoleId();
                UserRole userRole = userRoleDAO.findById(userRoleId);

                if (userRole != null) {
                    String newRoleName = promptForNewRoleName();
                    updateUserRoleName(userRole, newRoleName);
                } else {
                    System.out.println("User role not found.");
                }
            }

            System.out.println("Would you like to modify another user role? (yes/no)");
            String continueSearch = scanner.nextLine().trim().toLowerCase();
            if (!continueSearch.equals("yes")) {
                break;
            }
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

    private void printUserRoles(List<UserRole> userRoles) {
        if (userRoles == null || userRoles.isEmpty()) {
            System.out.println("No user roles found for the given user ID.");
            return;
        }

        System.out.println("User Role ID | Role Name");
        System.out.println("=======================");
        for (UserRole userRole : userRoles) {
            System.out.printf("%d | %s%n",
                    userRole.getId(),
                    userRole.getRoleName());
        }
        System.out.println("\n");
    }

    private Integer promptForUserRoleId() {
        while (true) {
            try {
                System.out.print("Enter the user role ID to modify: ");
                int userRoleId = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character
                return userRoleId;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private String promptForNewRoleName() {
        System.out.print("Enter the new role name: ");
        return scanner.nextLine().trim();
    }

    private void updateUserRoleName(UserRole userRole, String newRoleName) {
        userRole.setRoleName(newRoleName);
        userRoleDAO.update(userRole);
        System.out.println("User role updated successfully!");
    }

    public static void main(String[] args) {
        UserRoleMain urm = new UserRoleMain();
        urm.run();
    }
}

