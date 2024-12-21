package oops.Inheritance;

// Lamborghini class (Subclass of Car)
class Lamborghini extends Car {
    // Constructor for Lamborghini
    public Lamborghini(String model, int year) {
        super("Lamborghini", model, year);  // Call the parent class constructor
        logger.info("Lamborghini {} ({}) created.", model, year);
    }

    // overriding parent method by its own turboboost
    @Override
    public void turboBoost() {
        Car.logger.info("Turbo Boost activated for Lamborghini {}!", model);
    }

    @Override
    public void startVehicle() {
        super.startVehicle();  // Calling the parent class startVehicle method
        Car.logger.info("The Lamborghini {} is now in sport mode!", model);
    }
}
