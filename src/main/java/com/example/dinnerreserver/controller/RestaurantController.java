package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import com.example.dinnerreserver.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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

    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star5;

    public RestaurantController() {
        restaurantDAO = new SqliteRestaurantDAO();
        restaurantDAO.insertSampleData();
    }

    @FXML
    public Restaurant selectRestaurant(Integer restaurantId)
    {
        Restaurant restaurants = restaurantDAO.getRestaurant(restaurantId);
        Float restaurantRating = restaurants.getRating();
        String rating_score = STR."\{restaurantRating.toString()} / 5";
        name.setText(restaurants.getName());
        address.setText(restaurants.getAddress());
        description.setText(restaurants.getDescription());
        rating.setText(rating_score);

        if(restaurantRating > 4.4) {
            star1.setVisible(true);
            star2.setVisible(true);
            star3.setVisible(true);
            star4.setVisible(true);
            star5.setVisible(true);
        } else if(restaurantRating > 3.4) {
            star1.setVisible(true);
            star2.setVisible(true);
            star3.setVisible(true);
            star4.setVisible(true);
        } else if(restaurantRating > 2.4) {
            star1.setVisible(true);
            star2.setVisible(true);
            star3.setVisible(true);
        } else if(restaurantRating > 1.4) {
            star1.setVisible(true);
            star2.setVisible(true);
        } else if(restaurantRating > 0.4) {
            star1.setVisible(true);
        }

        return restaurants;
    }

    @FXML
    private void onBack() throws IOException{
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("browsepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }
}
