package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.model.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class RestaurantController {

    SqliteRestaurantDAO restaurantDAO;

    @FXML
    private Text name;

    @FXML
    private Text address;

    @FXML
    private Text description;

    @FXML
    private Text rating;

    public RestaurantController() {
        restaurantDAO = new SqliteRestaurantDAO();
        restaurantDAO.insertSampleData();
    }

    @FXML
    public void selectRestaurant(Integer restaurantId)
    {
        Restaurant restaurant = restaurantDAO.getRestaurant(restaurantId);
        name.setText(restaurant.getName());
        address.setText(restaurant.getAddress());
        description.setText(restaurant.getDescription());
        rating.setText(restaurant.getRating().toString());
    }
}
