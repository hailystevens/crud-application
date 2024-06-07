package org.example;

import org.example.database.dao.EmployeeDAO;
import org.example.database.entity.Employee;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeMain {

    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            Integer employeeId = promptForEmployeeId();
            Employee employee = employeeDAO.findById(employeeId);

            if (employee != null) {
                printEmployeeDetails(employee);
                String field = promptForFieldToUpdate();
                if (field != null) {
                    updateEmployeeField(employee, field);
                    employeeDAO.update(employee);
                    System.out.println("Employee details updated successfully!");
                }
            } else {
                System.out.println("Employee not found.");
            }

            System.out.println("Would you like to modify another employee? (yes/no)");
            String continueSearch = scanner.nextLine().trim().toLowerCase();
            if (!continueSearch.equals("yes")) {
                break;
            }
        }
    }

    private Integer promptForEmployeeId() {
        while (true) {
            try {
                System.out.print("Enter an employee ID: ");
                int employeeId = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character
                return employeeId;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private void printEmployeeDetails(Employee employee) {
        System.out.println("Employee ID: " + employee.getId());
        System.out.println("First Name: " + employee.getFirstname());
        System.out.println("Last Name: " + employee.getLastname());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Job Title: " + employee.getJobTitle());
        System.out.println("Extension: " + employee.getExtension());
        System.out.println("Office ID: " + employee.getOfficeId());
        System.out.println("Reports To: " + employee.getReportsTo());
        System.out.println("Vacation Hours: " + employee.getVacationHours());
        System.out.println("Profile Image URL: " + employee.getProfileImageUrl());
    }

    private String promptForFieldToUpdate() {
        System.out.println("Which field would you like to update?");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Email");
        System.out.println("4. Job Title");
        System.out.println("5. Extension");
        System.out.println("6. Office ID");
        System.out.println("7. Reports To");
        System.out.println("8. Vacation Hours");
        System.out.println("9. Profile Image URL");
        System.out.print("Enter the number of the field you want to update (or 'exit' to cancel): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "1": return "firstname";
            case "2": return "lastname";
            case "3": return "email";
            case "4": return "jobTitle";
            case "5": return "extension";
            case "6": return "officeId";
            case "7": return "reportsTo";
            case "8": return "vacationHours";
            case "9": return "profileImageUrl";
            case "exit": return null;
            default:
                System.out.println("Invalid choice. Please try again.");
                return promptForFieldToUpdate();
        }
    }

    private void updateEmployeeField(Employee employee, String field) {
        System.out.print("Enter the new value for " + field + ": ");
        String newValue = scanner.nextLine().trim();
        switch (field) {
            case "firstname":
                employee.setFirstname(newValue);
                break;
            case "lastname":
                employee.setLastname(newValue);
                break;
            case "email":
                employee.setEmail(newValue);
                break;
            case "jobTitle":
                employee.setJobTitle(newValue);
                break;
            case "extension":
                employee.setExtension(newValue);
                break;
            case "officeId":
                try {
                    employee.setOfficeId(Integer.parseInt(newValue));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format for office ID.");
                }
                break;
            case "reportsTo":
                try {
                    employee.setReportsTo(Integer.parseInt(newValue));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format for reports to.");
                }
                break;
            case "vacationHours":
                try {
                    employee.setVacationHours(Integer.parseInt(newValue));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format for vacation hours.");
                }
                break;
            case "profileImageUrl":
                employee.setProfileImageUrl(newValue);
                break;
            default:
                System.out.println("Unknown field. No updates made.");
        }
    }

    public static void main(String[] args) {
        EmployeeMain em = new EmployeeMain();
        em.run();
    }
}

