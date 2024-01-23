package models;

import database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class Manager extends Guest {


    private double salary;
    private double bankAccountBalance;
    private String id;
    String employeeId;





    public Manager(
            String id,
            String nationalCode,
            String firstName,
            String lastName,
            String email,
            String password,
            double salary,
            double bankAccountBalance) {
        super(nationalCode, firstName, lastName, email, password);
        this.salary = salary;
        this.bankAccountBalance = bankAccountBalance;
        this.employeeId = employeeId;
        this.id = id;
    }


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBankAccountBalance() {
        return bankAccountBalance;
    }

    public void setBankAccountBalance(double bankAccountBalance) {
        this.bankAccountBalance = bankAccountBalance;
    }

    public String getEmployeeId() {
        return employeeId;
    }


    public String getId() {
        return this.id;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }


}



