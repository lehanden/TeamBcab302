package com.example.dinnerreserver.model;

import java.util.ArrayList;
import java.util.List;

public class MockRestaurantDAO implements IRestaurantDAO{

    public static final ArrayList<Restaurant> restaurants = new ArrayList<>();
    private static int autoIncrementedId = 0;

    public MockRestaurantDAO() {
        addRestaurant(new Restaurant("San Kai Japanese Restaurant", "164 Grey St, South Brisbane", "Japanese classics like sushi, tempura & gyoza served in a chic dining room with sidewalk seating."));
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        restaurant.setId(autoIncrementedId);
        autoIncrementedId++;
        restaurants.add(restaurant);
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {

    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {

    }

    @Override
    public Restaurant getRestaurant(int id) {
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return List.of();
    }
}
