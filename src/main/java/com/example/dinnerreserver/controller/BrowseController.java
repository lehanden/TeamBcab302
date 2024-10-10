package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import com.example.dinnerreserver.controller.RestaurantController;
import com.example.dinnerreserver.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BrowseController {

    SqliteRestaurantDAO restaurantDAO;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox restaurantContainer;

    private List<Restaurant> restaurantList = new ArrayList<>();

    public User loggedInUser;

    @FXML
    public void initialize() {
        loggedInUser = MainController.loggedInUser;
        restaurantDAO = new SqliteRestaurantDAO();
        loadRestaurantsFromDB();
        populateRestaurantUI();
    }

    @FXML
    private void onBack() throws IOException {
        showAlert("Log Out", "You have been successfully logged out.");
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landingpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }
    @FXML
    private void onProfile() throws IOException {
        if (loggedInUser != null) {

            Stage stage = (Stage) Stage.getWindows().get(0);
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userprofilepage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 400);
            UserProfileController userProfileController = fxmlLoader.getController();
            userProfileController.setLoggedInUser(loggedInUser);
            stage.setScene(scene);
        }
        else {
            // testing a user is logged in
            System.out.println("No user is logged in.");
        }
    }

    // Load restaurants from the database
    private void loadRestaurantsFromDB() {
        String url = "jdbc:sqlite:SBEats.db"; // Path to your SQLite database
        String query = "SELECT id, name, address, description, rating, imageSource FROM restaurants"; // SQL query to get restaurant data

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String description = rs.getString("description");
                Float rating = rs.getFloat("rating");
                String imageSource = rs.getString("imageSource");

                Restaurant restaurant = new Restaurant(id, name, address, description, rating, imageSource); // Create a Restaurant object
                restaurantList.add(restaurant); // Add the restaurant to the list
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Populate the ScrollPane with restaurant data
    private void populateRestaurantUI() {
        restaurantContainer.getChildren().clear(); // Clear any existing content in the VBox

        for (Restaurant restaurant : restaurantList) {
            Label nameLabel = new Label("Name: " + restaurant.getName());
            Label addressLabel = new Label("Address: " + restaurant.getAddress());
            Label descriptionLabel = new Label("Description: " + restaurant.getDescription());
            Label ratingLabel = new Label("Rating: " + restaurant.getRating());

            // Create a view button for each restaurant
            Button viewButton = new Button("View");
            viewButton.setOnAction(event -> {
                try {
                    openRestaurantPage(restaurant.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Create a VBox for each restaurant entry
            VBox restaurantBox = new VBox(nameLabel, addressLabel, descriptionLabel, ratingLabel, viewButton);
            restaurantBox.setSpacing(10);
            restaurantBox.setStyle("-fx-padding: 10; -fx-border-style: solid inside; -fx-border-width: 1; -fx-border-color: lightgray;");

            // Add the restaurant VBox to the main container
            restaurantContainer.getChildren().add(restaurantBox);
        }
    }


    private void openRestaurantPage(int restaurantId) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("restaurantpage.fxml"));
        Scene scene = new Scene(loader.load(), 640, 400);
        RestaurantController controller = loader.getController();
        controller.selectRestaurant(restaurantId);
        Stage stage = (Stage) Stage.getWindows().get(0);
        stage.setScene(scene);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        if (loggedInUser != null) {
            System.out.println("Logged in user: " + loggedInUser.getUsername());
        } else {
            System.out.println("No user is logged in.");
        }
    }
}
