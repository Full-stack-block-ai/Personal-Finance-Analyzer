package model;

import java.time.LocalDate;

public class Transaction {


    private static int idCounter = 1;
    private final int transactionId;
    private final String username;
    private String transactionType;
    private double amount;
    private LocalDate transactionDate;
    private String description;

    /**
     * Constructor that initializes a Transaction with a unique ID and transaction details.
     * The transaction ID is Automatically assigned based on an incrementing static counter, ensuring
     * each transaction has a unique identifier.
     *
     * @param username         The username of the user who made the transaction.
     * @param transactionType The type of transaction (e.g., "income", "expense", "investment").
     * @param amount          The amount of money involved in the transaction.
     * @param transactionDate The date when the transaction occurred.
     * @param description     A brief description of the transaction.
     */
    public Transaction(String username, String transactionType, double amount, LocalDate transactionDate, String description) {

        // Validate the transaction type, ensuring it is either "income," "expense," or "investment."
        // If the transaction type is invalid, throw an IllegalArgumentException.
        if (!isValidTransactionType(transactionType.toLowerCase())) {
            throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
        }

        // Ensure that the transaction amount is a positive number.
        // If the amount is negative, throw an IllegalArgumentException.
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }

        this.transactionId = idCounter;
        idCounter ++;
        this.username = username;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.description = description;
    }

    /**
     * Checks if the transaction type is valid.
     *
     * @param transactionType the type of transaction
     * @return true if the type is valid, false otherwise
     */
    private boolean isValidTransactionType(String transactionType) {
        return transactionType.equals("income") || transactionType.equals("expense") || transactionType.equals("investment");
    }

    /**
     * Gets the unique transaction ID.
     *
     * @return the transaction ID
     */
     public int getTransactionId() {
         return transactionId;
     }

    /**
     * Gets the username of the user who made the transaction.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }


    /**
     * Gets the type of the transaction (e.g., "income", "expense", "investment").
     *
     * @return the transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Gets the amount of money involved in the transaction.
     *
     * @return the transaction amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the date when the transaction occurred.
     *
     * @return the transaction date
     */
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return the transaction description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the type of the transaction (e.g., "income", "expense", "investment").
     *
     * @param transactionType the type of transaction
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Sets the amount of money involved in the transaction.
     *
     * @param amount the transaction amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Sets the date when the transaction occurred.
     *
     * @param transactionDate the transaction date
     */
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param description the transaction description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
