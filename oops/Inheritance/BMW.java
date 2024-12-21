package oops.Inheritance;

// BMW class (Subclass of Car)
class BMW extends Car {
    // Constructor for BMW
    public BMW(String model, int year) {
        super("BMW", model, year);  // Call the parent class constructor
        logger.info("BMW {} ({}) created.", model, year);
    }

    // overriding parent method by implementing own sports mode
    @Override
    public void sportMode() {
        logger.info("Sport mode activated for BMW {}!", model);
    }

    @Override
    public void startVehicle() {
        super.startVehicle();  // Calling the parent class startVehicle method
        logger.info("The BMW {} is now in comfort mode!", model);
    }
}
