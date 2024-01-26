package controllers;

import database.Database;
import globals.GlobalValues;
import interfaces.InsertCallbacks;
import models.Guest;
import models.Hotel;
import models.Reservation;
import models.Room;
import utils.ResultSetMapper;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GuestController extends Database {


    ResultSetMapper<Guest> resultSetMapper = new ResultSetMapper<>();
    Guest guest = GlobalValues.guest;

    public void createGuest(Guest guest) {
        String[] guestTableColums = getColumns("guest");
        String[] values = new String[]{guest.getNationalCode(), guest.getFirstName(), guest.getLastName(), guest.getEmail(), guest.getPassword()};

        insertIntoTableQuery(
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
            if (result.first()) {
                String email = result.getString("email");
                String nationalCodee = result.getString("nationalCode");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String emaill = result.getString("email");
                String passwordd = result.getString("password");
                return new Guest(nationalCode, firstName, lastName, email, password);
            }
            return null;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
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


    public int reserveRoom(Room room, int durationOfStay) {
        String insertReseravation = "INSERT INTO reservation (guestNationalCode, paymentAmount, durationOfStay, roomNumber, roomId) VALUES (?, ?, ?, ?, ?)";
        String updateRoomQuery = "UPDATE room SET isReserved = 1 WHERE id = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insertReseravation);
            preparedStatement.setString(1, guest.getNationalCode());
            preparedStatement.setDouble(2, room.getPrice() * durationOfStay);
            preparedStatement.setInt(3, durationOfStay);
            preparedStatement.setString(4, room.getRoomNumber());
            preparedStatement.setString(5, room.getId());
            int inserResult = preparedStatement.executeUpdate();

            PreparedStatement roomUpateStatement = getConnection().prepareStatement(updateRoomQuery);
            roomUpateStatement.setString(1, room.getId());
            int roomUpdateResult = roomUpateStatement.executeUpdate();

            if (inserResult == roomUpdateResult) {
                return 1;
            }

            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int unReserveRoom(Reservation reservation) {
        String deleteReservationQuery = "DELETE FROM reservation WHERE id = ?";
        String updateRoomQuery = "UPDATE room SET isReserved = 0 WHERE id = ?";
        try {

            PreparedStatement deleteReservation = getConnection().prepareStatement(deleteReservationQuery);
            deleteReservation.setInt(1, reservation.getId());
            int deleteReservationResult = deleteReservation.executeUpdate();


            // Update room
            PreparedStatement updateRoom = getConnection().prepareStatement(updateRoomQuery);
            updateRoom.setInt(1, reservation.getRoomId());
            int updateResult = updateRoom.executeUpdate();

            if (deleteReservationResult == updateResult) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
        }

        return 0;
    }

    public ArrayList<Reservation> getAllReservedHotels() {

        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            String query = "SELECT * FROM reservation WHERE guestNationalCode = ?";

            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, GlobalValues.guest.getNationalCode());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getInt("id"));
                reservation.setDate(resultSet.getDate("date").toLocalDate());
                reservation.setGuestNationalCode(resultSet.getString("guestNationalCode"));
                reservation.setPaymentAmount(resultSet.getDouble("paymentAmount"));
                reservation.setDurationOfStay(resultSet.getInt("durationOfStay"));
                reservation.setRoomNumber(resultSet.getInt("roomNumber"));
                reservation.setStatus(resultSet.getString("status"));
                reservation.setRoomId(resultSet.getInt("roomId"));
                reservations.add(reservation);
            }

            return reservations;


        } catch (Exception e) {
        }
        return null;
    }


}
