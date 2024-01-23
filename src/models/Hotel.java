package models;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private Manager manager;
    private int availableRooms;
    private String id;
    private String name;
    private String status;
    private String bankAccount;

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }


    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Hotel(Manager manager, int availableRooms, String id, String name, String status, String bankAccount) {
        this.manager = manager;
        this.availableRooms = availableRooms;
        this.id = id;
        this.name = name;
        this.status = status;
        this.bankAccount = bankAccount;
    }


}
