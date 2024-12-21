package oops.Inheritance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Base class (Parent class)
public class Car {
    // Instance variables common to all cars
    String brand;
    String model;
    int year;

    // Logger instance for Car class
    public static final Logger logger = LoggerFactory.getLogger(Car.class);

    // Constructor for Car class
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        logger.info("Car created: {} {} ({})", brand, model, year);
    }

    // Method to display vehicle details
    public void displayCarDetails() {
        logger.info("Displaying details for {} {} ({})", brand, model, year);
    }

    // Method to start the car
    public void startVehicle() {
        logger.info("The car {} {} ({}) is starting...", brand, model, year);
    }

    // Method to stop the car
    public void stopVehicle() {
        logger.info("The car {} {} ({}) has stopped.", brand, model, year);
    }
    
    //method for turboboost
    public void turboBoost() {
        logger.info("Turbo Boost activated for Vehicle {}!", model);
    }
    
    //method for sport mode
    public void sportMode() {
        logger.info("Sport mode activated for Vehicle {}!", model);
    }

    // Main function
    public static void main(String[] args) {
        // Creating objects for Lamborghini and BMW
        Car lamborghini = new Lamborghini("Aventador", 2023);
        Car bmw = new BMW("M3", 2022);

        // Displaying car details and starting the cars
        lamborghini.displayCarDetails();
        lamborghini.startVehicle();
        lamborghini.turboBoost();  // method overriding here 

        bmw.displayCarDetails();
        bmw.startVehicle();
        bmw.sportMode();  // method overriding

        // Stopping the cars
        lamborghini.stopVehicle();
        bmw.stopVehicle();
    }
}

