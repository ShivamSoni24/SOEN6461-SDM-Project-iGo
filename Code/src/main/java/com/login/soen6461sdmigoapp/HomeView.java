package com.login.soen6461sdmigoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeView {
    @FXML
    private void onLogout(ActionEvent event) {
        UtilityClass.changeScene(event, "landing-view.fxml");
    }

    @FXML
    private void onBuyNewOPUS(ActionEvent event) {
        UtilityClass.changeScene(event, "user-home-buy-opus-view.fxml");
    }

    @FXML
    private void onBuyTicket(ActionEvent event) {
        UtilityClass.changeScene(event, "user-home-buy-ticket-view.fxml");
    }

    @FXML
    private void onRechargeOPUS(ActionEvent event) {
        UtilityClass.changeScene(event, "user-home-show-tickets.fxml");
    }
}
