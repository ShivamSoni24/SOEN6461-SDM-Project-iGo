package com.login.soen6461sdmigoapp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import static com.login.soen6461sdmigoapp.Database.dbURL;

public class LandingController {
    public static int isUser = -1;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label loginFailureMessage;
    @FXML
    private Label signupFailureMessage;
    @FXML
    private TextField loginUsername;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private TextField newUserFirstName;
    @FXML
    private TextField newUserSurname;
    @FXML
    private TextField newUserEmail;
    @FXML
    private TextField serialOPUS;
    @FXML
    private PasswordField newUserPassword;
    @FXML
    private DatePicker newUserDOB;
    @FXML
    private CheckBox termsAndConditionsCheck;
    @FXML
    private Button openAdminPanel;

    @FXML
    protected void onClickingSignup(ActionEvent event) {
        UtilityClass.changeScene(event, "signup-view.fxml");
    }

    @FXML
    protected void onClickingBackToLogin(ActionEvent event) {
        UtilityClass.changeScene(event, "landing-view.fxml");
    }

    @FXML
    protected void onClickingLogin(ActionEvent event) {

        isUser = Database.handleLogin(loginUsername, loginPassword);
        if(isUser != -1)
            UtilityClass.changeScene(event, "user-home-view.fxml");
        else
            loginFailureMessage.setText("Invalid credentials or user is not registered");
    }

    @FXML
    protected void onCreatingAccount(ActionEvent event) {
        boolean userRegistered = false;
        if(!termsAndConditionsCheck.isSelected())
            signupFailureMessage.setText("Please mark terms and condition box");
        else {
            userRegistered = Database.handleSignup(newUserFirstName, newUserSurname, newUserEmail, serialOPUS,
                    newUserPassword, newUserDOB);
            if(userRegistered)
                UtilityClass.changeScene(event, "user-home-view.fxml");
            else
                signupFailureMessage.setText("Failed to register the customer, please try again");
        }
    }
    @FXML
    protected void onAdminPanel(ActionEvent event) {
        UtilityClass.changeScene(event, "admin-panel.fxml");
    }
}