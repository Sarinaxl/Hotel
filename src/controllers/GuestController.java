package controllers;

import database.Database;
import interfaces.InsertCallbacks;
import models.Guest;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

public class GuestController extends Database {

    public void createGuest(Guest guest) {
        String[] guestTableColums = getColumns("guest");
        String[] values = new String[]{guest.getNationalCode(), guest.getFirstName(), guest.getLastName(), guest.getEmail(), guest.getPassword()};

        insertIntoTable(
                "guest",
                guestTableColums,
                values, new InsertCallbacks() {
                    @Override
                    public void onSuccess(int affectedRows) {
                        JOptionPane.showMessageDialog(null,
                                "You are Succefully Registered!",
                                "Success!",
                                JOptionPane.PLAIN_MESSAGE);
                    }

                    @Override
                    public void onError(Exception e) {
                        JOptionPane.showMessageDialog(null,
                                e.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
        );
    }

    public Guest loginGuest(String nationalCode, String password) {
        LinkedHashMap<String, String> conditions = new LinkedHashMap();
        conditions.put("password", password);
        conditions.put("nationalCode", nationalCode);
        ResultSet test = selectQuery("guest", conditions);
        return null;
    }
}
