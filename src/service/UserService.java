package service;

import model.User;

import java.util.ArrayList;

/**
 * Creates a new valid user from the user class
 */
public class UserService {

    //arraylist for user objects to be stored
    private final ArrayList<User> users = new ArrayList<>();


    /**
     * Creates a new user and adds it to the users list if the username is unique.
     *
     * @param username  the username for the new user
     * @param password  the password for the new user
     * @param email     the email for the new user
     * @param firstName the first name of the new user
     * @param lastName  the last name of the new user
     */
    public User createUser(String username, String password, String email, String firstName, String lastName) {
        if (usernameExists(username)) {
            System.out.println("Username '" + username + "' is already taken. Choose a different username.");
            return null;
        } else {
            User newUser = new User(firstName, lastName, email, username, password);
            users.add(newUser);
            System.out.println("User created: " + newUser.getUsername());
            return newUser;
        }

    }

    /**
     * Checks if a username already exists in the users list.
     *
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    public boolean usernameExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }



    /**
     * Retrieves a user's details based on the username.
     * @param username username
     * @return user user
     */
    public User getUserDetails(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     *Deletes a user based on the username.
     */
    public void deleteUser(String username) {
        if (!usernameExists(username)) {
            System.out.println("Username '" + username + "' does not exist. Choose a different username.");
        }else{
            users.remove(getUserDetails(username));
        }
    }

    /**
     *Checks if username exists and updates user details
     */
    public User updateUser(String username, String password, String email, String firstName, String lastName) {
        if (!usernameExists(username)) {
            System.out.println("Username '" + username + "' does not exist. Choose a different username.");
            return null;
        }else{
           User existingUser = getUserDetails(username);
           existingUser.setPassword(password);
           existingUser.setEmail(email);
           existingUser.setFirstName(firstName);
           existingUser.setLastName(lastName);
        }
        return getUserDetails(username);
    }
}
