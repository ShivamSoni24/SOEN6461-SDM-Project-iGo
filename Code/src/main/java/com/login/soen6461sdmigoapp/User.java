package com.login.soen6461sdmigoapp;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private final StringProperty firstName;
    private final StringProperty surName;
    private final StringProperty email;
    private final StringProperty password;
    private final StringProperty opus;
    private final StringProperty dob;

    public User(String firstName, String surName, String email, String password, String opus, String dob) {
        this.firstName = new SimpleStringProperty(firstName);
        this.surName = new SimpleStringProperty(surName);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.opus = new SimpleStringProperty(opus);
        this.dob = new SimpleStringProperty(dob);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getSurName() {
        return surName.get();
    }

    public void setSurName(String surName) {
        this.surName.set(surName);
    }

    public StringProperty surNameProperty() {
        return surName;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getOpus() {
        return opus.get();
    }

    public void setOpus(String opus) {
        this.opus.set(opus);
    }

    public StringProperty opusProperty() {
        return opus;
    }

    public String getDob() {
        return dob.get();
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public StringProperty dobProperty() {
        return dob;
    }
}
