package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import com.example.dinnerreserver.model.Restaurant;
import com.example.dinnerreserver.model.SqliteConnection;
import com.example.dinnerreserver.model.SqliteRestaurantDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingController {

    @FXML
    private Label name;

    @FXML
    private Label address;

    @FXML
    private TextField peopleTextField;

    @FXML
    private ComboBox<String> timeComboBox;

    private SqliteRestaurantDAO restaurantDAO;

    public BookingController() {
        restaurantDAO = new SqliteRestaurantDAO();
    }

    //@FXML
    //public void selectRestaurant(Integer restaurantId)
    //{
       // Restaurant restaurants = restaurantDAO.getRestaurant(restaurantId);
     //   name.setText(restaurants.getName());
        //address.setText(restaurants.getAddress());
    //}


    @FXML
    public void initialize() {
        // Initialize default values, if necessary
        timeComboBox.getSelectionModel().selectFirst(); // Select the first time option
    }


    //@FXML
    //private void onBack() throws IOException {
       // Stage stage = (Stage) Stage.getWindows().get(0);
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("restaurantpage.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        //stage.setScene(scene);

    @FXML
    private void onBookButton() throws IOException{
        // Get user inputs
        String numberOfPeople = peopleTextField.getText();
        String selectedTime = timeComboBox.getValue();

        // Validation of input
        if (numberOfPeople.isEmpty()) {
            showAlert("Error", "Please enter the number of people.");
            return;
        }

        int peopleCount;
        try {
            peopleCount = Integer.parseInt(numberOfPeople);
            if (peopleCount <= 0) {
                showAlert("Error", "Number of people must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid number format. Please enter a valid number of people.");
            return;
        }

        // If all inputs are valid, you can now proceed with booking
        showAlert("Success", "Table booked for " + peopleCount + " people at " + selectedTime + ".");
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("browsepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }
        // Add booking logic here (e.g., saving the booking details)


    @FXML
    private void onCancelButton() throws IOException{
        // Clear the form fields or return to the previous page
        peopleTextField.clear();
        timeComboBox.getSelectionModel().selectFirst(); // Reset time selection to default
        showAlert("Cancelled", "Booking process has been cancelled.");
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("browsepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }

    // Utility function to display alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


