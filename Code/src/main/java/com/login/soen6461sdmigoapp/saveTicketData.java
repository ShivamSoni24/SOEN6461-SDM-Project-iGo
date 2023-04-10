package com.login.soen6461sdmigoapp;

import java.util.Random;

public class saveTicketData {
    private String zoneData;
    private String fareTypeData;
    private String tripTypeData;
    private int numberOfTickets;
    private double amount;

    public String getBarcodeData() {
        return barcodeData;
    }

    public void setBarcodeData() {
        StringBuilder sb = new StringBuilder();

        // Add the first digit (always 8)
        sb.append("8");

        // Generate 11 more random digits
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        this.barcodeData = sb.toString();
    }

    private String barcodeData;

    public saveTicketData() {
        // Default constructor
    }

    public String getZoneData() {
        return zoneData;
    }

    public void setZoneData(String zoneData) {
        this.zoneData = zoneData;
    }

    public String getFareTypeData() {
        return fareTypeData;
    }

    public void setFareTypeData(String fareTypeData) {
        this.fareTypeData = fareTypeData;
    }

    public String getTripTypeData() {
        return tripTypeData;
    }

    public void setTripTypeData(String tripTypeData) {
        this.tripTypeData = tripTypeData;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
