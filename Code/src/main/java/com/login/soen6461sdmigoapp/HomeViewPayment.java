package com.login.soen6461sdmigoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.login.soen6461sdmigoapp.Database.dbURL;
import static com.login.soen6461sdmigoapp.HomeViewBuyTicket.ticketData;
import static com.login.soen6461sdmigoapp.LandingController.isUser;

public class HomeViewPayment implements Initializable {
    @FXML
    private RadioButton cardPay;
    @FXML
    private RadioButton cashPay;
    @FXML
    private Label paymentSuccess;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        cardPay.setToggleGroup(toggleGroup);
        cashPay.setToggleGroup(toggleGroup);
    }

    private void addDataToDatabase() throws SQLException {
        String sql = "INSERT INTO Tickets (user_id, zone, fare_type, trip_type, num_tickets, amount, barcode) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DriverManager.getConnection(dbURL);
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, isUser);
            pstmt.setString(2, ticketData.getZoneData());
            pstmt.setString(3, ticketData.getFareTypeData());
            pstmt.setString(4, ticketData.getTripTypeData());
            pstmt.setInt(5, ticketData.getNumberOfTickets());
            pstmt.setDouble(6, ticketData.getAmount());
            pstmt.setString(7, ticketData.getBarcodeData());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        UtilityClass.changeScene(event, "user-home-view.fxml");
    }

    @FXML
    private void onLogout(ActionEvent event) {
        UtilityClass.changeScene(event, "landing-view.fxml");
    }

@FXML
private void onPay(ActionEvent event) throws SQLException {
    if(cardPay.isSelected() || cashPay.isSelected()) {
        paymentSuccess.setText("Payment Successful!!!");
        addDataToDatabase();
    }
    else {
        paymentSuccess.setText("Select a method of pay");
    }
}
}
