package main;

import model.*;
import service.UserService;


public class Main {
    public static void main(String[] args) {
        // Initialize UserService
        UserService userService = new UserService();

        // 1. Test Creating Users
        System.out.println("Testing createUser with valid details...");
        userService.createUser("johndoe", "Password123@", "johndoe@example.com", "John", "Doe"); // Expected: Success
        userService.createUser("janedoe", "Password456@", "janedoe@example.com", "Jane", "Doe"); // Expected: Success
        System.out.println("Testing createUser with duplicate username...");
        userService.createUser("johndoe", "AnotherPassword789@", "newemail@example.com", "Johnny", "D"); // Expected: Username taken

        // 2. Test Retrieving User Details
        System.out.println("\nTesting getUserDetails for existing username...");
        User existingUser = userService.getUserDetails("johndoe"); // Expected: User found
        if (existingUser != null) {
            System.out.println("Found user: " + existingUser.getUsername());
        } else {
            System.out.println("User not found.");
        }

        System.out.println("Testing getUserDetails for non-existing username...");
        User nonExistingUser = userService.getUserDetails("nonexistentuser"); // Expected: User not found
        if (nonExistingUser != null) {
            System.out.println("Found user: " + nonExistingUser.getUsername());
        } else {
            System.out.println("User not found.");
        }

        // 3. Test Deleting Users
        System.out.println("\nTesting deleteUser with existing username...");
        userService.deleteUser("johndoe"); // Expected: User deleted
        System.out.println("Testing deleteUser with non-existing username...");
        userService.deleteUser("nonexistentuser"); // Expected: Username does not exist

        // Verify deletion by trying to retrieve deleted user
        System.out.println("Verifying deletion by retrieving deleted user...");
        User deletedUser = userService.getUserDetails("johndoe"); // Expected: User not found
        if (deletedUser == null) {
            System.out.println("User 'johndoe' successfully deleted.");
        } else {
            System.out.println("User deletion failed.");
        }

        // 4. Test Updating Users - Valid Update
        System.out.println("\nTesting updateUser with valid details...");
        User updatedUser = userService.updateUser("alexsmith",  "UpdatedPass456@", "updated.email@example.com", "Alexander", "Smithy"); // Expected: User updated

        if (updatedUser != null) {
            System.out.println("User updated successfully:");
            System.out.println("Username: " + updatedUser.getUsername());
            System.out.println("Password: " + updatedUser.getPassword());
            System.out.println("Email: " + updatedUser.getEmail());
            System.out.println("First Name: " + updatedUser.getFirstName());
            System.out.println("Last Name: " + updatedUser.getLastName());
        } else {
            System.out.println("User update failed.");
        }

        // Ensure that "alexsmith" can no longer be retrieved, and "alexsmithUpdated" is now active
        System.out.println("\nVerifying that the username has been updated...");
        if (userService.getUserDetails("alexsmith") == null) {
            System.out.println("Original username 'alexsmith' successfully removed.");
        }
        User newUpdatedUser = userService.getUserDetails("alexsmithUpdated");
        if (newUpdatedUser != null) {
            System.out.println("Updated username 'alexsmithUpdated' found.");
        }

        // 5. Test Updating Users - Invalid Update (Duplicate Username)
        System.out.println("\nTesting updateUser with invalid details (duplicate username)...");
        userService.createUser("janedoe", "Password456@", "janedoe@example.com", "Jane", "Doe"); // Create a user with "janedoe" username
        User invalidUpdate = userService.updateUser("janedoe", "passUpdated",  "updated.email@example.com", "Updated", "name"); // Expected: Update failed

        if (invalidUpdate == null) {
            System.out.println("Invalid update attempt correctly handled.");
        } else {
            System.out.println("Unexpected update success: " + invalidUpdate.getUsername());
        }
    }
}