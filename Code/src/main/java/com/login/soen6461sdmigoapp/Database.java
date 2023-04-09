package com.login.soen6461sdmigoapp;

import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Database {
    private static Connection conn;
    public static final String dbURL = "jdbc:sqlite:src/main/resources/com/login/soen6461sdmigoapp/authDB/userDB.db";

    private static void createTable() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "firstname TEXT NOT NULL, " +
                    "surname TEXT NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "email TEXT NOT NULL, " +
                    "dob TEXT NOT NULL, " +
                    "opus TEXT)";
            stmt.execute(sql);
        }

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }

        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Tickets (" +
                    "ticket_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INT, " +
                    "zone VARCHAR(255), " +
                    "fare_type VARCHAR(255), " +
                    "trip_type VARCHAR(255), " +
                    "num_tickets INT, " +
                    "amount DOUBLE, " +
                    "barcode VARCHAR(255)," +
                    "FOREIGN KEY (user_id) REFERENCES users(id))";
            stmt.execute(sql);
        }

//        try (Statement stmt = conn.createStatement()) {
//            String sql = "ALTER TABLE Tickets ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)";
//            stmt.execute(sql);
//        }
    }
    public static void initialize() {
        try {
            conn = DriverManager.getConnection(dbURL);
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int verifyUser(String email, String password) throws SQLException {
        String sql = "SELECT id FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1; // indicates that no user was found with the given email and password
                }
            }
        }
    }

    public static int handleLogin(TextField emailField, PasswordField passwordField) {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            return verifyUser(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean handleSignup(TextField firstNameField,
                                       TextField surnameField,
                                       TextField emailField,
                                       TextField OPUSserialNo,
                                       PasswordField passwordField,
                                       DatePicker dobField) {

        String firstname = firstNameField.getText();
        String surname = surnameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String opusSerialNumber = OPUSserialNo.getText();

        if (firstname.isEmpty() || surname.isEmpty() || password.isEmpty() || email.isEmpty() || (dobField.getValue() == null)) {
            // Show error message if any required field is empty
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all required fields.");
            alert.showAndWait();
            return false;
        }

        // Connect to database
        try (Connection conn = DriverManager.getConnection(dbURL)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = dobField.getValue();
            String formattedDate = date.format(formatter);


            // Create a prepared statement for inserting a new user
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (firstname, surname, password, email, dob, opus) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, firstname);
            stmt.setString(2, surname);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.setString(5, formattedDate);
            stmt.setString(6, opusSerialNumber);

            // Execute the statement and get the number of affected rows
            int rowsInserted = stmt.executeUpdate();
            Alert alert;
            if (rowsInserted > 0) {
                // Show success message if the user was successfully added
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("User registered successfully.");
                return true;
            } else {
                // Show error message if the user was not added
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to register user.");
            }
            alert.showAndWait();

            // Close the statement
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Show error message if there was an error connecting to the database
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to connect to database.");
            alert.showAndWait();
        }
        return false;
    }
}
