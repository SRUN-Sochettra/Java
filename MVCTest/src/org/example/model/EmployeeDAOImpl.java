package org.example.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employee (name, age, department) VALUES (?, ?, ?) RETURNING id";

        try (Connection conn = Connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the values for the parameters
            pstmt.setString(1, employee.getName());
            pstmt.setInt(2, employee.getAge());
            pstmt.setString(3, employee.getDepartment());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int generatedId = rs.getInt("id");
                System.out.println("Inserted Employee with ID: " + generatedId);
                employee.setId(generatedId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = Connect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employee")) {
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("department")));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (Connection conn = Connect.getConnection()) {
            String query = "UPDATE employee SET name=?, age=?, department=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, employee.getName());
            stmt.setInt(2, employee.getAge());
            stmt.setString(3, employee.getDepartment());
            stmt.setInt(4, employee.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("employee updated successfully!");
            } else {
                System.out.println("employee not found!");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (Connection conn = Connect.getConnection()) {
            String query = "DELETE FROM employee WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("employee deleted successfully!");
            } else {
                System.out.println("employee not found!");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
