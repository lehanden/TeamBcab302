package com.example.dinnerreserver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A database access object for a mock user used for testing
 */
public class MockUserDAO implements IUserDAO {
    /**
     * A static list of users to be used as a mock database.
     */
    public static final ArrayList<User> users = new ArrayList<>();
    private static int autoIncrementedId = 0;

    /**
     * Creates four instances of mock users
     */
    public MockUserDAO() {
        // Add some initial users to the mock database
        addUser(new User("johndoe", "johndoe@example.com", "1234"));
        addUser(new User("janedoe", "janedoe@example.com", "1234"));
        addUser(new User("jaydoe", "jaydoe@example.com", "1234"));
        addUser(new User("jerrydoe", "jerrydoe@example.com", "1234"));
    }

    @Override
    public void addUser(User user) {
        user.setId(autoIncrementedId);
        autoIncrementedId++;
        users.add(user);
    }

    @Override
    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                break;
            }
        }
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }

    @Override
    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Gets a user's information using their username
     * @param username The username of the selected user
     * @return The selected user
     */
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
