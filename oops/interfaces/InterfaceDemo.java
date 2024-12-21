package oops.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//create interface/blueprint for payment processor classes
// so different modes will implement this method
//according to there process
interface PaymentProcessor{
	void paymentProcessor(double amount);
	void refundPayment(double amount);
	void displayPaymentDetails();
}


class CreditCardPayment implements PaymentProcessor{
	
	private static final Logger logger=LoggerFactory.getLogger(CreditCardPayment.class);
	
	private String cardNumber;
	private String cardHolderName;
	
	 CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        logger.info("CreditCardPayment object created for card holder: {}", cardHolderName);
    }

	@Override
	public void paymentProcessor(double amount) {

		 logger.info("Processing Credit Card payment of ${} for card holder: {}", amount, cardHolderName);
	}

	@Override
	public void refundPayment(double amount) {
		 logger.info("Refunding ${} to Credit Card: {} for card holder: {}", amount, getCardNumber(), cardHolderName);
		
	}

	@Override
	public void displayPaymentDetails() {
		logger.info("Credit Card Details: Card Holder: {}, Card Number: {}", cardHolderName, getCardNumber());
		
	}
	 // Helper method to get the card number for security
    private String getCardNumber() {
        return "XXXX-XXXX-XXXX-" + cardNumber.substring(cardNumber.length() - 4);
    }
	
}

class PayPalPayment implements PaymentProcessor{
	
	 private static final Logger logger = LoggerFactory.getLogger(PayPalPayment.class);
	 
	 private String email;
	    
	 public PayPalPayment(String email) {
	        this.email = email;
	        logger.info("PayPalPayment object created for email: {}", email);
	 }

	    @Override
	    public void paymentProcessor(double amount) {
	        logger.info("Processing PayPal payment of ${} for email: {}", amount, email);
	    }

	    @Override
	    public void refundPayment(double amount) {
	        logger.info("Refunding ${} to PayPal account: {}", amount, email);
	    }

	    @Override
	    public void displayPaymentDetails() {
	        logger.info("PayPal Account Details: Email: {}", email);
	    }
	
}


public class InterfaceDemo {
	private static final Logger logger = LoggerFactory.getLogger(InterfaceDemo.class);
	
	public static void main(String []args) {
		
		 logger.info("Starting Payment System Application...");

	        // Create a CreditCardPayment instance
	        PaymentProcessor creditCardPayment = new CreditCardPayment("1234567812345678", "Vaibhav Mahale");

	        // Create a PayPalPayment instance
	        PaymentProcessor payPalPayment = new PayPalPayment("vaibhav.mahale@example.com");

	        // Process payments
	        creditCardPayment.paymentProcessor(150.0);
	        payPalPayment.paymentProcessor(200.0);

	        // Display payment details
	        creditCardPayment.displayPaymentDetails();
	        payPalPayment.displayPaymentDetails();

	        // Refund payments
	        creditCardPayment.refundPayment(50.0);
	        payPalPayment.refundPayment(100.0);

	        logger.info("Payment System Application Ended.");
	

	}
}
