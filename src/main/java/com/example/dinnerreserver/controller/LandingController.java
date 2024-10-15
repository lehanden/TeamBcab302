package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

import static com.example.dinnerreserver.HelloApplication.*;

/**
 * A controller for the landing page when the app is opened
 */
public class LandingController {


    @FXML
    private void onSignUpButton() throws IOException
    {
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signuppage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }

    @FXML
    private void onSignInButton() throws IOException
    {
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }

//    public void onRestaurantButton() throws IOException
//    {
//        Stage stage = (Stage) Stage.getWindows().get(0);
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("restaurantpage.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
//        RestaurantController restaurantController = fxmlLoader.getController();
//        restaurantController.selectRestaurant(1);
//        stage.setScene(scene);
//    }

//    public void onBrowseButton() throws IOException
//    {
//        Stage stage = (Stage) Stage.getWindows().get(0);
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("browsepage.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
//        BrowseController browseController = fxmlLoader.getController();
//        stage.setScene(scene);
//    }

    //public void onUserProfileButton() throws IOException
    //{
        //Stage stage = (Stage) Stage.getWindows().get(0);
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userprofilepage.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        //UserProfileController userprofileController = fxmlLoader.getController();
        //userprofileController.selectUser(1);
        //stage.setScene(scene);
    //}
    //public void onBookingPageButton() throws IOException
    //{
      //  Stage stage = (Stage) Stage.getWindows().get(0);
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("bookingpage.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        //BookingController bookingController = fxmlLoader.getController();
        //stage.setScene(scene);
    //}
}
