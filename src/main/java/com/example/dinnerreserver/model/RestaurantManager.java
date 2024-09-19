package com.example.dinnerreserver.model;

import java.util.List;

public class RestaurantManager {
    private IRestaurantDAO restaurantDAO;
    public RestaurantManager(IRestaurantDAO restaurantDAO){
        this.restaurantDAO = restaurantDAO;
    }

    public List<Restaurant> searchRestaurants(String query){
        return restaurantDAO.getAllRestaurants()
                .stream()
                .filter(restaurant -> isRestaurantMatched(restaurant, query))
                .toList();
    }
    private boolean isRestaurantMatched(Restaurant restaurant, String query){
        if(query == null || query.isEmpty()) return true;
        query = query.toLowerCase();
        String searchString = restaurant.getName();
        return searchString.toLowerCase().contains(query);
    }

    public void addRestaurant(Restaurant restaurant){
        restaurantDAO.addRestaurant(restaurant);
    }
    public void deleteRestaurant(Restaurant restaurant){
        restaurantDAO.deleteRestaurant(restaurant);
    }
    public void updateRestaurant(Restaurant restaurant){
        restaurantDAO.updateRestaurant(restaurant);
    }
    public List<Restaurant> getAllRestaurants(){
        return restaurantDAO.getAllRestaurants();
    }

}
