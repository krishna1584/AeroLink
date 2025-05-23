import java.util.UUID;

/**
 * Passenger class to store passenger information
 * This is a model class representing a passenger in the system
 */
public class Passenger {
    private String passengerId;
    private String name;
    private int age;
    private String passportNumber;

    /**
     * Constructor for Passenger class
     */
    public Passenger(String name, int age, String passportNumber) {
        this.passengerId = UUID.randomUUID().toString().substring(0, 8);
        this.name = name;
        this.age = age;
        this.passportNumber = passportNumber;
    }

    // Getters and setters
    public String getPassengerId() {
        return passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    /**
     * String representation of the passenger
     */
    @Override
    public String toString() {
        return "Passenger ID: " + passengerId + "\n" +
                "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Passport Number: " + passportNumber;
    }
}