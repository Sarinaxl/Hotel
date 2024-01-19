package controllers;

import alerts.Alert.Alert;
import database.Database;
import models.Manager;

import java.util.Objects;

public class ManagerController extends Database {


    private static Manager MANAGER = new Manager(
            "123456", "admin", "admin", "admin@gmail.com",
            "admin@12345", 0, 9999999, "20000");


    public Manager loginManager(String nationalId, String password) {
        if (Objects.equals(nationalId, MANAGER.getNationalCode()) && Objects.equals(password, MANAGER.getPassword())) {
            return MANAGER;
        } else {
            return null;
        }
    }

}
