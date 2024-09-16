package com.example.dinnerreserver.controller;

import com.example.dinnerreserver.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserProfileController {
    @FXML
    private void onBack() throws IOException {
        Stage stage = (Stage) Stage.getWindows().get(0);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landingpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setScene(scene);
    }
}
