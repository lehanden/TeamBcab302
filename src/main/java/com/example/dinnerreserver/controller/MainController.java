package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.model.IUserDAO;
import com.example.dinnerreserver.model.SqliteUserDAO;
import com.example.dinnerreserver.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    private void onCreateAccount() {
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            User newUser = new User(username, email, password);
            userDAO.addUser(newUser);
            clearCreateAccountFields();
        }
    }

    @FXML
    private void onLogin() {
        String username = loginUsernameTextField.getText();
        String password = loginPasswordTextField.getText();

        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // Login successful, handle accordingly
        } else {
            // Login failed, handle accordingly
        }
    }

    private void clearCreateAccountFields() {
        usernameTextField.clear();
        emailTextField.clear();
        passwordTextField.clear();
    }
}