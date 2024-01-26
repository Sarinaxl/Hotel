package UI.guest;

import UI.manager.AdminLayout;
import alerts.Alert.Alert;
import components.CostumeLayout;
import components.CustomLabel;
import components.CustomeButton;
import components.CustomeTextField;
import components.list.CustomList;
import controllers.GuestController;
import models.Hotel;
import models.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ReserveHotelPage extends CostumeLayout {

    GuestController guestController = new GuestController();

    Hotel selectedHotel;
    Room selectedRoom;

    ArrayList<Hotel> hotels = guestController.getHotels();
    String[] hotelNames = hotels.stream()
            .map(Hotel::getName)
            .toArray(String[]::new);


    public ReserveHotelPage(int width, int height, String frameName) {
        super(width, height, frameName, true);


        AtomicInteger yAxis = new AtomicInteger(100);

        hotels.forEach(hotel -> {


            ArrayList<Room> rooms = hotel.getRooms();
            String[] roomsName = rooms.stream()
                    .map(Room::getRoomDetail)
                    .toArray(String[]::new);


            CustomLabel hotelTitle = new CustomLabel(hotel.getName(),
                    50,
                    yAxis.get() - 10,
                    300,
                    10);
            addComponent(hotelTitle);


            CustomList<String> roomsList = new CustomList<>(roomsName,
                    50,
                    yAxis.get(),
                    300,
                    200,
                    e -> {
                        JList<String> source = (JList<String>) e.getSource();
                        int selectedIndex = source.getSelectedIndex();
                        selectedRoom = hotel.getRooms().get(selectedIndex);
                    });
            yAxis.addAndGet(230);
            addComponent(roomsList);


            CustomeTextField durationOfStay = new CustomeTextField("duration Of Stay",
                    400,
                    25,
                    200,
                    25);
            addComponent(durationOfStay);

            CustomeButton reserveButton = new CustomeButton("Reserve This Room",
                    400,
                    50,
                    200,
                    50,
                    e -> {

                        System.out.println(selectedRoom.getId());
                        int result = guestController.reserveRoom(selectedRoom, Integer.parseInt(durationOfStay.getEnteredText()));
                        if (result == 1) {
                            Alert.showSuccess("Room Enrolled");
                        } else {
                            Alert.showError(" Error !");
                        }
                        System.out.println(result);
                    });
            addComponent(reserveButton);


        });

    }
}
