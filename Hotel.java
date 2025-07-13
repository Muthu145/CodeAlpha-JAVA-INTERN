import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    private List<Room> rooms;
    private List<Booking> bookings;

    public Hotel() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        loadRooms();
        loadBookings();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> searchAvailableRooms(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public boolean bookRoom(String customerName, String category, Date checkIn, Date checkOut) {
        List<Room> availableRooms = searchAvailableRooms(category);
        if (!availableRooms.isEmpty()) {
            Room room = availableRooms.get(0);
            room.setAvailable(false);
            Booking booking = new Booking(customerName, room, checkIn, checkOut);
            bookings.add(booking);
            saveBookings();
            saveRooms();
            return true;
        }
        return false;
    }

    public boolean cancelBooking(String customerName, int roomNumber) {
        for (Booking booking : bookings) {
            if (booking.getCustomerName().equalsIgnoreCase(customerName) &&
                booking.getRoom().getRoomNumber() == roomNumber) {
                booking.getRoom().setAvailable(true);
                bookings.remove(booking);
                saveBookings();
                saveRooms();
                return true;
            }
        }
        return false;
    }

    public void viewBookings() {
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private void saveRooms() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("rooms.dat"))) {
            oos.writeObject(rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRooms() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("rooms.dat"))) {
            rooms = (List<Room>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file not found, initialize with default rooms
            rooms = new ArrayList<>();
            rooms.add(new Room(101, "Standard"));
            rooms.add(new Room(102, "Deluxe"));
            rooms.add(new Room(103, "Suite"));
            saveRooms();
        }
    }

    private void saveBookings() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("bookings.dat"))) {
            oos.writeObject(bookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBookings() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("bookings.dat"))) {
            bookings = (List<Booking>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            bookings = new ArrayList<>();
        }
    }
}
