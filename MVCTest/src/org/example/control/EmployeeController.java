package org.example.control;

import org.example.model.*;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    private EmployeeDAO employeeDAO;

    public EmployeeController() {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    public void addEmployee(Scanner scanner) {
        String name;
        System.out.print("Enter Employee name: ");
        name = scanner.nextLine();
        while (!Validation.isValidName(name)) {
            System.out.print("Enter a valid name: ");
            name = scanner.nextLine();
        }

        String age;
        System.out.print("Enter Employee age: ");
        age = scanner.nextLine();
        while (!Validation.isValidAge(age)) {
            System.out.print("Enter a valid age: ");
            age = scanner.nextLine();
        }

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        while (!Validation.isValidDepartment(department)) {
            System.out.println("Enter a valid department: ");
            department = scanner.nextLine();
        }
        Employee employee = new Employee(0, name, Integer.parseInt(age), department);
        employeeDAO.addEmployee(employee);
    }
    public void viewEmployees() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No Employees found.");
            return;
        }
        Table employeeTable = new Table(4, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        employeeTable.setColumnWidth(0,20,100);
        employeeTable.setColumnWidth(1,20,100);
        employeeTable.setColumnWidth(2,20,100);
        employeeTable.setColumnWidth(3,20,100);
        employeeTable.addCell("ID");
        employeeTable.addCell("Name");
        employeeTable.addCell("Age");
        employeeTable.addCell("Department");
        for (Employee employee : employees) {
            employeeTable.addCell(String.valueOf(employee.getId()));
            employeeTable.addCell(employee.getName());
            employeeTable.addCell(String.valueOf(employee.getAge()));
            employeeTable.addCell(String.valueOf(employee.getDepartment()));
        }
        System.out.println(employeeTable.render());
    }

    public void updateEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Department: ");
        String department = scanner.nextLine();
        Employee employee = new Employee(id, name, age, department);
        employeeDAO.updateEmployee(employee);
    }

    public void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        employeeDAO.deleteEmployee(id);
    }
}
