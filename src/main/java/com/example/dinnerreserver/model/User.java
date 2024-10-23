package com.example.dinnerreserver.model;

/**
 * A simple class for the users containing the same parameters as the users database
 */
public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    /**
     * Constructor methods for each of the user parameters
     * @param username
     * @param email
     * @param password
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getter and setter methods
    /**
     * Gets the current user id
     * @return The id of the current user
     */
    public int getId() {
        return id;
    }

    /**
     * Updates the current user to use the given user id
     * @param id The new user id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the current user
     * @return The current user's username
     */
    public String getUsername() { return username; }

    /**
     * Updates the current user to use the given username
     * @param username The new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email address of the current user
     * @return The current user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Updates the current user to use the given email address
     * @param email The new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email.matches(emailRegex);
    }

    /**
     * Gets the password of the current user
     * @return The current user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Updates the current user to use the given password
     * @param password The new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
