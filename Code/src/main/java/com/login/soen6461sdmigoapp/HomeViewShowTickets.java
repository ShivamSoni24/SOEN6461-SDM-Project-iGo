package com.login.soen6461sdmigoapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.login.soen6461sdmigoapp.Database.dbURL;
import static com.login.soen6461sdmigoapp.LandingController.isUser;

public class HomeViewShowTickets implements Initializable {

    @FXML
    private TableView<Ticket> tableView;

    @FXML
    private TableColumn<Ticket, String> ticketZone;

    @FXML
    private TableColumn<Ticket, String> ticketFare;

    @FXML
    private TableColumn<Ticket, String> ticketTrip;

    @FXML
    private TableColumn<Ticket, String> ticketQuantity;

    @FXML
    private TableColumn<Ticket, String> ticketAmount;

    @FXML
    private TableColumn<Ticket, String> ticketBarcode;

    private void showAllTickets() throws SQLException {

        ObservableList<Ticket> ticketsList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM Tickets WHERE user_id = ?";
        Connection conn = DriverManager.getConnection(dbURL);
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, isUser);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int ticket_id = rs.getInt("ticket_id");
                String zone = rs.getString("zone");
                String fare_type = rs.getString("fare_type");
                String trip_type = rs.getString("trip_type");
                int num_tickets = rs.getInt("num_tickets");
                double amount = rs.getDouble("amount");
                String barcode = rs.getString("barcode");

                Ticket ticket = new Ticket(zone, fare_type, trip_type, Integer.toString(num_tickets), Double.toString(amount), barcode);
                ticket.setZone(zone);
                ticket.setFareType(fare_type);
                ticket.setTripType(trip_type);
                ticket.setQuantity(Integer.toString(num_tickets));
                ticket.setAmount(Double.toString(amount));
                ticket.setBarcode(barcode);
                ticketsList.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ticketZone.setCellValueFactory(cellData -> cellData.getValue().zoneProperty());
        ticketFare.setCellValueFactory(cellData -> cellData.getValue().fareTypeProperty());
        ticketTrip.setCellValueFactory(cellData -> cellData.getValue().tripTypeProperty());
        ticketAmount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        ticketQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        ticketBarcode.setCellValueFactory(cellData -> cellData.getValue().barcodeProperty());

        tableView.setItems(ticketsList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showAllTickets();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}
