import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.List;

/**
 * ServiceManager class to manage all ground service-related operations
 * Uses various data structures:
 * - ArrayList for storage of services
 * - HashMap for quick service lookup by ID
 * - PriorityQueue for processing services by priority
 * - List for service types
 */
public class ServiceManager {
    private ArrayList<Service> services;  // ArrayList for services storage
    private HashMap<String, Service> serviceMap;  // HashMap for quick service lookup by ID
    private PriorityQueue<Service> servicePriorityQueue;  // PriorityQueue for service priority
    private List<String> serviceTypes;  // List of service types
    private Scanner scanner;

    /**
     * Constructor initializes the data structures
     */
    public ServiceManager() {
        this.services = new ArrayList<>();  // Dynamic array for storing all services
        this.serviceMap = new HashMap<>();  // HashMap for O(1) lookup by ID
        this.servicePriorityQueue = new PriorityQueue<>();  // PriorityQueue ordered by natural ordering (priority)
        this.serviceTypes = new ArrayList<>();  // List for service types
        this.scanner = new Scanner(System.in);

        // Initialize service types
        serviceTypes.add("Baggage Handling");
        serviceTypes.add("Aircraft Cleaning");
        serviceTypes.add("Catering");
        serviceTypes.add("Refueling");
        serviceTypes.add("Maintenance");
    }

    /**
     * Request a ground service
     * Uses various data structures for efficient operations
     */
    public void requestGroundService() {
        try {
            System.out.println("\n===== Request Ground Service =====");

            // Display available service types
            System.out.println("\nAvailable Service Types:");
            for (int i = 0; i < serviceTypes.size(); i++) {
                System.out.println((i + 1) + ". " + serviceTypes.get(i));
            }

            System.out.print("\nEnter Service Type: ");
            String serviceType = scanner.nextLine().trim();

            System.out.print("Enter Description: ");
            String description = scanner.nextLine().trim();

            System.out.print("Enter Location (Gate/Terminal): ");
            String location = scanner.nextLine().trim();

            System.out.print("Enter Priority (1-5, lower number means higher priority): ");
            int priority = Integer.parseInt(scanner.nextLine().trim());

            if (priority < 1 || priority > 5) {
                System.out.println("Error: Priority must be between 1 and 5.");
                return;
            }

            Service service = new Service(serviceType, description, location, priority);

            // Add to data structures
            services.add(service);
            serviceMap.put(service.getServiceId(), service);
            servicePriorityQueue.add(service);  // Add to priority queue

            System.out.println("Service requested successfully!");
            System.out.println("Service ID: " + service.getServiceId());

            // Display next service to be processed based on priority
            displayNextServiceInQueue();
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid priority number.");
        } catch (Exception e) {
            System.out.println("Error requesting service: " + e.getMessage());
        }
    }

    /**
     * Display the next service to be processed based on priority
     * Uses PriorityQueue to determine the highest priority service
     */
    private void displayNextServiceInQueue() {
        if (!servicePriorityQueue.isEmpty()) {
            System.out.println("\nNext service to be processed (highest priority):");

            // Peek at the highest priority service without removing it
            Service nextService = servicePriorityQueue.peek();

            System.out.println("Service ID: " + nextService.getServiceId());
            System.out.println("Type: " + nextService.getServiceType());
            System.out.println("Priority: " + nextService.getPriority());
            System.out.println("Status: " + (nextService.isCompleted() ? "Completed" : "Pending"));
        }
    }

    /**
     * View all services
     * Uses ArrayList for display
     */
    public void viewAllServices() {
        System.out.println("\n===== All Ground Services =====");

        if (services.isEmpty()) {
            System.out.println("No services available.");
            return;
        }

        int count = 1;
        for (Service service : services) {
            System.out.println("\nService #" + count + ":");
            System.out.println(service);
            System.out.println("------------------------------");
            count++;
        }
    }

    /**
     * Complete a service
     * Updates service status and removes from priority queue
     */
    public void completeService() {
        System.out.println("\n===== Complete Service =====");

        if (services.isEmpty()) {
            System.out.println("No services available to complete.");
            return;
        }

        System.out.print("Enter Service ID to mark as completed: ");
        String serviceId = scanner.nextLine().trim();

        Service service = serviceMap.get(serviceId);
        if (service == null) {
            System.out.println("Service not found with ID: " + serviceId);
            return;
        }

        if (service.isCompleted()) {
            System.out.println("This service is already marked as completed.");
            return;
        }

        service.setCompleted(true);

        // Rebuild priority queue without the completed service
        PriorityQueue<Service> newQueue = new PriorityQueue<>();
        for (Service s : services) {
            if (!s.isCompleted()) {
                newQueue.add(s);
            }
        }
        servicePriorityQueue = newQueue;

        System.out.println("Service marked as completed successfully!");

        // Display next service to be processed based on priority
        displayNextServiceInQueue();
    }
}