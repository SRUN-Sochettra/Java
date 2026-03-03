import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

class Author {
    private String name;
    private String yearActive;

    public Author() {}

    public Author(String name, String yearActive) {
        this.name = name;
        this.yearActive = yearActive;
    }

    public String getName() {
        return name;
    }

    public String getYearActive() {
        return yearActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearActive(String yearActive) {
        this.yearActive = yearActive;
    }

    @Override
    public String toString() {
        return name + " (" + yearActive + ")";
    }
}

class Book {
    private int id;
    private String title;
    private Author author;
    private int publishedYear;
    private boolean available;

    public Book() {}

    public Book(int id, String title, Author author, int publishedYear, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class InputUtil {
    private static final Pattern INT_1_TO_6 = Pattern.compile("^[1-6]$");
    private static final Pattern POSITIVE_INT = Pattern.compile("^[1-9]\\d*$");

    public static int readMenuChoice(Scanner sc, String msg) {
        return readInt(sc, msg, INT_1_TO_6, "Invalid option! Choose 1-6.");
    }

    public static int readPositiveInt(Scanner sc, String msg) {
        return readInt(sc, msg, POSITIVE_INT, "Invalid number! Try again.");
    }

    private static int readInt(Scanner sc, String msg, Pattern pattern, String err) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (pattern.matcher(s).matches()) {
                return Integer.parseInt(s);
            }
            System.out.println(err);
        }
    }

    public static String readNonEmptyLine(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty! Try again.");
        }
    }

    public static void pressEnterToContinue(Scanner sc) {
        System.out.print("Press \"ENTER\" to continue...");
        sc.nextLine();
    }
}

class Library {
    private final String name;
    private final String address;

    private Book[] books;
    private int size;
    private int nextId;

    private final CellStyle headerStyle = new CellStyle(CellStyle.HorizontalAlign.center);
    private final CellStyle cellStyle = new CellStyle(CellStyle.HorizontalAlign.center,
            CellStyle.AbbreviationStyle.crop,
            CellStyle.NullStyle.emptyString);

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
        this.books = new Book[10];
        this.size = 0;
        this.nextId = 1;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void addBook(Scanner sc) {
        System.out.println("========= ADD BOOK INFO =========");

        int id = nextId++;
        System.out.println("=> Book ID : " + id);

        String title = InputUtil.readNonEmptyLine(sc, "=> Enter Book's Name : ");
        String authorName = InputUtil.readNonEmptyLine(sc, "=> Enter Book Author Name: ");
        String authorYearActive = InputUtil.readNonEmptyLine(sc, "=> Enter Author Year Active: ");
        int publishedYear = InputUtil.readPositiveInt(sc, "=> Enter Published Year : ");

        Author author = new Author(authorName, authorYearActive);
        Book book = new Book(id, title, author, publishedYear, true);

        ensureCapacity();
        books[size++] = book;

        System.out.println("Book is added successfully");
    }

    public void showAllBooks() {
        System.out.println("========= ALL BOOKS INFO =========");

        if (size == 0) {
            System.out.println("No Book Available");
            return;
        }

        Table table = buildBookTable();
        for (int i = 0; i < size; i++) {
            addBookRow(table, books[i]);
        }
        System.out.println(table.render());
    }

    public void showAvailableBooks() {
        System.out.println("========= AVAILABLE BOOKS INFO =========");

        if (size == 0) {
            System.out.println("No Book Available");
            return;
        }

        Table table = buildBookTable();
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (books[i].isAvailable()) {
                addBookRow(table, books[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Book Available");
            return;
        }

        System.out.println(table.render());
    }

    public void borrowBook(int id) {
        System.out.println("========= BORROW BOOK INFO =========");

        Book b = findById(id);
        if (b == null) {
            System.out.println("Book ID : " + id + " not Exist...");
            return;
        }
        if (!b.isAvailable()) {
            System.out.println("Book ID : " + id + " is already borrowed...");
            return;
        }

        b.setAvailable(false);

        System.out.println("Book ID : " + b.getId());
        System.out.println("Book Title : " + b.getTitle());
        System.out.println("Book Author : " + b.getAuthor());
        System.out.println("Published Year : " + b.getPublishedYear() + " is borrowed successfully...");
    }

    public void returnBook(int id) {
        System.out.println("========= RETURN BOOK INFO =========");

        Book b = findById(id);
        if (b == null) {
            System.out.println("Book ID : " + id + " not Exist...");
            return;
        }
        if (b.isAvailable()) {
            System.out.println("Book ID : " + id + " is failed to return...");
            return;
        }

        b.setAvailable(true);

        System.out.println("Book ID : " + b.getId());
        System.out.println("Book Title : " + b.getTitle());
        System.out.println("Book Author : " + b.getAuthor());
        System.out.println("Published Year : " + b.getPublishedYear() + " is returned successfully...");
    }

    private void ensureCapacity() {
        if (size >= books.length) {
            Book[] newArr = new Book[books.length * 2];
            for (int i = 0; i < books.length; i++) newArr[i] = books[i];
            books = newArr;
        }
    }

    private Book findById(int id) {
        for (int i = 0; i < size; i++) {
            if (books[i].getId() == id) return books[i];
        }
        return null;
    }

    private Table buildBookTable() {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);

        table.setColumnWidth(0, 4, 6);   // ID
        table.setColumnWidth(1, 12, 30); // TITLE
        table.setColumnWidth(2, 12, 30); // AUTHOR
        table.setColumnWidth(3, 10, 14); // PUBLISHED
        table.setColumnWidth(4, 10, 14); // STATUS

        table.addCell("ID", headerStyle);
        table.addCell("TITLE", headerStyle);
        table.addCell("AUTHOR", headerStyle);
        table.addCell("PUBLISHED", headerStyle);
        table.addCell("STATUS", headerStyle);

        return table;
    }

    private void addBookRow(Table table, Book b) {
        String status = b.isAvailable() ? "AVAILABLE" : "UNAVAILABLE";
        table.addCell(String.valueOf(b.getId()), cellStyle);
        table.addCell(b.getTitle(), cellStyle);
        table.addCell(b.getAuthor().toString(), cellStyle);
        table.addCell(String.valueOf(b.getPublishedYear()), cellStyle);
        table.addCell(status, cellStyle);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        String formattedDate = ZonedDateTime.now(ZoneId.of("Asia/Phnom_Penh")).format(formatter);

        System.out.println("========= SET UP LIBRARY =========");
        String libraryName = InputUtil.readNonEmptyLine(scanner, "=> Enter Library's Name : ");
        String libraryAddress = InputUtil.readNonEmptyLine(scanner, "=> Enter Library's Address : ");

        System.out.println("\"" + libraryName + "\" Library is already created in \"" +
                libraryAddress + "\" address successfully on " + formattedDate);

        Library library = new Library(libraryName, libraryAddress);

        while (true) {
            System.out.println("\n========= " + libraryName + " ," + libraryAddress.toUpperCase() + " =========");
            System.out.println("1- Add Book");
            System.out.println("2- Show All Books");
            System.out.println("3- Show Available Books");
            System.out.println("4- Borrow Book");
            System.out.println("5- Return Book");
            System.out.println("6- Exit");
            System.out.println("-----------------------------------------");

            int choice = InputUtil.readMenuChoice(scanner, "=> Choose option(1-6) : ");

            switch (choice) {
                case 1 -> library.addBook(scanner);
                case 2 -> {
                    library.showAllBooks();
                    InputUtil.pressEnterToContinue(scanner);
                }
                case 3 -> {
                    library.showAvailableBooks();
                    InputUtil.pressEnterToContinue(scanner);
                }
                case 4 -> {
                    int id = InputUtil.readPositiveInt(scanner, "=> Enter Book ID to Borrow : ");
                    library.borrowBook(id);
                }
                case 5 -> {
                    int id = InputUtil.readPositiveInt(scanner, "=> Enter Book ID to Return : ");
                    library.returnBook(id);
                }
                case 6 -> {
                    System.out.println("(^-^) Good Bye! (^-^)");
                    scanner.close();
                    return;
                }
            }
        }
    }
}
