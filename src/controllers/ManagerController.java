package controllers;

import alerts.Alert.Alert;
import database.Database;
import interfaces.InsertCallbacks;
import models.Manager;

import java.sql.ResultSet;
import java.util.Objects;

public class ManagerController extends Database {


    public static Manager MANAGER = new Manager(
            "11110000", "admin", "admin", "admin@gmail.com",
            "admin@ee", 50000, 9999999);


    public Manager loginManager(String nationalId, String password) {
        if (Objects.equals(nationalId, MANAGER.getNationalCode()) && Objects.equals(password, MANAGER.getPassword())) {
            return MANAGER;
        } else {
            return null;
        }
    }


    public void createHotel(Manager manager, double bankAccount) {
        String[] columns = {"managerId", "availableRooms", "bankAccount"};
        String[] values = {"1", "0", "200000"};

        insertIntoTable("hotel",
                columns,
                values,
                new InsertCallbacks() {
                    @Override
                    public void onSuccess(int affectedRows) {
                        Alert.showSuccess("Hotel Created !");
                    }

                    @Override
                    public void onError(Exception e) {
                        Alert.showError("Hotel Creation Failed !");

                    }
                });
    }

    public void getAllHotels() {
        try {
            ResultSet resultSet = selectQuery("hotel", null);
            System.out.println(resultSet.getFetchSize());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
