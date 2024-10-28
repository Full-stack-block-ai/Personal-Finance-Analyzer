package model;
import java.util.regex.Pattern;

/**
 * Represents a user in the system with an id, email, username, and password.
 */
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    // Regular expression pattern for email validation
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";



    /**
     * Constructs a User object with the specified id, email, username, and password.
     *
     * @param firstName       the user firstname
     * @param lastName       the user lastname
     * @param email     the email address of the user
     * @param username  the username of the user
     * @param password  the password for the user
     */
    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.setEmail(email);
        this.setPassword(password);
    }

    /**
     * Checks if email is valid using pre defined pattern
     */
    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    /**
     * Checks if password is valid using pre-defined pattern
     */
    public static boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }


    /**
     * Gets the user firstname.
     *
     * @return the user firstname
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the user firstName.
     *
     * @param firstName the user firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user lastname.
     *
     * @return the user lastname
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the user lastName.
     *
     * @param lastName the user lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the new email address
     */
    public void setEmail(String email) {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        if (isValidPassword(password)){
            this.password = password;
        }else{
            throw new IllegalArgumentException("Invalid password");
        }
    }
}