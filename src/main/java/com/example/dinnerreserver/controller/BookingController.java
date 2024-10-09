package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import com.example.dinnerreserver.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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



    @FXML
    public void initialize() {
        Restaurant selectedRestaurant = SharedData.getInstance().getSelectedRestaurant();

        if (selectedRestaurant != null) {
            name.setText(selectedRestaurant.getName());
            address.setText(selectedRestaurant.getAddress());
        }
        // Initialize default values, if necessary
        timeComboBox.getSelectionModel().selectFirst(); // Select the first time option
    }

    public void setRestaurant(Restaurant restaurant) {
        if (restaurant != null) {
            name.setText(restaurant.getName());
            address.setText(restaurant.getAddress());
        }
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



    @FXML
    private void onBack() throws IOException{
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



