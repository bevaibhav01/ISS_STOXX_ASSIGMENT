package oops.encapsulation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BankAccount {
    private static final Logger logger = LoggerFactory.getLogger(BankAccount.class);

    // Private fields to ensure data security
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        setBalance(initialBalance); // Using setter for validation
        logger.info("Bank account created for {} with Account Number: {}", accountHolderName, accountNumber);
    }

    // Getter for accountHolderName
    public String getAccountHolderName() {
        return accountHolderName;
    }

    // Setter for accountHolderName
    public void setAccountHolderName(String accountHolderName) {
        if (accountHolderName != null && !accountHolderName.isEmpty()) {
            this.accountHolderName = accountHolderName;
            logger.info("Account holder name updated to: {}", accountHolderName);
        } else {
            logger.warn("Invalid account holder name. Update ignored.");
        }
    }

    // Getter for accountNumber
    public String getAccountNumber() {
        return accountNumber;
    }

 

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Setter for balance with validation
    private void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            logger.warn("Initial balance cannot be negative. Setting balance to $0.0.");
            this.balance = 0.0;
        }
    }

    // Public method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            logger.info("Deposited ${}. New Balance: ${}", amount, balance);
        } else {
            logger.warn("Invalid deposit amount: ${}. Deposit failed.", amount);
        }
    }

    // Public method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            logger.info("Withdrew ${}. Remaining Balance: ${}", amount, balance);
        } else if (amount > balance) {
            logger.error("Insufficient funds for withdrawal of ${}. Current Balance: ${}", amount, balance);
        } else {
            logger.warn("Invalid withdrawal amount: ${}. Withdrawal failed.", amount);
        }
    }

    // Public method to display account details
    public void displayAccountDetails() {
        logger.info("Account Details: Holder Name: {}, Account Number: {}, Balance: ${}",
                accountHolderName, accountNumber, balance);
    }

    
}

public class EncapsulationDemo {

    public static void main(String[] args) {
        // Creating a new bank account
        BankAccount account = new BankAccount("John Doe", "1234567890", 500.0);

        // Displaying account details
        account.displayAccountDetails();

        // Performing some transactions
        account.deposit(200.0); // Valid deposit
        account.withdraw(100.0); // Valid withdrawal
        account.withdraw(700.0); // Insufficient funds

        // Attempting invalid operations
        account.deposit(-50.0); // Invalid deposit
        account.withdraw(-30.0); // Invalid withdrawal

        // Updating account holder name
        account.setAccountHolderName("Jane Doe");

        // Displaying updated account details
        account.displayAccountDetails();
    }
}


