package multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Inventory class with shared stock count
//synchronize block use
class Inventory {
    private static final Logger logger = LoggerFactory.getLogger(Inventory.class);
    private int stockCount; // Shared resource: stock count

    // Constructor to initialize stock
    public Inventory(int initialStock) {
        this.stockCount = initialStock;
        logger.info("Initial stock set to {}", initialStock);
    }

    // Synchronized method to purchase an item
    public synchronized boolean purchaseItem(String customerName) {
        if (stockCount > 0) {
            stockCount--;
            logger.info("{} purchased an item. Remaining stock: {}", customerName, stockCount);
            return true;
        } else {
            logger.warn("{} tried to purchase, but no stock available.", customerName);
            return false;
        }
    }

    // Synchronized method to restock items
    public synchronized void restock(int quantity) {
        stockCount += quantity;
        logger.info("Restocked {} items. Total stock: {}", quantity, stockCount);
    }

    // Method to get the current stock count (not synchronized since it's read-only)
    public int getStockCount() {
        return stockCount;
    }
}

// WarehouseUpdater: Simulates restocking items
class WarehouseUpdater implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseUpdater.class);
    private Inventory inventory;

    public WarehouseUpdater(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000); // Simulating delay for restocking
            int restockAmount = 10; // Restocking 10 items
            inventory.restock(restockAmount);
            logger.info("Warehouse restocked {} items.", restockAmount);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("WarehouseUpdater interrupted", e);
        }
    }
}

// Customer: Simulates a customer attempting to purchase items
class Customer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Customer.class);
    private Inventory inventory;
    private String customerName;

    public Customer(Inventory inventory, String customerName) {
        this.inventory = inventory;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        while (true) {
            if (inventory.purchaseItem(customerName)) {
                break; // Stop trying once the purchase is successful
            } else {
                logger.info("{} is waiting for stock to be available.", customerName);
                try {
                    Thread.sleep(1000); // Wait and retry
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.error("Customer thread interrupted", e);
                }
            }
        }
    }
}

// Main class to demonstrate the inventory system
public class MultithreadingDemo {
    private static final Logger logger = LoggerFactory.getLogger(MultithreadingDemo.class);

    public static void main(String[] args) {
        // Initialize inventory with 5 items
        Inventory inventory = new Inventory(5);

        // Warehouse thread to restock items
        Thread warehouseUpdater = new Thread(new WarehouseUpdater(inventory), "WarehouseUpdater");

        // Customer threads attempting to purchase items
        Thread customer1 = new Thread(new Customer(inventory, "Customer1"));
        Thread customer2 = new Thread(new Customer(inventory, "Customer2"));
        Thread customer3 = new Thread(new Customer(inventory, "Customer3"));

        logger.info("Starting threads...");

        // Start all threads
        customer1.start();
        customer2.start();
        customer3.start();
        warehouseUpdater.start();
    }
}
