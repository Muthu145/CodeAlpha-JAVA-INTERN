import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable {
    private String customerName;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;

    public Booking(String customerName, Room room, Date checkInDate, Date checkOutDate) {
        this.customerName = customerName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "customerName='" + customerName + '\'' +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
