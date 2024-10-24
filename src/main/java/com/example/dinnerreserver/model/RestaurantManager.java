package com.example.dinnerreserver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple class to handle the restaurant search features
 */
public class RestaurantManager {
    private IRestaurantDAO restaurantDAO;

    /**
     * Constructor methods for the database access object
     * @param restaurantDAO
     */
    public RestaurantManager(IRestaurantDAO restaurantDAO){
        this.restaurantDAO = restaurantDAO;
    }

    /**
     * Gets a list of restaurants based on a search
     * @param query The given search query
     * @return The list of restaurants that match the query
     */
    public List<Restaurant> searchRestaurants(String query){
        return restaurantDAO.getAllRestaurants()
                .stream()
                .filter(restaurant -> isRestaurantMatched(restaurant, query))
                .toList();
    }
    private boolean isRestaurantMatched(Restaurant restaurant, String query){
        if(query == null || query.isEmpty()) return true;
        query = query.toLowerCase();

        String name = restaurant.getName().toLowerCase();
        String address = restaurant.getAddress().toLowerCase();
        String description = restaurant.getDescription().toLowerCase();

        return name.contains(query) || address.contains(query) || description.contains(query);
        //return searchString.toLowerCase().contains(query);
    }

    /**
     * Adds a new restaurant to the database table
     * @param restaurant The new restaurant to be added
     */
    public void addRestaurant(Restaurant restaurant){
        restaurantDAO.addRestaurant(restaurant);
    }

    /**
     * Deletes a restaurant from the database table
     * @param restaurant The restaurant to be deleted
     */
    public void deleteRestaurant(Restaurant restaurant){
        restaurantDAO.deleteRestaurant(restaurant);
    }

    /**
     * Updates a restaurant in the database table
     * @param restaurant The restaurant to be updated
     */
    public void updateRestaurant(Restaurant restaurant){
        restaurantDAO.updateRestaurant(restaurant);
    }

    /**
     * Gets a list of all restaurants from the database
     * @return A list of all restaurants
     */
    public List<Restaurant> getAllRestaurants(){
        return restaurantDAO.getAllRestaurants();
    }

    /**
     *
     * @param restaurantList A list of restaurants
     * @return the restaurant list sorted Alphabetically
     */
    public List<Restaurant> sortRestaurantsAlphabetically(List<Restaurant> restaurantList) {
        // Create a mutable copy of the list
        List<Restaurant> mutableList = new ArrayList<>(restaurantList);
        mutableList.sort((r1, r2) -> r1.getName().compareToIgnoreCase(r2.getName()));
        return mutableList;
    }

    /**
     *
     * @param restaurantList A list of restaurants
     * @return the restaurant list sorted by highest rating
     */
    public List<Restaurant> sortRestaurantsByRating(List<Restaurant> restaurantList) {
        // Create a mutable copy of the list
        List<Restaurant> mutableList = new ArrayList<>(restaurantList);
        mutableList.sort((r1, r2) -> Float.compare(r2.getRating(), r1.getRating()));  // Sort by rating in descending order
        return mutableList;
    }
}
