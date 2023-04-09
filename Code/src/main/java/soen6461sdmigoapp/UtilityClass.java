package com.login.soen6461sdmigoapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UtilityClass {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    private UtilityClass() {
    }

    public static void changeScene(ActionEvent event, String fxmlPath) {
        Platform.runLater(() -> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(UtilityClass.class.getResource(fxmlPath)));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
