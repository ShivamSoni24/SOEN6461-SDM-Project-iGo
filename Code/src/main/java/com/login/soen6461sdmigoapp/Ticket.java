package com.login.soen6461sdmigoapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ticket {
    private final StringProperty zone;
    private final StringProperty fareType;
    private final StringProperty tripType;
    private final StringProperty quantity;
    private final StringProperty amount;
    private final StringProperty barcode;

    public String getZone() {
        return zone.get();
    }

    public StringProperty zoneProperty() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone.set(zone);
    }

    public String getFareType() {
        return fareType.get();
    }

    public StringProperty fareTypeProperty() {
        return fareType;
    }

    public void setFareType(String fareType) {
        this.fareType.set(fareType);
    }

    public String getTripType() {
        return tripType.get();
    }

    public StringProperty tripTypeProperty() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType.set(tripType);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public StringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getBarcode() {
        return barcode.get();
    }

    public Ticket(String zone, String fareType, String tripType, String quantity, String amount, String barcode) {
        this.zone = new SimpleStringProperty(zone);
        this.fareType = new SimpleStringProperty(fareType);
        this.tripType = new SimpleStringProperty(tripType);
        this.quantity = new SimpleStringProperty(quantity);
        this.amount = new SimpleStringProperty(amount);
        this.barcode = new SimpleStringProperty(barcode);
    }

    public StringProperty barcodeProperty() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }
}
