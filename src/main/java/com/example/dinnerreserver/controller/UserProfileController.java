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

    @FXML
    private void onBack() throws IOException{
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("browsepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }

    public UserProfileController() {
        UserProfileDAO = new SqliteUserDAO();
    }

    @FXML
    private Text username;

    @FXML
    private Text email;

    @FXML
    private PasswordField password;

    @FXML
    public void selectUser(Integer userId)
    {
        User user = UserProfileDAO.getUser(userId);
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
    }

}