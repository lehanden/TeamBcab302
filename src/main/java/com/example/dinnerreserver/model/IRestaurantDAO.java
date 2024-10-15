package com.example.dinnerreserver.model;

import java.util.List;

/**
 * Interface for the Restaurant Data Access Object that handles
 * the CRUD operations for the User class with the database
 */
public interface IRestaurantDAO {

    /**
     * Adds a new restaurant to the database
     * @param restaurant The restaurant to add
     */
    public void addRestaurant(Restaurant restaurant);

    /**
     * Deletes a restaurant from the database
     * @param restaurant The restaurant to delete
     */
    public void deleteRestaurant(Restaurant restaurant);

    /**
     * Updates an existing restaurant in the database
     * @param restaurant The restaurant to update
     */
    public void updateRestaurant(Restaurant restaurant);

    /**
     * Retrieves a restaurant from the database
     * @param id The id of the restaurant to retrieve
     * @return The restaurant with the given id, or null if not found
     */
    public Restaurant getRestaurant(int id);

    /**
     * Retrieves all restaurants from the database
     * @return A list of all restaurants in the database
     */
    public List<Restaurant> getAllRestaurants();

}
