package controllers;

import database.Database;
import interfaces.InsertCallbacks;
import models.Guest;
import utils.ResultSetMapper;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class GuestController extends Database {


    ResultSetMapper<Guest> resultSetMapper = new ResultSetMapper<>();

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

        try {
            LinkedHashMap<String, String> conditions = new LinkedHashMap();
            conditions.put("password", password);
            conditions.put("nationalCode", nationalCode);
            ResultSet result = selectQuery("guest", conditions);
            if (result.first()){
                String email = result.getString("email");
                String nationalCodee = result.getString("nationalCode");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String emaill = result.getString("email");
                String passwordd = result.getString("password");
                return new Guest(nationalCode,firstName,lastName,email,password);

            }
            return null;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
