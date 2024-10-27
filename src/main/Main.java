package main;

import service.TransactionService;
import service.UserService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Initialize UserService and TransactionService
        UserService userService = new UserService();
        TransactionService transactionService = new TransactionService(userService);

        // Create two users
        userService.createUser("user1", "password123", "user1@example.com", "User", "One");
        userService.createUser("user2", "password123", "user2@example.com", "User", "Two");

        // Add transactions for user1
        transactionService.addTransaction("user1", "income", 100.0, LocalDate.now(), "Job income");
        transactionService.addTransaction("user1", "income", 50.0, LocalDate.now().minusDays(1), "Side job");
        transactionService.addTransaction("user1", "expense", 30.0, LocalDate.now().minusDays(2), "Groceries");
        transactionService.addTransaction("user1", "expense", 20.0, LocalDate.now().minusDays(3), "Utilities");

        // Add transactions for user2
        transactionService.addTransaction("user2", "income", 300.0, LocalDate.now(), "Bonus");
        transactionService.addTransaction("user2", "income", 150.0, LocalDate.now().minusDays(1), "Freelance");
        transactionService.addTransaction("user2", "expense", 80.0, LocalDate.now().minusDays(2), "Shopping");
        transactionService.addTransaction("user2", "expense", 40.0, LocalDate.now().minusDays(3), "Transport");

        // Test getTotalAmountByType directly for user1
        System.out.println("Testing getTotalAmountByType for user1...");
        double user1IncomeTotal = transactionService.getTotalAmountByType("user1", "income");  // Expected: 150.0
        double user1ExpenseTotal = transactionService.getTotalAmountByType("user1", "expense"); // Expected: 50.0
        System.out.println("Expected income total for user1: 150.0, Calculated: " + user1IncomeTotal);
        System.out.println("Expected expense total for user1: 50.0, Calculated: " + user1ExpenseTotal);

        // Test getTotalAmountByType directly for user2
        System.out.println("Testing getTotalAmountByType for user2...");
        double user2IncomeTotal = transactionService.getTotalAmountByType("user2", "income");  // Expected: 450.0
        double user2ExpenseTotal = transactionService.getTotalAmountByType("user2", "expense"); // Expected: 120.0
        System.out.println("Expected income total for user2: 450.0, Calculated: " + user2IncomeTotal);
        System.out.println("Expected expense total for user2: 120.0, Calculated: " + user2ExpenseTotal);
    }
}
