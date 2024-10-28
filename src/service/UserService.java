package service;

import model.User;
import repository.UserRepository;

/**
 * Service class for managing users.
 */
public class UserService {

    // Reference to the UserRepository
    private final UserRepository userRepository;

    /**
     * Constructor for UserService.
     *
     * @param userRepository the UserRepository instance to use
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user and adds it to the repository if the username is unique.
     *
     * @param username  the username for the new user
     * @param password  the password for the new user
     * @param email     the email for the new user
     * @param firstName the first name of the new user
     * @param lastName  the last name of the new user
     * @return the created User object, or null if username is already taken
     */
    public User createUser(String username, String password, String email, String firstName, String lastName) {
        if (userRepository.usernameExists(username)) {
            System.out.println("Username '" + username + "' is already taken. Choose a different username.");
            return null;
        } else {
            User newUser = new User(firstName, lastName, email, username, password);
            userRepository.addUser(newUser);
            System.out.println("User created: " + newUser.getUsername());
            return newUser;
        }
    }

    /**
     * Retrieves a user's details based on the username.
     *
     * @param username the username
     * @return the User object if found, null otherwise
     */
    public User getUserDetails(String username) {
        return userRepository.getUserByUsername(username);
    }

    /**
     * Deletes a user based on the username.
     *
     * @param username the username of the user to delete
     */
    public void deleteUser(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            userRepository.removeUser(user);
            System.out.println("User '" + username + "' has been deleted.");
        } else {
            System.out.println("Username '" + username + "' does not exist. Cannot delete user.");
        }
    }

    /**
     * Updates the details of an existing user.
     *
     * @param username  the username of the user to update
     * @param password  the new password
     * @param email     the new email
     * @param firstName the new first name
     * @param lastName  the new last name
     * @return the updated User object, or null if the user does not exist
     */
    public User updateUser(String username, String password, String email, String firstName, String lastName) {
        User existingUser = userRepository.getUserByUsername(username);
        if (existingUser == null) {
            System.out.println("Username '" + username + "' does not exist. Cannot update user.");
            return null;
        } else {
            existingUser.setPassword(password);
            existingUser.setEmail(email);
            existingUser.setFirstName(firstName);
            existingUser.setLastName(lastName);
            System.out.println("User '" + username + "' has been updated.");
            return existingUser;
        }
    }
}
