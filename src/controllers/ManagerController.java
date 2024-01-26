package controllers;

import UI.manager.Reserves;
import alerts.Alert.Alert;
import database.Database;
import globals.GlobalValues;
import interfaces.InsertCallbacks;
import models.*;

import java.sql.PreparedStatement;
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

        insertIntoTableQuery("hotel",
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

            System.out.println(result.getFetchSize());

            while (result.next()) {
                Hotel hotel;
                LinkedHashMap<String, String> condition = new LinkedHashMap<>();

                // Getting Hotel's Rooms
                condition.put("hotelId", result.getString("id"));
                ResultSet roomsResult = selectQuery("room", condition);

                ArrayList<Room> rooms = new ArrayList<>();
                while (roomsResult.next()) {
                    if (roomsResult.getInt("isReserved") == 1) continue;
                    Room room = new Room(
                            roomsResult.getString("reservedBy"),
                            roomsResult.getString("id"),
                            roomsResult.getInt("roomNumber"),
                            roomsResult.getInt("numberOfBeds"),
                            roomsResult.getInt("isReserved"),
                            roomsResult.getInt("hotelId"),
                            roomsResult.getString("name"),
                            roomsResult.getDouble("price")
                    );
                    rooms.add(room);
                }


                hotel = new Hotel(GlobalValues.manager,
                        result.getInt("availableRooms"),
                        result.getString("id"),
                        result.getString("name"),
                        result.getString("status"),
                        result.getString("bankAccount"),
                        rooms);

                hotels.add(hotel);
            }
        } catch (Exception ignored) {
            return new ArrayList<>();
        }


        return hotels;

    }

    public ArrayList<Room> getRooms() {


        ResultSet result = selectQuery("room", new LinkedHashMap<>());
        ArrayList<Room> rooms = new ArrayList<>();

        try {
            while (result.next()) {
                Room room = new Room(
                        result.getString("reservedBy"),
                        result.getString("id"),
                        result.getInt("roomNumber"),
                        result.getInt("numberOfBeds"),
                        result.getInt("isReserved"),
                        result.getInt("hotelId"),
                        result.getString("name"),
                        result.getDouble("price")
                );
                rooms.add(room);
            }
        } catch (Exception ignored) {
            return new ArrayList<>();
        }


        return rooms;

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


    public int deleteEmployee(String employeeNationalCode) {
        String query = "UPDATE employee SET hotelId = NULL  WHERE nationalCode = ? ";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, employeeNationalCode);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addemployee(String hotelId, String employeeNationalCode) {

        System.out.println(employeeNationalCode);
        System.out.println(hotelId);
        String query = "UPDATE employee SET hotelId = ? WHERE nationalCode = ? ";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, hotelId);
            statement.setString(2, employeeNationalCode);
            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public ArrayList<Reservation> getAllReservations() {
        String query = "SELECT * FROM reservation ";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSets = preparedStatement.executeQuery();
            System.out.println(resultSets.getFetchSize());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }


}
