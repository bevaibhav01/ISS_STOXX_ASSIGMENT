package oops.polymorphism;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Base class (Parent class)
class Vehicle {
    // Logger instance
    protected static final Logger logger = LoggerFactory.getLogger(Vehicle.class);

    // Method to start the vehicle (overridden by subclasses)
    public void start() {
        logger.info("The vehicle is starting...");
    }

    // Method to stop the vehicle (common for all vehicles)
    public void stop() {
        logger.info("The vehicle has stopped.");
    }
}

// Subclass: Car
class Car extends Vehicle {
	
	//overriding vehicle starting method
    @Override
    public void start() {
        logger.info("The car is starting with a key ignition system.");
    }

    public void playMusic() {
        logger.info("Playing music in the car...");
    }
}

// Subclass: Bike
class Bike extends Vehicle {
    @Override
    public void start() {
        logger.info("The bike is starting with a kick start or self-start system.");
    }

    public void doWheelie() {
        logger.info("The bike is performing a wheelie!");
    }
}

// Subclass: Truck
class Truck extends Vehicle {
    @Override
    public void start() {
        logger.info("The truck is starting with a heavy-duty diesel engine.");
    }

    public void loadCargo() {
        logger.info("The truck is loading cargo...");
    }
}

// Main class to demonstrate method overriding
public class MethodOverridingDemo {
    //private static final Logger logger = LoggerFactory.getLogger(MethodOverridingDemo.class);

    public static void main(String[] args) {
        // Array of vehicles to demonstrate runtime polymorphism
        Vehicle[] vehicles = {new Car(), new Bike(), new Truck()};

        // Iterate through each vehicle and start them
        for (Vehicle vehicle : vehicles) {
            vehicle.start(); // Calls the overridden start() method of each subclass
            vehicle.stop();  // Calls the common stop() method
        }

        // Demonstrate subclass-specific behavior
        Car car = new Car();
        car.start();
        car.playMusic();

        Bike bike = new Bike();
        bike.start();
        bike.doWheelie();

        Truck truck = new Truck();
        truck.start();
        truck.loadCargo();
    }
}
