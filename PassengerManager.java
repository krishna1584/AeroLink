import java.util.*;

public class PassengerManager {
    private ArrayList<Passenger> passengers;
    private HashMap<String, Passenger> passengerMap;
    private Scanner scanner;

    public PassengerManager() {
        this.passengers = new ArrayList<>();
        this.passengerMap = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    // Add Passenger - Uses ArrayList and HashMap
    public void addPassenger() {
        try {
            System.out.println("\n===== Add New Passenger =====");

            System.out.print("Enter Passenger Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());
            if (age <= 0) {
                System.out.println("Error: Age must be greater than zero.");
                return;
            }

            System.out.print("Enter Passport Number: ");
            String passportNumber = scanner.nextLine().trim();

            // Check if passport number already exists
            for (Passenger p : passengers) {
                if (p.getPassportNumber().equalsIgnoreCase(passportNumber)) {
                    System.out.println("Error: A passenger with this passport number already exists.");
                    return;
                }
            }

            Passenger passenger = new Passenger(name, age, passportNumber);
            passengers.add(passenger);
            passengerMap.put(passenger.getPassengerId(), passenger);

            System.out.println("Passenger added successfully!");
            System.out.println("Passenger ID: " + passenger.getPassengerId());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid age.");
        } catch (Exception e) {
            System.out.println("Error adding passenger: " + e.getMessage());
        }
    }

    // Delete Passenger - Uses HashMap and ArrayList
    public void deletePassenger() {
        System.out.println("\n===== Delete Passenger =====");

        if (passengers.isEmpty()) {
            System.out.println("No passengers available to delete.");
            return;
        }

        // Display all passengers first
        System.out.println("\nRegistered Passengers:");
        int count = 1;
        for (Passenger passenger : passengers) {
            System.out.println(count + ". ID: " + passenger.getPassengerId() +
                    " | Name: " + passenger.getName() +
                    " | Passport: " + passenger.getPassportNumber());
            count++;
        }

        System.out.print("\nEnter Passenger ID to delete: ");
        String passengerId = scanner.nextLine().trim();

        Passenger passenger = passengerMap.get(passengerId);
        if (passenger == null) {
            System.out.println("Passenger not found with ID: " + passengerId);
            return;
        }

        passengers.remove(passenger);
        passengerMap.remove(passengerId);

        System.out.println("Passenger deleted successfully!");
    }

    // View All Passengers - Uses Collections.sort
    public void viewAllPassengers() {
        System.out.println("\n===== All Passengers =====");

        if (passengers.isEmpty()) {
            System.out.println("No passengers registered.");
            return;
        }

        // Use the original ArrayList order instead of sorting
        int count = 1;
        for (Passenger passenger : passengers) {
            System.out.println("\nPassenger #" + count + ":");
            System.out.println(passenger);
            System.out.println("------------------------------");
            count++;
        }
    }

    // Update Passenger Details - Uses HashMap
    public void updatePassengerDetails() {
        System.out.println("\n===== Update Passenger Details =====");

        if (passengers.isEmpty()) {
            System.out.println("No passengers available to update.");
            return;
        }

        // Display all passengers first
        System.out.println("\nRegistered Passengers:");
        int count = 1;
        for (Passenger passenger : passengers) {
            System.out.println(count + ". ID: " + passenger.getPassengerId() +
                    " | Name: " + passenger.getName() +
                    " | Passport: " + passenger.getPassportNumber());
            count++;
        }

        System.out.print("\nEnter Passenger ID to update: ");
        String passengerId = scanner.nextLine().trim();

        Passenger passenger = passengerMap.get(passengerId);
        if (passenger == null) {
            System.out.println("Passenger not found with ID: " + passengerId);
            return;
        }

        System.out.println("Current passenger details:");
        System.out.println(passenger);

        try {
            System.out.println("\nEnter new details (leave blank to keep current value):");

            System.out.print("Name [" + passenger.getName() + "]: ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                passenger.setName(name);
            }

            System.out.print("Age [" + passenger.getAge() + "]: ");
            String ageStr = scanner.nextLine().trim();
            if (!ageStr.isEmpty()) {
                int age = Integer.parseInt(ageStr);
                if (age <= 0) {
                    System.out.println("Error: Age must be greater than zero.");
                    return;
                }
                passenger.setAge(age);
            }

            System.out.print("Passport Number [" + passenger.getPassportNumber() + "]: ");
            String passportNumber = scanner.nextLine().trim();
            if (!passportNumber.isEmpty()) {
                for (Passenger p : passengers) {
                    if (p != passenger && p.getPassportNumber().equalsIgnoreCase(passportNumber)) {
                        System.out.println("Error: Another passenger with this passport number already exists.");
                        return;
                    }
                }
                passenger.setPassportNumber(passportNumber);
            }

            System.out.println("Passenger details updated successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid age.");
        } catch (Exception e) {
            System.out.println("Error updating passenger: " + e.getMessage());
        }
    }
    // Search Passenger - Uses LinkedList
    public LinkedList<Passenger> searchPassengers(String searchTerm) {
        LinkedList<Passenger> results = new LinkedList<>();
        for (Passenger passenger : passengers) {
            if (passenger.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    passenger.getPassportNumber().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(passenger);
            }
        }
        return results;
    }

    // Get All Passengers - Used by booking system
    public ArrayList<Passenger> getAllPassengers() {
        return new ArrayList<>(passengers);
    }

    // Get passenger by ID
    public Passenger getPassengerById(String passengerId) {
        return passengerMap.get(passengerId);
    }
    class PassengerSearchNode {
        Passenger data;
        PassengerSearchNode left, right;

        public PassengerSearchNode(Passenger data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    public void searchPassengers() {
        System.out.println("\n===== Search Passengers =====");
        if (passengers.isEmpty()) {
            System.out.println("No passengers available.");
            return;
        }

        System.out.print("Enter keyword (name/passport): ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        if (keyword.isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return;
        }

        PassengerSearchNode root = null;
        for (Passenger passenger : passengers) {
            root = insertPassengerNode(root, passenger);
        }

        System.out.println("Search results:");
        if (!searchPassengerTree(root, keyword)) {
            System.out.println("No matching passengers found.");
        }
    }

    private PassengerSearchNode insertPassengerNode(PassengerSearchNode root, Passenger passenger) {
        if (root == null) return new PassengerSearchNode(passenger);
        if (passenger.getName().compareToIgnoreCase(root.data.getName()) < 0)
            root.left = insertPassengerNode(root.left, passenger);
        else
            root.right = insertPassengerNode(root.right, passenger);
        return root;
    }

    private boolean searchPassengerTree(PassengerSearchNode node, String keyword) {
        if (node == null) return false;
        boolean foundLeft = searchPassengerTree(node.left, keyword);
        boolean foundCurrent = false;

        Passenger p = node.data;
        if (p.getName().toLowerCase().contains(keyword) ||
                p.getPassportNumber().toLowerCase().contains(keyword)) {
            System.out.println(p);
            System.out.println("------------------------------");
            foundCurrent = true;
        }

        boolean foundRight = searchPassengerTree(node.right, keyword);
        return foundLeft || foundCurrent || foundRight;
    }
}
