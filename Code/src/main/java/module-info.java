module com.example.soen6461sdmigoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    opens com.login.soen6461sdmigoapp to javafx.fxml;
    exports com.login.soen6461sdmigoapp;
}