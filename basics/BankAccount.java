package basics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankAccount {
    // Instance variables
    String accountHolderName;
    double accountBalance;
    
    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(BankAccount.class);
    
    // Constructor
    BankAccount(String accountHolderName, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountBalance = initialBalance;
        logger.info("BankAccount created for {} with initial balance: {}", this.accountHolderName, this.accountBalance);
    }
    
    // Methods(Behaviour)
    void depositMoney(double moneyToDeposit) {
        if (moneyToDeposit > 0) {
            this.accountBalance += moneyToDeposit;
            logger.info("Deposited {} to {}'s account. New Balance: {}", moneyToDeposit, accountHolderName, this.accountBalance);
        } else {
            logger.warn("Attempted to deposit an invalid amount: {}", moneyToDeposit);
        }
    }
    
    void withdrawMoney(double moneyToWithdraw) {
        if (this.accountBalance >= moneyToWithdraw) {
            this.accountBalance -= moneyToWithdraw;
            logger.info("Withdraw {} from {}'s account. Remaining Balance: {}", moneyToWithdraw, accountHolderName, this.accountBalance);
        } else if (moneyToWithdraw > this.accountBalance) {
            logger.error("Insufficient funds for withdrawal of {}. Current Balance: {}", moneyToWithdraw, this.accountBalance);
        } else {
            logger.warn("11Attempted to withdraw an invalid amount: {}", moneyToWithdraw);
        }
    }
    
    public static void main(String[] args) {
        // Creating a bank account object
        BankAccount account = new BankAccount("	Vaibhav", 1000.0);

        // Performing transactions
        account.depositMoney(500.0);
        account.withdrawMoney(300.0);
        account.withdrawMoney(2000.0); // Insufficient funds
    }
}
