package UI.manager;

import alerts.Alert.Alert;
import components.CustomLabel;
import components.CustomeButton;
import components.CustomeTextField;
import components.list.CustomList;
import controllers.ManagerController;
import interfaces.InsertCallbacks;
import models.Hotel;
import models.Room;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ManagerRooms extends AdminLayout {

    Hotel selectedHotel;
    Room selectedRoom;
    ManagerController managerController = new ManagerController();
    ArrayList<Hotel> hotels = managerController.getHotels();
    ArrayList<Room> rooms = managerController.getRooms();
    String[] roomsName = rooms.stream()
            .map(Room::getRoomNumber)
            .toArray(String[]::new);
    String[] hotelNames = hotels.stream()
            .map(Hotel::getName)
            .toArray(String[]::new);


    public ManagerRooms(int width, int height, String frameName) {
        super(width, height, frameName, true);


        CustomLabel roomsLable = new CustomLabel("Rooms", 400, 25, 200, 20);
        addComponent(roomsLable);
        CustomList<String> roomsList = new CustomList<>(roomsName,
                400,
                50,
                250,
                300,
                e -> {
                    JList<String> source = (JList<String>) e.getSource();
                    int selectedIndex = source.getSelectedIndex();
                    selectedRoom = rooms.get(selectedIndex);
                });
        addComponent(roomsList);


        //    Hotels
        int hotelsFormYAxis = 100;
        addComponent(new CustomLabel("Hotels", 50, 80, 200, 20));
        CustomList<String> customListHotels = new CustomList<>(
                hotelNames,
                50,
                hotelsFormYAxis,
                200,
                200,
                e -> {
                    if (!e.getValueIsAdjusting()) {
                        JList<String> source = (JList<String>) e.getSource();
                        int selectedIndex = source.getSelectedIndex();
                        selectedHotel = hotels.get(selectedIndex);
                    }
                }
        );
        addComponent(customListHotels);


        CustomeTextField roomNumbers = new CustomeTextField("roomNumbers",
                25,
                hotelsFormYAxis + 200,
                250,
                25);
        addComponent(roomNumbers);


        CustomeTextField numberOfBeds = new CustomeTextField("Number od Beds",
                25,
                hotelsFormYAxis + (50) + 200,
                250,
                25);
        addComponent(numberOfBeds);
        CustomeTextField isReserved = new CustomeTextField("Is Reserved ( 0 : False  -   1 : true)",
                25,
                hotelsFormYAxis + (50 * 2) + 200,
                250,
                25);
        addComponent(isReserved);


        CustomeTextField price = new CustomeTextField("price",
                25,
                hotelsFormYAxis + (50 * 3) + 200,
                250,
                25);
        addComponent(price);


        CustomeTextField name = new CustomeTextField("name",
                25,
                hotelsFormYAxis + (50 * 4) + 200,
                250,
                25);
        addComponent(name);


        CustomeButton addNewRoomButton = new CustomeButton("Add New Room",
                25,
                hotelsFormYAxis + (50 * 5) + 200,
                250,
                25,
                e -> {

                    managerController.insertIntoTableQuery(
                            "room",
                            new String[]{"roomNumber", "numberOfBeds", "isReserved", "hotelId", "price", "name"},
                            new String[]{
                                    roomNumbers.getEnteredText(),
                                    numberOfBeds.getEnteredText(),
                                    isReserved.getEnteredText(),
                                    selectedHotel.getId(),
                                    price.getEnteredText(),
                                    name.getEnteredText()
                            }
                            ,
                            new InsertCallbacks() {
                                @Override
                                public void onSuccess(int affectedRows) {
                                    Alert.showSuccess("Room Succesfully Created!");
                                }

                                @Override
                                public void onError(Exception e) {
                                    Alert.showSuccess("Error Eccourd");

                                }
                            });
                });
        addComponent(addNewRoomButton);


        CustomeButton editRoom = new CustomeButton("Edit Room",
                400,
                370,
                250,
                25,
                e -> {
                    EditRoomPage editRoomPage = new EditRoomPage(
                            600,
                            600,
                            "Edit Room Page" ,
                            selectedRoom
                    );
                    this.closeFrame();
                });
        addComponent(editRoom);


    }
}
