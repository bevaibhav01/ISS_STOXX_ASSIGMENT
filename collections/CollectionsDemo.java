package collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

class Transaction {
    private final String transactionId;
    private final double amount;
    private final String payerName;

    public Transaction(String transactionId, double amount, String payerName) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.payerName = payerName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPayerName() {
        return payerName;
    }

    @Override
    public String toString() {
        return String.format("Transaction[ID=%s, Amount=%.2f, Payer=%s]", transactionId, amount, payerName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Transaction that = (Transaction) obj;
        return Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }
}

public class CollectionsDemo {
    private static final Logger logger = LoggerFactory.getLogger(CollectionsDemo.class);

    public static void main(String[] args) {
        // 1. Using List
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction("TXN001", 500.00, "Vaibhav"));
        transactionList.add(new Transaction("TXN002", 1200.50, "Deep"));
        transactionList.add(new Transaction("TXN003", 300.75, "Ronak"));

        logger.info("Transaction List:");
        transactionList.forEach(txn -> logger.info(txn.toString()));

        // Searching in the List
        Transaction searchResult = transactionList.stream()
                .filter(txn -> txn.getTransactionId().equals("TXN002"))
                .findFirst()
                .orElse(null);

        if (searchResult != null) {
            logger.info("Found Transaction: {}", searchResult);
        } else {
            logger.warn("Transaction with ID TXN002 not found.");
        }

        // 2. Using Set
        Set<Transaction> uniqueTransactions = new HashSet<>(transactionList);
        uniqueTransactions.add(new Transaction("TXN001", 500.00, "Alice")); // Duplicate, won't be added
        uniqueTransactions.add(new Transaction("TXN004", 800.00, "Dave"));  

        logger.info("Unique Transactions (Set):");
        uniqueTransactions.forEach(txn -> logger.info(txn.toString()));

        // 3. Using Map
        Map<String, Transaction> transactionMap = new HashMap<>();
        transactionList.forEach(txn -> transactionMap.put(txn.getTransactionId(), txn));

        // Adding another transaction
        transactionMap.put("TXN005", new Transaction("TXN005", 950.00, "Eve"));

        logger.info("Transaction Map:");
        transactionMap.forEach((key, value) -> logger.info("Key: {}, Value: {}", key, value));

        // Retrieve a specific transaction by ID
        Transaction txn005 = transactionMap.get("TXN005");
        if (txn005 != null) {
            logger.info("Retrieved from Map: {}", txn005);
        } else {
            logger.warn("Transaction with ID TXN005 not found in Map.");
        }

        // Aggregation operation on the Collection
        double totalAmount = transactionList.stream().mapToDouble(Transaction::getAmount).sum();
        logger.info("Total Transaction Amount: {:.2f}", totalAmount);
    }
}
