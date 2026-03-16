import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class HotelManagement {

    static Pattern NUMBER_FROM_1 = Pattern.compile("^[1-9]\\d*$");
    static Pattern OPTIONS_5 = Pattern.compile("^[1-5]$");
    static Pattern HOTELS_ID_VALIDATION = Pattern.compile("^(0|[1-9]\\d*)$");

    static int validateInteger(Scanner sc, String msg, Pattern p) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();
            if (p.matcher(input).matches()) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Invalid input! Try again.");
            }
        }
    }

    static void showHotels(boolean[][] rooms) {
        System.out.println("\n---------- Display All Hotel Information ----------");
        System.out.println("Id   Rooms Available Unavailable");
        for (int i = 0; i < rooms.length; i++) {
            int available = 0;
            for (boolean room : rooms[i]) {
                if (room) available++;
            }
            int unavailable = rooms[i].length - available;
            System.out.println((i + 1) + "    " + rooms[i].length + "   " + available + "       " + unavailable);
        }
    }

    static void showHotelDetails(boolean[][] rooms, int hotelId) {
        System.out.println("\n---------- Display Hotel Information ----------");
        boolean[] hotelRooms = rooms[hotelId - 1];
        for (int i = 0; i < hotelRooms.length; i++) {
            System.out.print((hotelRooms[i] ? "(+)" : "(-)") + " " + (i + 1) + "   ");
            if ((i + 1) % 5 == 0) System.out.println();
        }
        System.out.println("\n( - ):Unavailable   ( + ):Available");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("------------- Setting up Hotel -------------");
        int hotelCount = validateInteger(sc, "Enter number of Hotels: ", NUMBER_FROM_1);
        int roomCount = validateInteger(sc, "Enter number of Rooms per Hotel: ", NUMBER_FROM_1);

        boolean[][] rooms = new boolean[hotelCount][roomCount];
        for (boolean[] hotel : rooms) {
            Arrays.fill(hotel, true);
        }

        while (true) {
            System.out.println("\n------------- Hotel Management System -------------");
            System.out.println("1- Check Hotel");
            System.out.println("2- Book Room");
            System.out.println("3- Cancel Booking");
            System.out.println("4- Reset Hotel");
            System.out.println("5- Exit");
            System.out.println("---------------------------------------------------");

            int choice = validateInteger(sc, "-> Choose option (1-5): ", OPTIONS_5);

            switch (choice) {
                case 1 -> {
                    showHotels(rooms);
                    int hotelId = validateInteger(sc, "-> Enter 0 to back or Hotel Id to see details: ", HOTELS_ID_VALIDATION);
                    if (hotelId > 0) showHotelDetails(rooms, hotelId);
                }
                case 2 -> {
                    int hotelId = validateInteger(sc, "Enter Hotel Id: ", NUMBER_FROM_1);
                    int roomNo = validateInteger(sc, "Enter Room number to book: ", NUMBER_FROM_1);
                    if (!rooms[hotelId - 1][roomNo - 1]) {
                        System.out.println("Room already booked!");
                    } else {
                        rooms[hotelId - 1][roomNo - 1] = false;
                        System.out.println("Room booked successfully!");
                    }
                }
                case 3 -> {
                    int hotelId = validateInteger(sc, "Enter Hotel Id: ", NUMBER_FROM_1);
                    int roomNo = validateInteger(sc, "Enter Room number to cancel: ", NUMBER_FROM_1);
                    if (rooms[hotelId - 1][roomNo - 1]) {
                        System.out.println("Room is already available!");
                    } else {
                        rooms[hotelId - 1][roomNo - 1] = true;
                        System.out.println("Room booking canceled!");
                    }
                }
                case 4 -> {
                    int hotelId = validateInteger(sc, "Enter Hotel Id: ", NUMBER_FROM_1);
                    Arrays.fill(rooms[hotelId - 1], true);
                    System.out.println("Hotel reset successfully!");
                }
                case 5 -> {
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                }
            }
        }
    }
}