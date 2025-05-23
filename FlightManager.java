import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

public class FlightManager {
    private ArrayList<Flight> flights;
    private HashMap<String, Flight> flightMap;
    private Scanner scanner;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");

    public FlightManager() {
        this.flights = new ArrayList<>();
        this.flightMap = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void addFlight() {
        try {
            System.out.println("\n===== Add New Flight =====");

            System.out.print("Enter Flight Name: ");
            String flightName = scanner.nextLine().trim();

            System.out.print("Enter Origin: ");
            String origin = scanner.nextLine().trim();

            System.out.print("Enter Destination: ");
            String destination = scanner.nextLine().trim();

            LocalDateTime departureTime = getDateTime("Enter Departure Time (HH:mm dd-MM-yyyy): ");
            LocalDateTime arrivalTime = getDateTime("Enter Arrival Time (HH:mm dd-MM-yyyy): ");

            if (arrivalTime.isBefore(departureTime)) {
                System.out.println("Error: Arrival time cannot be before departure time.");
                return;
            }

            System.out.print("Enter Capacity: ");
            int capacity = Integer.parseInt(scanner.nextLine().trim());

            if (capacity <= 0) {
                System.out.println("Error: Capacity must be greater than zero.");
                return;
            }

            System.out.print("Enter Flight Price: ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            Flight flight = new Flight(flightName, origin, destination, departureTime, arrivalTime, capacity, price);
            flights.add(flight);
            flightMap.put(flight.getFlightId(), flight);

            System.out.println("Flight added successfully!");
            System.out.println("Flight ID: " + flight.getFlightId());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numeric values.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use HH:mm dd-MM-yyyy");
        } catch (Exception e) {
            System.out.println("Error adding flight: " + e.getMessage());
        }
    }

    private LocalDateTime getDateTime(String prompt) throws DateTimeParseException {
        System.out.print(prompt);
        String dateTimeStr = scanner.nextLine().trim();
        return LocalDateTime.parse(dateTimeStr, formatter);
    }


    public void cancelFlight() {
        System.out.println("\n===== Cancel Flight =====");
        if (flights.isEmpty()) {
            System.out.println("No flights available to cancel.");
            return;
        }

        // Display all flights first
        System.out.println("\nAvailable Flights:");
        int count = 1;
        for (Flight flight : flights) {
            if (!flight.isCancelled()) {
                System.out.println(count + ". ID: " + flight.getFlightId() +
                        " | Flight: " + flight.getFlightName() +
                        " | From: " + flight.getOrigin() +
                        " | To: " + flight.getDestination());
                count++;
            }
        }

        if (count == 1) {
            System.out.println("No active flights available to cancel.");
            return;
        }

        System.out.print("\nEnter Flight ID to cancel: ");
        String flightId = scanner.nextLine().trim();
        Flight flight = flightMap.get(flightId);

        if (flight == null) {
            System.out.println("Flight not found with ID: " + flightId);
            return;
        }

        if (flight.isCancelled()) {
            System.out.println("This flight is already cancelled.");
            return;
        }

        flight.setCancelled(true);
        System.out.println("Flight cancelled successfully!");
    }

    public void deleteFlight() {
        System.out.println("\n===== Delete Flight =====");
        if (flights.isEmpty()) {
            System.out.println("No flights available to delete.");
            return;
        }

        // Display all flights first
        System.out.println("\nAvailable Flights:");
        int count = 1;
        for (Flight flight : flights) {
            System.out.println(count + ". ID: " + flight.getFlightId() +
                    " | Flight: " + flight.getFlightName() +
                    " | From: " + flight.getOrigin() +
                    " | To: " + flight.getDestination() +
                    " | Status: " + (flight.isCancelled() ? "Cancelled" : "Active"));
            count++;
        }

        System.out.print("\nEnter Flight ID to delete: ");
        String flightId = scanner.nextLine().trim();
        Flight flight = flightMap.get(flightId);

        if (flight == null) {
            System.out.println("Flight not found with ID: " + flightId);
            return;
        }

        flights.remove(flight);
        flightMap.remove(flightId);
        System.out.println("Flight deleted successfully!");
    }

    public void updateFlightDetails() {
        System.out.println("\n===== Update Flight Details =====");
        if (flights.isEmpty()) {
            System.out.println("No flights available to update.");
            return;
        }

        // Display all flights first
        System.out.println("\nAvailable Flights:");
        int count = 1;
        for (Flight flight : flights) {
            System.out.println(count + ". ID: " + flight.getFlightId() +
                    " | Flight: " + flight.getFlightName() +
                    " | From: " + flight.getOrigin() +
                    " | To: " + flight.getDestination() +
                    " | Status: " + (flight.isCancelled() ? "Cancelled" : "Active"));
            count++;
        }

        System.out.print("\nEnter Flight ID to update: ");
        String flightId = scanner.nextLine().trim();
        Flight flight = flightMap.get(flightId);

        if (flight == null) {
            System.out.println("Flight not found with ID: " + flightId);
            return;
        }

        System.out.println("Current flight details:");
        System.out.println(flight);

        try {
            System.out.println("\nEnter new details (leave blank to keep current value):");

            System.out.print("Flight Name [" + flight.getFlightName() + "]: ");
            String flightName = scanner.nextLine().trim();
            if (!flightName.isEmpty()) {
                flight.setFlightName(flightName);
            }

            System.out.print("Origin [" + flight.getOrigin() + "]: ");
            String origin = scanner.nextLine().trim();
            if (!origin.isEmpty()) {
                flight.setOrigin(origin);
            }

            System.out.print("Destination [" + flight.getDestination() + "]: ");
            String destination = scanner.nextLine().trim();
            if (!destination.isEmpty()) {
                flight.setDestination(destination);
            }

            System.out.print("Departure Time [" + flight.getDepartureTime().format(formatter) + "]: ");
            String departureStr = scanner.nextLine().trim();
            if (!departureStr.isEmpty()) {
                flight.setDepartureTime(LocalDateTime.parse(departureStr, formatter));
            }

            System.out.print("Arrival Time [" + flight.getArrivalTime().format(formatter) + "]: ");
            String arrivalStr = scanner.nextLine().trim();
            if (!arrivalStr.isEmpty()) {
                flight.setArrivalTime(LocalDateTime.parse(arrivalStr, formatter));
            }

            System.out.print("Capacity [" + flight.getCapacity() + "]: ");
            String capacityStr = scanner.nextLine().trim();
            if (!capacityStr.isEmpty()) {
                int newCapacity = Integer.parseInt(capacityStr);
                if (newCapacity < flight.getBookedSeats()) {
                    System.out.println("Error: New capacity cannot be less than the current booked seats.");
                    return;
                }
                flight.setCapacity(newCapacity);
            }

            System.out.print("Price [₹" + flight.getPrice() + "]: ");
            String priceStr = scanner.nextLine().trim();
            if (!priceStr.isEmpty()) {
                double newPrice = Double.parseDouble(priceStr);
                if (newPrice <= 0) {
                    System.out.println("Error: Price must be greater than zero.");
                    return;
                }
                flight.setPrice(newPrice);
            }

            System.out.println("Flight details updated successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numeric values.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use HH:mm dd-MM-yyyy");
        } catch (Exception e) {
            System.out.println("Error updating flight: " + e.getMessage());
        }
    }

    public void viewAllFlights() {
        System.out.println("\n===== All Flights =====");
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        int count = 1;
        for (Flight flight : flights) {
            System.out.println("\nFlight #" + count + ":");
            System.out.println(flight);
            System.out.println("------------------------------");
            count++;
        }
    }

    public void predictFlightDelay() {
        System.out.println("\n===== Predict Flight Delay =====");
        if (flights.isEmpty()) {
            System.out.println("No flights available to predict delays.");
            return;
        }

        System.out.print("Enter Flight ID to predict delay: ");
        String flightId = scanner.nextLine().trim();
        Flight flight = flightMap.get(flightId);

        if (flight == null) {
            System.out.println("Flight not found with ID: " + flightId);
            return;
        }

        Queue<String> delayFactors = new LinkedList<>();
        delayFactors.add("Weather conditions");
        delayFactors.add("Air traffic");
        delayFactors.add("Technical issues");
        delayFactors.add("Airport conditions");

        System.out.println("\nAnalyzing potential delay factors:");
        Random random = new Random();
        int totalDelay = 0;

        while (!delayFactors.isEmpty()) {
            String factor = delayFactors.poll();
            int factorDelay = random.nextInt(30);
            System.out.println("- " + factor + ": " + factorDelay + " minutes");
            totalDelay += factorDelay;
        }

        System.out.println("\nPredicted delay for flight " + flight.getFlightName() + " (" + flightId + "): " + totalDelay + " minutes");

        LocalDateTime newArrival = flight.getArrivalTime().plusMinutes(totalDelay);
        System.out.println("Original arrival: " + flight.getArrivalTime().format(formatter));
        System.out.println("Predicted arrival: " + newArrival.format(formatter));
    }

    public ArrayList<Flight> getAllFlights() {
        return new ArrayList<>(flights);
    }

    public Flight getFlightById(String flightId) {
        return flightMap.get(flightId);
    }

    public ArrayList<Flight> getSortedFlights(String attribute) {
        PriorityQueue<Flight> priorityQueue;

        switch (attribute.toLowerCase()) {
            case "departure":
                priorityQueue = new PriorityQueue<>();
                break;
            case "arrival":
                priorityQueue = new PriorityQueue<>(Comparator.comparing(Flight::getArrivalTime));
                break;
            case "name":
                priorityQueue = new PriorityQueue<>(Comparator.comparing(Flight::getFlightName));
                break;
            case "available":
                priorityQueue = new PriorityQueue<>(Comparator.comparing(Flight::getAvailableSeats).reversed());
                break;
            default:
                priorityQueue = new PriorityQueue<>();
        }

        priorityQueue.addAll(flights);
        ArrayList<Flight> sortedFlights = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            sortedFlights.add(priorityQueue.poll());
        }

        return sortedFlights;
    }

    class FlightSearchNode {
        Flight data;
        FlightSearchNode left, right;

        public FlightSearchNode(Flight data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public void searchFlights() {
        System.out.println("\n===== Search Flights =====");
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        System.out.print("Enter keyword (name/origin/destination): ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        if (keyword.isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return;
        }

        FlightSearchNode root = null;
        for (Flight flight : flights) {
            root = insertFlightNode(root, flight);
        }

        System.out.println("Search results:");
        if (!searchFlightTree(root, keyword)) {
            System.out.println("No matching flights found.");
        }
    }

    private FlightSearchNode insertFlightNode(FlightSearchNode root, Flight flight) {
        if (root == null) return new FlightSearchNode(flight);
        if (flight.getFlightName().compareToIgnoreCase(root.data.getFlightName()) < 0)
            root.left = insertFlightNode(root.left, flight);
        else
            root.right = insertFlightNode(root.right, flight);
        return root;
    }

    private boolean searchFlightTree(FlightSearchNode node, String keyword) {
        if (node == null) return false;
        boolean foundLeft = searchFlightTree(node.left, keyword);
        boolean foundCurrent = false;

        Flight f = node.data;
        if (f.getFlightName().toLowerCase().contains(keyword) ||
                f.getOrigin().toLowerCase().contains(keyword) ||
                f.getDestination().toLowerCase().contains(keyword)) {
            System.out.println(f);
            System.out.println("------------------------------");
            foundCurrent = true;
        }

        boolean foundRight = searchFlightTree(node.right, keyword);
        return foundLeft || foundCurrent || foundRight;
    }
}


