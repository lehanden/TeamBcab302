module com.example.dinnerreserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.dinnerreserver to javafx.fxml;
    exports com.example.dinnerreserver;
    exports com.example.dinnerreserver.controller;
    opens com.example.dinnerreserver.controller to javafx.fxml;
    exports com.example.dinnerreserver.model;
    opens com.example.dinnerreserver.model to javafx.fxml;
}