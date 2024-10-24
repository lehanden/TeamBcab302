package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import com.example.dinnerreserver.controller.RestaurantController;
import com.example.dinnerreserver.model.RestaurantManager;
import com.example.dinnerreserver.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

/**
 * A controller for the browse page using restaurants from the database
 */
public class BrowseController {

    SqliteRestaurantDAO restaurantDAO;

    private RestaurantManager restaurantManager;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox restaurantContainer;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> sort;


    private List<Restaurant> restaurantList = new ArrayList<>();

    private String currentSortOption = null;
    private String currentSearchQuery = "";

    public User loggedInUser;

    /**
     * Loads the browse page with restaurants from the database
     */
    @FXML
    public void initialize() {
        loggedInUser = MainController.loggedInUser;
//        restaurantDAO = new SqliteRestaurantDAO();
//        loadRestaurantsFromDB();
//        populateRestaurantUI();
        restaurantManager = new RestaurantManager(new SqliteRestaurantDAO());
        loadRestaurantsFromDB();
        //populateRestaurantUI(restaurantList);
        applySearchAndSort();

        //Search listener
        //searchField.setOnAction(event -> searchRestaurants());
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            currentSearchQuery = newValue;
            applySearchAndSort();
        });

        //Sort Listener
        sort.getItems().addAll("Alphabetical", "Rating");
        sort.valueProperty().addListener((observable, oldValue, newValue) ->{
            currentSortOption = newValue;
            applySearchAndSort();
        });
    }
//    @FXML
//    private void onBack() throws IOException {
//        Stage stage = (Stage) Stage.getWindows().get(0);
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landingpage.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
//
//        // Add the stylesheet
//        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
//        scene.getStylesheets().add(stylesheet);
//
//        stage.setScene(scene);
//    }

    @FXML
    private void onBack() throws IOException {
        showAlert("Log Out", "You have been successfully logged out.");
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landingpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
                // Add the stylesheet
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);
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
        restaurantList = restaurantManager.getAllRestaurants();
//        String url = "jdbc:sqlite:SBEats.db"; // Path to your SQLite database
//        String query = "SELECT id, name, address, description, rating, imageSource FROM restaurants"; // SQL query to get restaurant data
//
//        try (Connection conn = DriverManager.getConnection(url);
//             PreparedStatement stmt = conn.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String address = rs.getString("address");
//                String description = rs.getString("description");
//                Float rating = rs.getFloat("rating");
//                String imageSource = rs.getString("imageSource");
//
//                Restaurant restaurant = new Restaurant(id, name, address, description, rating, imageSource); // Create a Restaurant object
//                restaurantList.add(restaurant); // Add the restaurant to the list
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void applySearchAndSort(){
        List<Restaurant> filteredRestaurants = searchRestaurants(currentSearchQuery);
        List<Restaurant> sortedRestaurants = sortRestaurants(filteredRestaurants);
        populateRestaurantUI(sortedRestaurants);
    }

    private List<Restaurant> searchRestaurants(String query){
        if(query == null || query.isEmpty()){
            return new ArrayList<>(restaurantList);
        }else{
            return restaurantManager.searchRestaurants(query);
        }
    }

    private List<Restaurant> sortRestaurants(List<Restaurant> restaurantsToSort){

        List<Restaurant> mutableList = new ArrayList<>(restaurantsToSort);

        if(currentSortOption != null) {
            if (currentSortOption.equals("Alphabetical")) {
                mutableList.sort((r1, r2) -> r1.getName().compareToIgnoreCase(r2.getName()));
            } else if (currentSortOption.equals("Rating")) {
                mutableList.sort((r1, r2) -> Float.compare(r2.getRating(), r1.getRating()));
            }
        }
        return mutableList;
    }

    // Populate the ScrollPane with restaurant data
    private void populateRestaurantUI(List<Restaurant> restaurants) {
        restaurantContainer.getChildren().clear(); // Clear any existing content in the VBox

        for (Restaurant restaurant : restaurants) {
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

    /**
     * Updates the logged-in user
     * @param user The currently logged-in user
     */
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        if (loggedInUser != null) {
            System.out.println("Logged in user: " + loggedInUser.getUsername());
        } else {
            System.out.println("No user is logged in.");
        }
    }
}
