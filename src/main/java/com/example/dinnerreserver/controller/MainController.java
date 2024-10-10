package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import com.example.dinnerreserver.model.IUserDAO;
import com.example.dinnerreserver.model.SqliteUserDAO;
import com.example.dinnerreserver.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private IUserDAO userDAO;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField loginUsernameTextField;
    @FXML
    private TextField loginPasswordTextField;

    public MainController() {
        userDAO = new SqliteUserDAO();
    }

    @FXML
    private void onCreateAccount() throws IOException {
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            User newUser = new User(username, email, password);
            userDAO.addUser(newUser);
            clearCreateAccountFields();
            //System.out.println("Creation Successful! Welcome "+ username);
            showAlert(AlertType.INFORMATION, "Creation Successful", "Account Created, welcome " + username + ".");
            onForward();
        }
        else{
            showAlert(AlertType.INFORMATION, "Creation Unsuccessful", "Please fill in all fields.");
        }
    }

    @FXML
    private void onLogin() throws IOException {
        String username = loginUsernameTextField.getText();
        String password = loginPasswordTextField.getText();

        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // Login successful
            //System.out.println("Log In Successful!");
            showAlert(AlertType.INFORMATION, "Log in Successful", "Welcome "+ username + ".");
            onForward();
        } else {
            //System.out.println("Log in Unsuccessful");
            // Login failed
            showAlert(AlertType.INFORMATION, "Log in Unsuccessful", "Incorrect username or password.");
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onBack() throws IOException {
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landingpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);

        // Add the stylesheet
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setScene(scene);
    }


    @FXML
    private void onForward() throws IOException {
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("browsepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }


    private void clearCreateAccountFields() {
        usernameTextField.clear();
        emailTextField.clear();
        passwordTextField.clear();
    }

}