import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service class to store ground service information
 * This is a model class representing a ground service request in the system
 */
public class Service implements Comparable<Service> {
    private String serviceId;
    private String serviceType;
    private String description;
    private String location;
    private LocalDateTime requestTime;
    private boolean completed;
    private int priority;  // Lower number means higher priority
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for Service class
     */
    public Service(String serviceType, String description, String location, int priority) {
        this.serviceId = UUID.randomUUID().toString().substring(0, 8);
        this.serviceType = serviceType;
        this.description = description;
        this.location = location;
        this.requestTime = LocalDateTime.now();
        this.completed = false;
        this.priority = priority;
    }

    // Getters and setters
    public String getServiceId() {
        return serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Implementation of Comparable interface to allow sorting of services by priority
     */
    @Override
    public int compareTo(Service other) {
        return Integer.compare(this.priority, other.priority);
    }

    /**
     * String representation of the service
     */
    @Override
    public String toString() {
        return "Service ID: " + serviceId + "\n" +
                "Type: " + serviceType + "\n" +
                "Description: " + description + "\n" +
                "Location: " + location + "\n" +
                "Request Time: " + requestTime.format(formatter) + "\n" +
                "Priority: " + priority + "\n" +
                "Status: " + (completed ? "Completed" : "Pending");
    }
}