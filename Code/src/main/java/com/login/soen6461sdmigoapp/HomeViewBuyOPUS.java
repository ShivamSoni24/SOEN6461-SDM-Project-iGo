package com.login.soen6461sdmigoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeViewBuyOPUS implements Initializable {
    @FXML private RadioButton forStudents;
    @FXML private RadioButton forSeniorCitizen;
    @FXML private Label successMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        forStudents.setToggleGroup(toggleGroup);
        forSeniorCitizen.setToggleGroup(toggleGroup);
    }

    @FXML
    private void onBookAppointment(ActionEvent event) {
        if(forStudents.isSelected() || forSeniorCitizen.isSelected())
            successMessage.setText("An email regarding the appointment details is sent to your email");
        else
            successMessage.setText("Please select one of the option");
    }

    @FXML
    private void onBack(ActionEvent event) {
        UtilityClass.changeScene(event, "user-home-view.fxml");
    }

    @FXML
    private void onLogout(ActionEvent event) {
        UtilityClass.changeScene(event, "landing-view.fxml");
    }
}
