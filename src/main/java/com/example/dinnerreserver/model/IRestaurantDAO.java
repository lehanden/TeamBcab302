package com.example.dinnerreserver.model;

import java.util.List;

public interface IRestaurantDAO {

    public void addRestaurant(Restaurant restaurant);

    public void updateRestaurant(Restaurant restaurant);

    public void deleteRestaurant(Restaurant restaurant);

    public Restaurant getRestaurant(int id);

    public List<Restaurant> getAllRestaurants();
}
