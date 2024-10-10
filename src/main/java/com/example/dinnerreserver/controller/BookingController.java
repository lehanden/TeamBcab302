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
import java.util.ArrayList;
import java.util.List;

import com.example.dinnerreserver.model.Restaurant;
import com.example.dinnerreserver.model.SqliteBookingDAO;

public class BookingController {

    private List<String> disabledTimeSlots = new ArrayList<>();

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

        // Populate the time slots in the ComboBox
        timeComboBox.getItems().addAll("5:00 PM", "5:30 PM", "6:00 PM", "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM", "8:30 PM", "9:00 PM", "9:30 PM");

        // Disable full time slots (50 people or more)
        for (String timeSlot : timeComboBox.getItems()) {
            int totalPeople = bookingDAO.countBookingsForTimeSlot(selectedRestaurant.getId(), timeSlot);

            // Check if this slot is already disabled or if it exceeds 50 people
            if (totalPeople >= 50 && !disabledTimeSlots.contains(timeSlot)) {
                disabledTimeSlots.add(timeSlot);  // Add it to the list of permanently disabled slots
            }
        }

        // Set the cell factory once to check the list of permanently disabled slots
        timeComboBox.setCellFactory(lv -> new ListCell<String>() {
            @Override
            public void updateItem(String time, boolean empty) {
                super.updateItem(time, empty);
                if (empty || time == null) {
                    setText(null);
                } else {
                    setText(time);
                    if (disabledTimeSlots.contains(time)) {
                        setDisable(true);  // Permanently disable the time slot
                        setStyle("-fx-opacity: 0.5;");  // Gray out the disabled time slot
                    }
                }
            }
        });

        timeComboBox.getSelectionModel().selectFirst(); // Select the first available time slot by default
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
        showAlert("Success", "Table booked for " + numberOfPeople + " people at " + selectedTime + " today.");
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


