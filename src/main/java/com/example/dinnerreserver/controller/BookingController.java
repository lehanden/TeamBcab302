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
import com.example.dinnerreserver.model.Restaurant;

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

    private IBookingDAO bookingDAO;
    private User loggedInUser;
    private Restaurant selectedRestaurant;

    public BookingController() {

        restaurantDAO = new SqliteRestaurantDAO();
        bookingDAO = new SqliteBookingDAO();
    }



    @FXML
    public void initialize() {
        loggedInUser = MainController.loggedInUser;
        selectedRestaurant = SharedData.getInstance().getSelectedRestaurant();

        if (selectedRestaurant != null) {
            name.setText(selectedRestaurant.getName());
            address.setText(selectedRestaurant.getAddress());
        }

        timeComboBox.getSelectionModel().selectFirst(); // Select the first time option
    }

    public void setRestaurant(Restaurant restaurant) {
        this.selectedRestaurant = restaurant;
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
        String peopleInput = peopleTextField.getText().trim(); // Get input and remove whitespace
        int numberOfPeople;
        String selectedTime = timeComboBox.getValue();


        if (loggedInUser == null || selectedRestaurant == null) {
            // testing errors
            System.out.println(selectedRestaurant);
            System.out.println(loggedInUser);
            return;
        }

        // Validation of input
        try {
            numberOfPeople = Integer.parseInt(peopleInput); // Try to parse the input
        } catch (NumberFormatException e) { // Handle parsing error
            showAlert("Error", "Please enter a valid number for people.");
            return;
        }


        Booking booking = new Booking(loggedInUser.getId(), selectedRestaurant.getId(), numberOfPeople, selectedTime);

        bookingDAO.addBooking(booking);

        // If all inputs are valid, proceed
        showAlert("Success", "Table booked for " + numberOfPeople + " people at " + selectedTime + ".");
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("browsepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);


    }



    @FXML
    private void onCancelButton() throws IOException{
        peopleTextField.clear();
        timeComboBox.getSelectionModel().selectFirst(); // Reset time selection to default
        showAlert("Cancelled", "Booking process has been cancelled.");
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("browsepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }

    //alert message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


