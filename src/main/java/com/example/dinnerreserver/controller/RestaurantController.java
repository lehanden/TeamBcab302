package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import com.example.dinnerreserver.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

    @FXML
    private void onBack() throws IOException{
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landingpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }
}
