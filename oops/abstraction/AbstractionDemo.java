package oops.abstraction;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Abstract class representing a Payment
abstract class Payment {
    protected static final Logger logger = LoggerFactory.getLogger(Payment.class);

    // Abstract method for processing payment (to be implemented by subclasses)
    public abstract void processPayment(double amount);

    // normal/concrete method for generating a payment receipt
    public void generateReceipt(double amount) {
        logger.info("Payment of ${} has been processed successfully. Receipt generated.", amount);
    }
}

// Subclass: Credit Card Payment
class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }
    //implementing the abstract method of parent class
    //to define own behavior 
    @Override
    public void processPayment(double amount) {
        logger.info("Processing credit card payment...");
        logger.info("Card Holder: {}, Card Number: **** **** **** {}", cardHolderName, cardNumber.substring(cardNumber.length() - 4));
        logger.info("Amount Paid: ${}", amount);
        generateReceipt(amount);
    }
}

// Subclass: PayPal Payment
class PayPalPayment extends Payment {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void processPayment(double amount) {
        logger.info("Processing PayPal payment...");
        logger.info("PayPal Account: {}", email);
        logger.info("Amount Paid: ${}", amount);
        generateReceipt(amount);
    }
}

// Main class to demonstrate abstraction
public class AbstractionDemo {
    private static final Logger logger = LoggerFactory.getLogger(AbstractionDemo.class);

    public static void main(String[] args) {
        // Using Credit Card Payment
        Payment creditCardPayment = new CreditCardPayment("1234567812345678", "John Doe");
        creditCardPayment.processPayment(150.0);

        logger.info("----------------------------------------");

        // Using PayPal Payment
        Payment payPalPayment = new PayPalPayment("john.doe@example.com");
        payPalPayment.processPayment(250.0);
    }
}

