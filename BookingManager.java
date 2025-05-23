import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * BookingManager class to manage all booking-related operations
 * Uses various data structures:
 * - ArrayList for storage of bookings
 * - HashMap for quick booking lookup by ID
 * - Stack for tracking recent bookings
 * - Deque for processing booking requests
 */
public class BookingManager {
    private ArrayList<Booking> bookings;  // ArrayList for bookings storage
    private HashMap<String, Booking> bookingMap;  // HashMap for quick booking lookup by ID
    private Stack<Booking> recentBookings;  // Stack to track recent bookings
    private Deque<String> bookingRequests;  // Deque for booking requests
    private Scanner scanner;

    /**
     * Constructor initializes the data structures
     */
    public BookingManager() {
        this.bookings = new ArrayList<>();  // Dynamic array for storing all bookings
        this.bookingMap = new HashMap<>();  // HashMap for O(1) lookup by ID
        this.recentBookings = new Stack<>();  // Stack for maintaining history of recent bookings
        this.bookingRequests = new ArrayDeque<>();  // Deque for processing booking requests
        this.scanner = new Scanner(System.in);
    }

    /**
     * Book a flight for a passenger
     * Uses various data structures for efficient operations
     */
    public void bookFlight(FlightManager flightManager, PassengerManager passengerManager) {
        try {
            System.out.println("\n===== Book Flight =====");

            // Check if flights are available
            ArrayList<Flight> availableFlights = flightManager.getAllFlights();
            if (availableFlights.isEmpty()) {
                System.out.println("No flights available for booking.");
                return;
            }

            // Check if passengers are registered
            ArrayList<Passenger> registeredPassengers = passengerManager.getAllPassengers();
            if (registeredPassengers.isEmpty()) {
                System.out.println("No passengers registered. Please add a passenger first.");
                return;
            }

            // Display available flights with available seats
            System.out.println("\nAvailable Flights:");
            boolean hasAvailableFlights = false;

            for (Flight flight : availableFlights) {
                if (flight.hasAvailableSeats() && !flight.isCancelled()) {
                    System.out.println("ID: " + flight.getFlightId() +
                            " | Flight: " + flight.getFlightName() +
                            " | From: " + flight.getOrigin() +
                            " | To: " + flight.getDestination() +
                            " | Available Seats: " + flight.getAvailableSeats());
                    hasAvailableFlights = true;
                }
            }

            if (!hasAvailableFlights) {
                System.out.println("No flights with available seats.");
                return;
            }

            System.out.print("\nEnter Flight ID to book: ");
            String flightId = scanner.nextLine().trim();

            Flight flight = flightManager.getFlightById(flightId);
            if (flight == null) {
                System.out.println("Flight not found with ID: " + flightId);
                return;
            }

            if (!flight.hasAvailableSeats()) {
                System.out.println("No available seats on this flight.");
                return;
            }

            if (flight.isCancelled()) {
                System.out.println("This flight has been cancelled.");
                return;
            }

            // Display registered passengers
            System.out.println("\nRegistered Passengers:");
            for (Passenger passenger : registeredPassengers) {
                System.out.println("ID: " + passenger.getPassengerId() +
                        " | Name: " + passenger.getName() +
                        " | Passport: " + passenger.getPassportNumber());
            }

            System.out.print("\nEnter Passenger ID to book for: ");
            String passengerId = scanner.nextLine().trim();

            Passenger passenger = passengerManager.getPassengerById(passengerId);
            if (passenger == null) {
                System.out.println("Passenger not found with ID: " + passengerId);
                return;
            }

            // Check if passenger already has a booking for this flight
            for (Booking booking : bookings) {
                if (booking.getPassengerId().equals(passengerId) &&
                        booking.getFlightId().equals(flightId)) {
                    System.out.println("This passenger already has a booking for this flight.");
                    return;
                }
            }

            // Add booking request to the deque
            bookingRequests.add(passengerId + ":" + flightId);

            // Process booking request
            String request = bookingRequests.poll();
            String[] parts = request.split(":");
            String reqPassengerId = parts[0];
            String reqFlightId = parts[1];

            // Create booking
            Booking booking = new Booking(reqPassengerId, reqFlightId);

            // Update flight's booked seats
            if (flight.bookSeat()) {
                // Add booking to data structures
                bookings.add(booking);
                bookingMap.put(booking.getBookingId(), booking);
                recentBookings.push(booking);  // Add to recent bookings stack

                System.out.println("Flight booked successfully!");
                System.out.println("Booking ID: " + booking.getBookingId());
            } else {
                System.out.println("Failed to book flight. No available seats.");
            }
        } catch (Exception e) {
            System.out.println("Error booking flight: " + e.getMessage());
        }
    }

    /**
     * View all bookings
     * Uses ArrayList for display
     */
    public void viewAllBookings() {
        System.out.println("\n===== All Bookings =====");

        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        int count = 1;
        for (Booking booking : bookings) {
            System.out.println("\nBooking #" + count + ":");
            System.out.println(booking);
            System.out.println("------------------------------");
            count++;
        }
    }

    /**
     * Get the most recent booking
     * Uses Stack to retrieve the most recent booking
     * @return the most recent Booking object, or null if none
     */
    public Booking getMostRecentBooking() {
        if (recentBookings.isEmpty()) {
            return null;
        }
        return recentBookings.peek();
    }

    /**
     * Get all bookings for a specific passenger
     * @param passengerId the ID of the passenger
     * @return ArrayList of bookings for the passenger
     */
    public ArrayList<Booking> getBookingsForPassenger(String passengerId) {
        ArrayList<Booking> passengerBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getPassengerId().equals(passengerId)) {
                passengerBookings.add(booking);
            }
        }

        return passengerBookings;
    }

    /**
     * Get all bookings for a specific flight
     * @param flightId the ID of the flight
     * @return ArrayList of bookings for the flight
     */
    public ArrayList<Booking> getBookingsForFlight(String flightId) {
        ArrayList<Booking> flightBookings = new ArrayList<>();

        for (Booking booking : bookings) {
            if (booking.getFlightId().equals(flightId)) {
                flightBookings.add(booking);
            }
        }

        return flightBookings;
    }
}