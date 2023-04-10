package com.login.soen6461sdmigoapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LandingPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("landing-view.fxml")));
            Scene scene = new Scene(root, 600, 400);
            stage.getIcons().add(new Image(Objects.requireNonNull(LandingPage.class.getResourceAsStream("logo.jpg"))));
            stage.setTitle("iGo - The online STM helper!!!");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Database.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}