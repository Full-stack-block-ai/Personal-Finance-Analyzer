package repository;

import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing user data.
 * This class provides methods to add, remove, and retrieve users.
 */
public class UserRepository{

    // List to store User objects
    private final List<User> users = new ArrayList<>();


    /**
     * Checks if a username exists in the repository.
     *
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */

    public boolean usernameExists(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    /**
     * Adds a new user to the repository.
     *
     * @param user the User object to be added
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Removes a user from the repository.
     *
     * @param user the User object to be removed
     */
    public void removeUser(User user) {
        users.remove(user);
    }


    /**
     * Retrieves a user by username.
     *
     * @param username the username of the user
     * @return the User object if found, null otherwise
     */
    public User getUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}