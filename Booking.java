import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Booking class to store booking information
 * This is a model class representing a flight booking in the system
 */
public class Booking {
    private String bookingId;
    private String passengerId;
    private String flightId;
    private LocalDateTime bookingTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for Booking class
     */
    public Booking(String passengerId, String flightId) {
        this.bookingId = UUID.randomUUID().toString().substring(0, 8);
        this.passengerId = passengerId;
        this.flightId = flightId;
        this.bookingTime = LocalDateTime.now();
    }

    // Getters and setters
    public String getBookingId() {
        return bookingId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getFlightId() {
        return flightId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    /**
     * String representation of the booking
     */
    @Override
    public String toString() {
        return "Booking ID: " + bookingId + "\n" +
                "Passenger ID: " + passengerId + "\n" +
                "Flight ID: " + flightId + "\n" +
                "Booking Time: " + bookingTime.format(formatter);
    }
}