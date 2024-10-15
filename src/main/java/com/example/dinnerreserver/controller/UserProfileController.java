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

/**
 * A controller for the user profile page for the logged-in user
 */
public class UserProfileController {

    SqliteUserDAO UserProfileDAO;
    private User loggedInUser;

    @FXML
    private Text username;

    @FXML
    private Text email;

    @FXML
    private PasswordField password;

    /**
     * Creates a new instance of the user database access object
     */
    public UserProfileController() {
        UserProfileDAO = new SqliteUserDAO();
    }

    // logged-in user
    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
        displayUserDetails();
    }


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