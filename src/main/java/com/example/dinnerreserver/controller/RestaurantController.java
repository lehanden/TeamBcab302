package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("restaurantpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }
}
