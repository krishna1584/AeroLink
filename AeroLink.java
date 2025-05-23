import java.util.Scanner;
import java.util.HashMap;

/**
 * Main class for the AeroLink Airport Management System
 * This serves as the entry point and controller for the application
 */
public class AeroLink {
    private static Scanner scanner = new Scanner(System.in);
    private static FlightManager flightManager = new FlightManager();
    private static PassengerManager passengerManager = new PassengerManager();
    private static BookingManager bookingManager = new BookingManager();
    private static ServiceManager serviceManager = new ServiceManager();
    private static boolean isLoggedIn = false;
    private static HashMap<String, String> adminCredentials = new HashMap<>();

    public static void main(String[] args) {
        // Initialize admin credentials
        adminCredentials.put("admin", "admin123");

        System.out.println("\n===== Welcome to AeroLink Airport Management System =====");

        // Main program loop
        boolean exit = false;
        while (!exit) {
            // Check if logged in
            if (!isLoggedIn) {
                if (login()) {
                    isLoggedIn = true;
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Login failed. Please try again.");
                    continue;
                }
            }

            // Display main menu
            displayMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        flightManager.addFlight();
                        break;
                    case 2:
                        passengerManager.addPassenger();
                        break;
                    case 3:
                        bookingManager.bookFlight(flightManager, passengerManager);
                        break;
                    case 4:
                        serviceManager.requestGroundService();
                        break;
                    case 5:
                        flightManager.cancelFlight();
                        break;
                    case 6:
                        flightManager.viewAllFlights();
                        break;
                    case 7:
                        passengerManager.viewAllPassengers();
                        break;
                    case 8:
                        bookingManager.viewAllBookings();
                        break;
                    case 9:
                        flightManager.predictFlightDelay();
                        break;
                    case 10:
                        flightManager.updateFlightDetails();
                        break;
                    case 11:
                        passengerManager.updatePassengerDetails();
                        break;
                    case 12:
                        flightManager.deleteFlight();
                        break;
                    case 13:
                        passengerManager.deletePassenger();
                        break;
                    case 14:
                        flightManager.searchFlights();
                        break;
                    case 15:
                        passengerManager.searchPassengers();
                        break;
                    case 16:
                        System.out.println("Thank you for using AeroLink Airport Management System!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * Handles admin login functionality
     * @return true if login is successful, false otherwise
     */
    private static boolean login() {
        System.out.println("===== Admin Login =====");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        // Check if credentials match
        return adminCredentials.containsKey(username) &&
                adminCredentials.get(username).equals(password);
    }

    /**
     * Displays the main menu options
     */
    private static void displayMenu() {
        System.out.println("\n===== AeroLink Airport Management Menu =====");
        System.out.println("1. Add Flight");
        System.out.println("2. Add Passenger");
        System.out.println("3. Book Flight");
        System.out.println("4. Request Ground Service");
        System.out.println("5. Cancel Flight");
        System.out.println("6. View All Flights");
        System.out.println("7. View All Passengers");
        System.out.println("8. View All Bookings");
        System.out.println("9. Predict Flight Delay");
        System.out.println("10. Update Flight Details");
        System.out.println("11. Update Passenger Details");
        System.out.println("12. Delete Flight");
        System.out.println("13. Delete Passenger");
        System.out.println("14. Search Flights");
        System.out.println("15. Search Passengers");
        System.out.println("16. Exit");
        System.out.print("Enter your choice: ");
    }
}