package org.example.view;

import org.example.control.EmployeeController;
import java.util.Scanner;

public class EmployeeView {
    public void start() {
        Scanner scanner = new Scanner(System.in);
        EmployeeController controller = new EmployeeController();

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.next();
            scanner.nextLine();
            switch (choice) {
                case "1" -> controller.addEmployee(scanner);
                case "2" -> controller.viewEmployees();
                case "3" -> controller.updateEmployee(scanner);
                case "4" -> controller.deleteEmployee(scanner);
                case "5" -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
