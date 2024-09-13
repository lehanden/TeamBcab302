package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.IOException;

public class RestaurantController {

    IRestaurantDAO restaurantDAO;
    public RestaurantController() {
        restaurantDAO = new SqliteRestaurantDAO();
    }

    @FXML
    private Text name;

    @FXML
    private Text address;

    @FXML
    private Text description;

    @FXML
    private Text rating;

    @FXML
    public void selectRestaurant(Integer restaurantId) throws IOException
    {
        Restaurant restaurant = restaurantDAO.getRestaurant(restaurantId);
        name.setText(restaurant.getName());
        address.setText(restaurant.getAddress());
        description.setText(restaurant.getDescription());
        rating.setText(restaurant.getRating().toString());
    }
}
