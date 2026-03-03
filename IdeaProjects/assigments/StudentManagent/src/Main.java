import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

class Student {
    private String id;
    private String name;
    private String email;

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email;
    }
}

public class Main {

    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    private static final Pattern ID_PATTERN = Pattern.compile("^NUM\\d{3}$");

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]{3,30}$");

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> listStudents();
                case 3 -> searchStudent();
                case 4 -> removeStudent();
                case 5 -> {
                    System.out.println("Exiting the system...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please select 1-5.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n---------- Student Management System ----------");
        System.out.println("1. Add Student");
        System.out.println("2. List Students");
        System.out.println("3. Search Student");
        System.out.println("4. Remove Student");
        System.out.println("5. Exit");
    }

    private static void addStudent() {
        System.out.println("---------  Add Student  ----------");

        String id;
        while (true) {
            id = readLine("Enter Student ID (e.g., NUM123): ").trim();
            if (!ID_PATTERN.matcher(id).matches()) {
                System.out.println("Invalid ID format. Must match: " + ID_PATTERN.pattern());
                continue;
            }
            if (findById(id) != null) {
                System.out.println("ID already exists. Enter a different ID.");
                continue;
            }
            break;
        }

        String name;
        while (true) {
            name = readLine("Enter Student Name: ").trim();
            if (!NAME_PATTERN.matcher(name).matches()) {
                System.out.println("Invalid name. Only letters/spaces, 3-30 characters.");
                continue;
            }
            name = name.replaceAll("\\s+", " ");
            break;
        }

        String email;
        while (true) {
            email = readLine("Enter Student Email: ").trim();
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                System.out.println("Invalid email format. Try again.");
                continue;
            }
            break;
        }

        students.add(new Student(id, name, email));
        System.out.println("Student added successfully!");
    }

    private static void listStudents() {
        System.out.println("---------  List Students  ----------");
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void searchStudent() {
        System.out.println("---------  Search Student  ----------");
        String id = readLine("Enter Student ID to search: ").trim();

        Student found = findById(id);
        if (found == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student Found: " + found);
        }
    }

    private static void removeStudent() {
        System.out.println("---------  Remove Student  ----------");
        String id = readLine("Enter Student ID to remove: ").trim();

        boolean removed = false;
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.remove();
                removed = true;
                break;
            }
        }

        if (removed) System.out.println("Student removed successfully!");
        else System.out.println("Student not found.");
    }

    private static Student findById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
