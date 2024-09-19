package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.model.SqliteRestaurantDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BookingController {

    @FXML
    private TextField peopleTextField;

    @FXML
    private ComboBox<String> timeComboBox;

    @FXML
    private Button bookButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void initialize() {
        // Initialize default values, if necessary
        timeComboBox.getSelectionModel().selectFirst(); // Select the first time option
    }

    @FXML
    private void onBookButton() {
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
        // Add booking logic here (e.g., saving the booking details)
    }

    @FXML
    private void onCancelButton() {
        // Clear the form fields or return to the previous page
        peopleTextField.clear();
        timeComboBox.getSelectionModel().selectFirst(); // Reset time selection to default
        showAlert("Cancelled", "Booking process has been cancelled.");
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


