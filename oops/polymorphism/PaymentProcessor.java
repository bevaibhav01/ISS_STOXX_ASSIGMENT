package oops.polymorphism;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// PaymentProcessor class demonstrating method overloading
public class PaymentProcessor {
    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessor.class);

    // Method to process payment using a credit card
    public void processPayment(String cardNumber, double amount) {
        logger.info("Processing payment of ${} using Credit Card: {}", amount, cardNumber);
        // Simulate payment processing
        logger.info("Payment of ${} via Credit Card {} was successful.", amount, cardNumber);
    }

    // Method to process payment using UPI
    public void processPayment(String upiId, double amount, String note) {
        logger.info("Processing payment of ${} using UPI ID: {} with note: {}", amount, upiId, note);
        // Simulate payment processing
        logger.info("Payment of ${} via UPI ID {} was successful.", amount, upiId);
    }

    // Method to process payment using a wallet
    public void processPayment(double amount, String walletName) {
        logger.info("Processing payment of ${} using Wallet: {}", amount, walletName);
        // Simulate payment processing
        logger.info("Payment of ${} via Wallet {} was successful.", amount, walletName);
    }

    // Method to process payment for multiple items (array of amounts)
    public void processPayment(double[] amounts) {
        double total = 0;
        logger.info("Processing payment for multiple items with amounts: {}", (Object) amounts);
        for (double amount : amounts) {
            total += amount;
        }
        logger.info("Total payment amount: ${}. Processing payment...", total);
        // Simulate payment processing
        logger.info("Payment of ${} for multiple items was successful.", total);
    }

    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        // Payment via Credit Card
        paymentProcessor.processPayment("1234-5678-9101-1121", 250.75);

        // Payment via UPI
        paymentProcessor.processPayment("user@upi", 1200.50, "Dinner payment");

        // Payment via Wallet
        paymentProcessor.processPayment(500.00, "Paytm");

        // Payment for multiple items
        double[] itemPrices = {100.50, 200.75, 300.25};
        paymentProcessor.processPayment(itemPrices);
    }
}
