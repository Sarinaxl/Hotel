package controllers;

import alerts.Alert.Alert;
import database.Database;
import globals.GlobalValues;
import interfaces.InsertCallbacks;
import models.Employee;
import models.Hotel;
import models.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ManagerController extends Database {


    public Manager loginManager(String nationalCode, String password) {
        Database database = new Database();
        LinkedHashMap<String, String> query = new LinkedHashMap<>();
        query.put("nationalCode", nationalCode);
        query.put("password", password);
        ResultSet resultSet = database.selectQuery("manager", query);
        try {
            if (resultSet.first()) {
                return new
                        Manager(
                        resultSet.getString("id"),
                        resultSet.getString("nationalCode"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("salary"),
                        resultSet.getInt("bankAccountBalance")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public void createHotel(Manager manager, String name, String availableRooms, String bankAccount) {

        String[] columns = {"name", "managerId", "availableRooms", "bankAccount"};
        String[] values = {name, manager.getId(), "25", "25252525"};

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


    public ArrayList<Hotel> getHotels() {


        ResultSet result = selectQuery("hotel", new LinkedHashMap<>());
        ArrayList<Hotel> hotels = new ArrayList<>();

        try {
            while (result.next()) {
                Hotel hotel;
                hotel = new Hotel(GlobalValues.manager,
                        result.getInt("availableRooms"),
                        result.getString("id"),
                        result.getString("name"),
                        result.getString("status"),
                        result.getString("bankAccount"));
                hotels.add(hotel)
                ;
            }
        } catch (Exception ignored) {
            return new ArrayList<>();
        }


        return hotels;

    }


    public ArrayList<Employee> getEmployeesOfOwnedHotel(String hotelId) {
        ArrayList<Employee> employees = new ArrayList<>();

        // Assuming you have a column named 'hotelId' in your employee table
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        conditions.put("hotelId", hotelId);

        ResultSet result = selectQuery("employee", conditions);

        try {
            while (result.next()) {
                Employee employee = new Employee();
                // Set employee properties based on the actual columns in your 'employee' table
                employee.setId(result.getString("id"));
                employee.setFirstName(result.getString("firstName"));
                employee.setLastName(result.getString("lastName"));
                employee.setEmail(result.getString("email"));
                employee.setPassword(result.getString("password"));
                employee.setNationalCode(result.getString("nationalCode"));
                employee.setSalary(result.getDouble("salary"));
                employee.setBankAccountBalance(result.getDouble("bankAccountBalance"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }


    public ArrayList<Employee> getEmployeesNotOwnedByHotelAndOtherHotels(String hotelId) {
        ArrayList<Employee> employees = new ArrayList<>();

        // Assuming you have a column named 'hotelId' in your employee table
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        conditions.put("hotelId", "!=" + hotelId); // Modified condition to fetch employees not owned by the specified hotel

        ResultSet result = selectQuery("employee", conditions);

        try {
            while (result.next()) {
                Employee employee = new Employee();
                // Set employee properties based on the actual columns in your 'employee' table
                employee.setId(result.getString("id"));
                employee.setFirstName(result.getString("firstName"));
                employee.setLastName(result.getString("lastName"));
                employee.setEmail(result.getString("email"));
                employee.setPassword(result.getString("password"));
                employee.setNationalCode(result.getString("nationalCode"));
                employee.setSalary(result.getDouble("salary"));
                employee.setBankAccountBalance(result.getDouble("bankAccountBalance"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }



    public void deleteEmployee(String hotelId, String employeeId) {
        try {
            // Assuming you have a column named 'hotelId' and 'id' in your employee table
            LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
            conditions.put("hotelId", hotelId);
            conditions.put("id", employeeId);

            // Check if the employee with the specified hotelId and employeeId exists before attempting to delete
            ResultSet result = selectQuery("employee", conditions);
            if (result.next()) {
                // Employee exists, proceed with deletion
                String deleteQuery = "DELETE FROM employee WHERE hotelId = '" + hotelId + "' AND id = '" + employeeId + "'";
                ResultSet rowsAffected = executeQuery(deleteQuery);

                // Check if the deletion was successful
            } else {
                // Employee with the specified hotelId and employeeId doesn't exist
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
