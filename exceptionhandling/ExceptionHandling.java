package exceptionhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class InvalidPaymentAmountException extends Exception{
	
	public InvalidPaymentAmountException(String message) {
		super(message);
	}
}


class InvalidCardException extends Exception{
	
	public InvalidCardException(String message) {
		super(message);
	}
}

interface Payment{
	void processPayment(double amount) throws InvalidPaymentAmountException;
}

abstract class CardPayment implements Payment{
	protected String cardNumber;
	
	static final Logger logger=LoggerFactory.getLogger(CardPayment.class);
	
	public CardPayment(String cardNumber) throws InvalidCardException {
        if (cardNumber == null || cardNumber.length() != 16) {
            throw new InvalidCardException("Card number must be exactly 16 digits.");
        }
        this.cardNumber = cardNumber;
        logger.info("Card initialized: {}", maskCardNumber());
    }
	
	 // Mask the card number for security
    protected String maskCardNumber() {
        return "XXXX-XXXX-XXXX-" + cardNumber.substring(cardNumber.length() - 4);
    }
}

class CreditCardPayment extends CardPayment{
	  public CreditCardPayment(String cardNumber) throws InvalidCardException {
	        super(cardNumber);
	    }

	    @Override
	    public void processPayment(double amount) throws InvalidPaymentAmountException {
	        if (amount <= 0) {
	            throw new InvalidPaymentAmountException("Payment amount must be greater than zero.");
	        }
	        logger.info("Processing credit card payment of ${} for card {}", amount, maskCardNumber());
	    }
}

//Implementation for debit card payments
 class DebitCardPayment extends CardPayment {
 public DebitCardPayment(String cardNumber) throws InvalidCardException {
     super(cardNumber);
 }

 @Override
 public void processPayment(double amount) throws InvalidPaymentAmountException {
     if (amount <= 0) {
         throw new InvalidPaymentAmountException("Payment amount must be greater than zero.");
     }
     logger.info("Processing debit card payment of ${} for card {}", amount, maskCardNumber());
 }
}

public class ExceptionHandling {
	 private static final Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

	 public static void main(String[] args) {
	        try {
	            // Initialize card payments
	            CreditCardPayment creditCard = new CreditCardPayment("1234567812345678");
	            DebitCardPayment debitCard = new DebitCardPayment("8765432187654321");

	            // Process valid payments
	            creditCard.processPayment(150.0);
	            debitCard.processPayment(200.0);

	            // Process invalid payment to demonstrate exception
	            creditCard.processPayment(-50.0);

	        } catch (InvalidCardException e) {
	            logger.error("Error during card initialization: {}", e.getMessage());
	        } catch (InvalidPaymentAmountException e) {
	            logger.error("Error during payment processing: {}", e.getMessage());
	        } catch (Exception e) {
	            logger.error("An unexpected error occurred: {}", e.getMessage());
	        } finally {
	            logger.info("Payment processing finished.");
	        }
	  }

}
