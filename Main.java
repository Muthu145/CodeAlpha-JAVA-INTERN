import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Hotel hotel = new Hotel();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    searchRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    hotel.viewBookings();
                    break;
                case 5:
                    System.out.println("Thank you for using the Hotel Reservation System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private static void searchRooms() {
        System.out.print("Enter room category (Standard/Deluxe/Suite): ");
        String category = scanner.nextLine();
        List<Room> availableRooms = hotel.searchAvailableRooms(category);
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms in this category.");
        } else {
            System.out.println("Available Rooms:");
            for (Room room : availableRooms) {
                System.out.println(room);
            }
        }
    }

    private static void bookRoom() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter room category (Standard/Deluxe/Suite): ");
        String category = scanner.nextLine();
        System.out.print("Enter check-in date (dd-MM-yyyy): ");
        String checkInStr = scanner.nextLine();
        System.out.print("Enter check-out date (dd-MM-yyyy): ");
        String checkOutStr = scanner.nextLine();
        try {
            Date checkIn = sdf.parse(checkInStr);
            Date checkOut = sdf.parse(checkOutStr);
            boolean success = hotel.bookRoom(name, category, checkIn, checkOut);
            if (success) {
                System.out.println("Room booked successfully!");
            } else {
                System.out.println("No available rooms in this category.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    private static void cancelBooking() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline
        boolean success = hotel.cancelBooking(name, roomNumber);
        if (success) {
            System.out.println("Booking canceled successfully.");
        } else {
            System.out.println("Booking not found.");
        }
    }
}
