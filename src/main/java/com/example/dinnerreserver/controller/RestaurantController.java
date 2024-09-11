package com.example.dinnerreserver.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import com.example.dinnerreserver.model.Restaurant;

import java.io.IOException;

public class RestaurantController {

    @FXML
    private Text name;

    @FXML
    private Text address;

    @FXML
    private Text description;

    @FXML
    private void selectRestaurant(Restaurant restaurant) throws IOException
    {
        name.setText(restaurant.getName());
        address.setText(restaurant.getAddress());
        description.setText(restaurant.getDescription());
    }

    @FXML
    public void MockRestaurant(ActionEvent actionEvent) throws IOException {
        selectRestaurant(new Restaurant("San Kai Japanese Restaurant", "164 Grey St, South Brisbane", "Japanese classics like sushi, tempura & gyoza served in a chic dining room with sidewalk seating."));
    }
}
