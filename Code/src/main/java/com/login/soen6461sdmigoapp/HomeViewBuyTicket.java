package com.login.soen6461sdmigoapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.hssf.usermodel.*;

public class HomeViewBuyTicket implements Initializable {
    @FXML private ComboBox numberOfTickets;
    @FXML private ComboBox selectZone;
    @FXML private ComboBox selectFareType;
    @FXML private ComboBox selectTripType;
    @FXML private Label amountTobePaid;
    private double amount = 0;
    private int noOfTickets = 1;
    public static saveTicketData ticketData = new saveTicketData();

    private final ObservableList<String> zones = FXCollections.observableArrayList(
            "Zone A",
            "Zone A and B",
            "Zone A, B and C",
            "Zone A, B, C and D",
            "Unlimited All Zones"
    );
    private final ObservableList<String> fareTypes = FXCollections.observableArrayList(
            "Regular",
            "Student from age 6 to 17",
            "Student from 18 and above",
            "Citizen with age 65 and Over"
    );
    private ObservableList<String> tripTypes = FXCollections.observableArrayList();

    private void updateAmount() {
        try {
            amount = Double.parseDouble(loadAmount(selectZone.getSelectionModel().getSelectedItem().toString(),
                    selectFareType.getSelectionModel().getSelectedIndex(),
                    selectTripType.getSelectionModel().getSelectedItem().toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        noOfTickets = Integer.parseInt(numberOfTickets.getSelectionModel().getSelectedItem().toString());
        amountTobePaid.setText("$ " + (amount * noOfTickets));
    }
    private void tripListener() {
        tripTypes.clear();
        loadExcelData(selectZone.getSelectionModel().getSelectedItem().toString());
        updateAmount();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectZone.setItems(zones);
        selectFareType.setItems(fareTypes);
        numberOfTickets.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        selectZone.getSelectionModel().selectFirst();
        selectFareType.getSelectionModel().selectFirst();
        numberOfTickets.getSelectionModel().selectFirst();
        tripListener();


        selectZone.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tripListener();
        });
        selectFareType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tripListener();
        });
        selectTripType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateAmount();
        });
        numberOfTickets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateAmount();
        });
    }

    @FXML
    private void loadExcelData(String zone) {
        // load data from the Excel file
        int rows = 0;
        String fileName = "src/main/resources/com/login/soen6461sdmigoapp/fares/" + zone + ".xlsx";
        File file = new File(fileName);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
                XSSFSheet sheet = workbook.getSheetAt(0);

                // clear and add new items to the triptypes combobox
                tripTypes.clear();
                rows = sheet.getPhysicalNumberOfRows();
                for (int i = 0; i < rows; i++) {
                    if (sheet.getRow(i).getCell(selectFareType.getSelectionModel().getSelectedIndex() + 1) != null) {
                        tripTypes.add(sheet.getRow(i).getCell(0).getStringCellValue());
                    }
                }
                selectTripType.setItems(tripTypes);
                selectTripType.getSelectionModel().selectFirst();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private String loadAmount(String zone, int faretype, String triptype) throws IOException {
        // Open the Excel file
        FileInputStream inputStream = new FileInputStream(new File("src/main/resources/com/login/soen6461sdmigoapp/fares/" + zone + ".xlsx"));
        Workbook workbook = new XSSFWorkbook(inputStream);

        // Get the first sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Iterate through each row and column to find the given text
        for (Row row : sheet) {
            if(row.getCell(0).getCellType()== CellType.STRING && row.getCell(0).getStringCellValue().equals(triptype)) {
                return row.getCell(faretype + 1).toString();
            }
        }

        // Close the Excel file
        workbook.close();
        inputStream.close();
        return "";
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
    private void onProceedToPayment(ActionEvent event) {
        ticketData.setZoneData(selectZone.getSelectionModel().getSelectedItem().toString());
        ticketData.setFareTypeData(selectFareType.getSelectionModel().getSelectedItem().toString());
        ticketData.setTripTypeData(selectTripType.getSelectionModel().getSelectedItem().toString());
        ticketData.setNumberOfTickets(Integer.parseInt(numberOfTickets.getSelectionModel().getSelectedItem().toString()));
        ticketData.setAmount(amount);
        ticketData.setBarcodeData();

        UtilityClass.changeScene(event, "user-home-payment-view.fxml");
    }
}
