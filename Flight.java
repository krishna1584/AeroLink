import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Flight class to store flight information
 * This is a model class representing a flight in the system
 */
public class Flight implements Comparable<Flight> {
    private String flightId;
    private String flightName;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int capacity;
    private int bookedSeats;
    private boolean cancelled;
    private double price;  // ✅ Added price field

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");

    /**
     * Constructor for Flight class
     */
    public Flight(String flightName, String origin, String destination,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, int capacity, double price) {
        this.flightId = UUID.randomUUID().toString().substring(0, 8);
        this.flightName = flightName;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.capacity = capacity;
        this.bookedSeats = 0;
        this.cancelled = false;
        this.price = price;  // ✅ Initialize price
    }

    // Getters and setters
    public String getFlightId() {
        return flightId;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean bookSeat() {
        if (bookedSeats < capacity && !cancelled) {
            bookedSeats++;
            return true;
        }
        return false;
    }

    public boolean cancelBooking() {
        if (bookedSeats > 0) {
            bookedSeats--;
            return true;
        }
        return false;
    }

    public boolean hasAvailableSeats() {
        return bookedSeats < capacity && !cancelled;
    }

    public int getAvailableSeats() {
        return capacity - bookedSeats;
    }

    @Override
    public int compareTo(Flight other) {
        return this.departureTime.compareTo(other.departureTime);
    }

    @Override
    public String toString() {
        return "Flight ID: " + flightId + "\n" +
                "Flight Name: " + flightName + "\n" +
                "Origin: " + origin + "\n" +
                "Destination: " + destination + "\n" +
                "Departure: " + departureTime.format(formatter) + "\n" +
                "Arrival: " + arrivalTime.format(formatter) + "\n" +
                "Capacity: " + capacity + "\n" +
                "Booked Seats: " + bookedSeats + "\n" +
                "Available Seats: " + getAvailableSeats() + "\n" +
                "Price: ₹" + price + "\n" +  // ✅ Show price
                "Status: " + (cancelled ? "Cancelled" : "Active");
    }
}
