package com.example.dinnerreserver.model;

public class SharedData {
    private static SharedData instance;
    public Restaurant selectedRestaurant;

    private SharedData() {}

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public Restaurant getSelectedRestaurant() {
        return selectedRestaurant;
    }

    public void setSelectedRestaurant(Restaurant restaurant) {
        this.selectedRestaurant = restaurant;
    }
}