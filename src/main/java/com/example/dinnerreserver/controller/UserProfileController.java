package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import com.example.dinnerreserver.model.SqliteUserDAO;
import com.example.dinnerreserver.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class UserProfileController {

    SqliteUserDAO UserProfileDAO;
    private User loggedInUser;

    @FXML
    private Text username;

    @FXML
    private Text email;

    @FXML
    private PasswordField password;

    public UserProfileController() {
        UserProfileDAO = new SqliteUserDAO();
    }

    // Method to receive the logged-in user
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        displayUserDetails();
    }

    // Method to display the user details on the FXML
    private void displayUserDetails() {
        if (loggedInUser != null) {
            username.setText(loggedInUser.getUsername());
            email.setText(loggedInUser.getEmail());
            password.setText(loggedInUser.getPassword());
        }
    }
    @FXML
   private void onBack() throws IOException{
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("browsepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }
}