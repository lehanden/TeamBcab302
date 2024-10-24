package com.example.dinnerreserver.model;

/**
 * A simple class to share restaurant instance data between classes
 */
public class SharedData {
    private static SharedData instance;
    /**
     * The selected restaurant
     */
    public Restaurant selectedRestaurant;

    private SharedData() {}

    /**
     * Gets a new instance of the SharedData class
     * @return The new SharedData instance
     */
    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    /**
     * Gets the shared restaurant data
     * @return The selected restaurant
     */
    public Restaurant getSelectedRestaurant() {
        return selectedRestaurant;
    }

    /**
     * Updates the shared data instance to use a given restaurant
     * @param restaurant The new restaurant data
     */
    public void setSelectedRestaurant(Restaurant restaurant) {
        this.selectedRestaurant = restaurant;
    }
}
