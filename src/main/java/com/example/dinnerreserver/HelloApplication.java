package com.example.dinnerreserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    // Constants defining the window title and size
    public static final String TITLE = "SB Eats";
    //public static final int WIDTH = 1000;
    //public static final int HEIGHT = 500;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landingpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}