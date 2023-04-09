package com.login.soen6461sdmigoapp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.login.soen6461sdmigoapp.Database.dbURL;

public class AdminView implements Initializable{

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> userFirstName;

    @FXML
    private TableColumn<User, String> userSurname;

    @FXML
    private TableColumn<User, String> userEmail;

    @FXML
    private TableColumn<User, String> userOpus;

    @FXML
    private TableColumn<User, String> userPassword;

    @FXML
    private TableColumn<User, String> userDoB;

    @FXML
    public void loadUsersFromDatabase() {
        ObservableList<User> userList = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(dbURL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                String firstName = rs.getString("firstname");
                String surName = rs.getString("surname");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String dob = rs.getString("dob");
                String opus = rs.getString("opus");

                User user = new User(firstName, surName, email, password, opus, dob);
                user.setFirstName(firstName);
                user.setSurName(surName);
                user.setEmail(email);
                user.setPassword(password);
                user.setOpus(opus);
                user.setDob(dob);
                userList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        userFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        userSurname.setCellValueFactory(cellData -> cellData.getValue().surNameProperty());
        userEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        userPassword.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        userOpus.setCellValueFactory(cellData -> cellData.getValue().opusProperty());
        userDoB.setCellValueFactory(cellData -> cellData.getValue().dobProperty());

        tableView.setItems(userList);
    }
//    @FXML
//    protected void onAdminPanel(ActionEvent event) {
//        Platform.runLater(() -> {
//            try {
//                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin-panel.fxml")));
//                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                System.out.println("Within Admin Panel");
//                stage.setOnShown(event1 -> {
//                    System.out.println("Within inside");
//                    loadUsersFromDatabase();
//                });
//                stage.show();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
    @FXML
    private void onExitToMainMenu(ActionEvent event) {
        UtilityClass.changeScene(event,"landing-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUsersFromDatabase();
    }
}
