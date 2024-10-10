package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import com.example.dinnerreserver.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.example.dinnerreserver.model.Restaurant;


public class RestaurantController {

    SqliteRestaurantDAO restaurantDAO;
    Restaurant restaurant;

    @FXML
    private ImageView restaurantImage;

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
    public Restaurant selectRestaurant(Integer restaurantId) throws FileNotFoundException
    {
        Restaurant restaurant = restaurantDAO.getRestaurant(restaurantId);
        this.restaurant = restaurant;

        Float restaurantRating = restaurant.getRating();
        String rating_score = STR."\{restaurantRating.toString()} / 5";
        name.setText(restaurant.getName());
        address.setText(restaurant.getAddress());
        description.setText(restaurant.getDescription());
        rating.setText(rating_score);

        String imageSource = restaurant.getImageSource();
        Image restaurantImageSource = new Image(new FileInputStream(STR."./src/main/resources/com/example/dinnerreserver/\{imageSource}"));

        restaurantImage.setImage(restaurantImageSource);

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

        SharedData.getInstance().setSelectedRestaurant(restaurant);
        return restaurant;
    }

    @FXML
    private void onBack() throws IOException{
        Restaurant selectedRestaurant = SharedData.getInstance().getSelectedRestaurant();
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("browsepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }

    @FXML
    private void onForward() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("bookingpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        Stage stage = (Stage) Stage.getWindows().get(0);
        stage.setScene(scene);
    }

    @FXML
    private void onMenu() throws URISyntaxException, IOException {
        String menuSource;
        menuSource = restaurant.getMenuSource();

        Desktop.getDesktop().browse(new URI(menuSource));
    }
}
