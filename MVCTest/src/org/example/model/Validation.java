package org.example.model;

public class Validation {
    public static boolean isValidId(String id) {
        return id.matches("\\d+");
    }

    public static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s]{1,50}$");
    }

    public static boolean isValidAge(String age) {
        return age.matches("^(?:[1-9]|[1-9][0-9]|100)$");
    }

    public static boolean isValidDepartment(String department) {
        return department.matches("^[a-zA-Z\\s]{1,50}$");
    }
}
