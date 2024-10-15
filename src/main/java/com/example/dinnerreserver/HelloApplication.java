package com.example.dinnerreserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A simple class to launch the application
 */
public class HelloApplication extends Application {

    /**
     * The application title
     */
    // Constants defining the window title and size
    public static final String TITLE = "SB Eats";
    //public static final int WIDTH = 1000;
    //public static final int HEIGHT = 664;

    /**
     * Loads the landing page
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landingpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Start the application
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}