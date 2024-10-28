package service;

import model.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Service class for managing user transactions. This class handles adding,
 * retrieving, deleting, and calculating total amounts for transactions.
 */
public class TransactionService {

    // List to store Transaction objects
    private final List<Transaction> transactions = new ArrayList<>();

    // Reference to UserLookupService (implemented by UserRepository)
    private final UserLookupService userLookupService;

    /**
     * Constructor for TransactionService.
     *
     * @param userLookupService the UserLookupService instance to use
     */
    public TransactionService(UserLookupService userLookupService) {
        this.userLookupService = userLookupService;
    }

    /**
     * Adds a new transaction for a user.
     * Takes in transaction details, creates a new Transaction object, and stores it in a list.
     * Associates the transaction with the specified username.
     *
     * @param username        The username of the user for whom the transaction is created
     * @param transactionType The type of transaction (e.g., "income", "expense", "investment")
     * @param amount          The amount of money involved in the transaction
     * @param transactionDate The date of the transaction
     * @param description     A brief description of the transaction
     */
    public void addTransaction(String username, String transactionType, double amount, LocalDate transactionDate, String description) {
        if (!userLookupService.usernameExists(username)) {
            throw new IllegalArgumentException("Username '" + username + "' does not exist. Cannot add transaction.");
        }
        transactions.add(new Transaction(username, transactionType, amount, transactionDate, description));
    }

    /**
     * Retrieves all transactions for a specific user.
     * Searches the list of transactions and returns only those associated with the provided username.
     *
     * @param username The username of the user whose transactions are being retrieved
     * @return A list of transactions associated with the given username
     */
    public List<Transaction> getTransactionsByUser(String username) {

        List<Transaction> userTransactions = new ArrayList<>();

        // Check if the username exists using UserLookupService
        if (!userLookupService.usernameExists(username)) {
            throw new IllegalArgumentException("Username '" + username + "' does not exist.");
        }

        // Loop through transactions and add those matching the username to userTransactions
        for (Transaction transaction : transactions) {
            if (transaction.getUsername().equals(username)) {
                userTransactions.add(transaction);
            }
        }

        return userTransactions;
    }

    /**
     * Deletes a specific transaction.
     * Searches for the transaction by its ID and removes it from the list if found.
     *
     * @param transactionId The unique ID of the transaction to delete
     * @return true if the transaction was successfully deleted, false if not found
     */
    public boolean deleteTransaction(int transactionId) {

        Iterator<Transaction> iterator = transactions.iterator();

        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();

            if (transaction.getTransactionId() == transactionId) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    /**
     * Calculates the total amount for a specific transaction type for a user.
     * Sums all transaction amounts for the given type (e.g., "income" or "expense") associated with the specified username.
     *
     * @param username        The username of the user whose transactions are being calculated
     * @param transactionType The type of transaction to filter by (e.g., "income", "expense")
     * @return The total amount for the specified transaction type for the user
     */
    public double getTotalAmountByType(String username, String transactionType) {

        double sum = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType().equals(transactionType) && Objects.equals(username, transaction.getUsername())) {
                sum += transaction.getAmount();
            }
        }
        return sum;
    }

}