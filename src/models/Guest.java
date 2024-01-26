package models;


import database.Database;

import java.util.LinkedHashMap;

public class Guest {


    private String nationalCode;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Guest() {

    }

    public Guest(String nationalCode, String firstName, String lastName, String email, String password) {
        this.nationalCode = nationalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "nationalCode='" + nationalCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

}
