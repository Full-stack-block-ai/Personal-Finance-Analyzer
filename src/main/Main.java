package main;

import model.Transaction;
import model.User;
import repository.UserRepository;
import service.TransactionService;
import service.UserService;

import java.time.LocalDate;
import java.util.List;

/**
 * Main application class to demonstrate the use of UserService and TransactionService.
 */
public class Main {

    public static void main(String[] args) {
        // Create the UserRepository instance
        UserRepository userRepository = new UserRepository();

        // Create the UserService, passing in the UserRepository
        UserService userService = new UserService(userRepository);

        // Create the TransactionService, passing in the UserRepository as UserLookupService
        TransactionService transactionService = new TransactionService(userRepository);

        // Now you can use userService and transactionService

        // ===========================
        // Testing User Creation
        // ===========================
        System.out.println("=== Testing User Creation ===");
        User user1 = userService.createUser("john_doe", "Password123!", "john@example.com", "John", "Doe");
        User user2 = userService.createUser("jane_smith", "SecurePass456!", "jane@example.com", "Jane", "Smith");

        // Attempt to create a user with an existing username
        User duplicateUser = userService.createUser("john_doe", "AnotherPass789!", "johnny@example.com", "Johnny", "Doe");

        // ===========================
        // Testing Get User Details
        // ===========================
        System.out.println("\n=== Testing Get User Details ===");
        User retrievedUser = userService.getUserDetails("john_doe");
        if (retrievedUser != null) {
            System.out.println("Retrieved User: " + retrievedUser.getUsername() + ", Email: " + retrievedUser.getEmail());
        }

        // ===========================
        // Testing Update User
        // ===========================
        System.out.println("\n=== Testing Update User ===");
        User updatedUser = userService.updateUser("jane_smith", "NewPass456!", "jane_new@example.com", "Jane", "Doe");
        if (updatedUser != null) {
            System.out.println("Updated User: " + updatedUser.getUsername() + ", Email: " + updatedUser.getEmail());
        }

        // ===========================
        // Testing Delete User
        // ===========================
        System.out.println("\n=== Testing Delete User ===");
        userService.deleteUser("john_doe");
        User deletedUser = userService.getUserDetails("john_doe");
        if (deletedUser == null) {
            System.out.println("User 'john_doe' successfully deleted.");
        }

        // ===========================
        // Testing Add Transactions
        // ===========================
        System.out.println("\n=== Testing Add Transactions ===");
        // Add transactions for 'jane_smith'
        transactionService.addTransaction("jane_smith", "income", 1500.00, LocalDate.now(), "Salary");
        transactionService.addTransaction("jane_smith", "expense", 200.00, LocalDate.now(), "Groceries");
        transactionService.addTransaction("jane_smith", "investment", 500.00, LocalDate.now(), "Stocks");

        // Attempt to add a transaction for a non-existent user
        try {
            transactionService.addTransaction("john_doe", "expense", 100.00, LocalDate.now(), "Utilities");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding transaction: " + e.getMessage());
        }

        // ===========================
        // Testing Get Transactions By User
        // ===========================
        System.out.println("\n=== Testing Get Transactions By User ===");
        List<Transaction> janeTransactions = transactionService.getTransactionsByUser("jane_smith");
        System.out.println("Transactions for 'jane_smith':");
        for (Transaction t : janeTransactions) {
            System.out.println("ID: " + t.getTransactionId() + ", Type: " + t.getTransactionType()
                    + ", Amount: $" + t.getAmount() + ", Description: " + t.getDescription());
        }

        // ===========================
        // Testing Get Total Amount By Type
        // ===========================
        System.out.println("\n=== Testing Get Total Amount By Type ===");
        double totalIncome = transactionService.getTotalAmountByType("jane_smith", "income");
        double totalExpenses = transactionService.getTotalAmountByType("jane_smith", "expense");
        System.out.println("Total Income for 'jane_smith': $" + totalIncome);
        System.out.println("Total Expenses for 'jane_smith': $" + totalExpenses);

        // ===========================
        // Testing Delete Transaction
        // ===========================
        System.out.println("\n=== Testing Delete Transaction ===");
        // Get the transaction ID to delete (e.g., the first transaction)
        int transactionIdToDelete = janeTransactions.get(0).getTransactionId();
        boolean isDeleted = transactionService.deleteTransaction(transactionIdToDelete);
        if (isDeleted) {
            System.out.println("Transaction ID " + transactionIdToDelete + " deleted successfully.");
        } else {
            System.out.println("Failed to delete transaction ID " + transactionIdToDelete + ".");
        }

        // Verify the transaction is deleted
        List<Transaction> updatedTransactions = transactionService.getTransactionsByUser("jane_smith");
        System.out.println("Updated Transactions for 'jane_smith':");
        for (Transaction t : updatedTransactions) {
            System.out.println("ID: " + t.getTransactionId() + ", Type: " + t.getTransactionType()
                    + ", Amount: $" + t.getAmount() + ", Description: " + t.getDescription());
        }

        // ===========================
        // Testing Add Transaction with Invalid Type
        // ===========================
        System.out.println("\n=== Testing Add Transaction with Invalid Type ===");
        try {
            transactionService.addTransaction("jane_smith", "donation", 100.00, LocalDate.now(), "Charity");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding transaction: " + e.getMessage());
        }

        // ===========================
        // Testing Add Transaction with Negative Amount
        // ===========================
        System.out.println("\n=== Testing Add Transaction with Negative Amount ===");
        try {
            transactionService.addTransaction("jane_smith", "expense", -50.00, LocalDate.now(), "Invalid Expense");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding transaction: " + e.getMessage());
        }

        // ===========================
        // Testing Password Validation
        // ===========================
        System.out.println("\n=== Testing Password Validation ===");
        try {
            userService.createUser("bob_brown", "weakpass", "bob@example.com", "Bob", "Brown");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }

        // ===========================
        // Testing Email Validation
        // ===========================
        System.out.println("\n=== Testing Email Validation ===");
        try {
            userService.createUser("alice_green", "StrongPass789!", "invalidemail", "Alice", "Green");
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }

        // ===========================
        // Testing Get Password (Validation)
        // ===========================
        System.out.println("\n=== Testing Get Password (Validation) ===");
        try {
            String password = userService.getUserDetails("jane_smith").getPassword();
            System.out.println("Password for 'jane_smith': " + password);
        } catch (IllegalArgumentException e) {
            System.out.println("Error retrieving password: " + e.getMessage());
        }

        // ===========================
        // Testing Update User with Invalid Email
        // ===========================
        System.out.println("\n=== Testing Update User with Invalid Email ===");
        try {
            userService.updateUser("jane_smith", "NewPass456!", "invalidemail", "Jane", "Doe");
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }

        // ===========================
        // Testing Delete Non-Existent User
        // ===========================
        System.out.println("\n=== Testing Delete Non-Existent User ===");
        userService.deleteUser("non_existent_user");

        // ===========================
        // Testing Complete
        // ===========================
        System.out.println("\n=== Testing Complete ===");
    }
}
