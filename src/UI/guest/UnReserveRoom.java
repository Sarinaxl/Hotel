package UI.guest;

import alerts.Alert.Alert;
import components.CostumeLayout;
import components.CustomeButton;
import components.list.CustomList;
import controllers.GuestController;
import models.Reservation;
import models.Room;

import javax.swing.*;
import java.util.ArrayList;

public class UnReserveRoom extends CostumeLayout {

    Reservation selectedReservation;
    Room selectedRoom;


    GuestController guestController = new GuestController();
    ArrayList<Reservation> reservations = guestController.getAllReservedHotels();
    String[] reservationsName = reservations.stream()
            .map(Reservation::toString)
            .toArray(String[]::new);

    public UnReserveRoom(int width, int height, String frameName) {
        super(width, height, frameName, true);


        CustomList<String> reservedList = new CustomList<>(reservationsName,
                25,
                100,
                900,
                900,
                e -> {
                    JList<String> source = (JList<String>) e.getSource();
                    int selectedIndex = source.getSelectedIndex();
                    selectedReservation = reservations.get(selectedIndex);
                });
        addComponent(reservedList);


        CustomeButton unReserve = new CustomeButton(
                "Un reserve This Record",
                25,
                50,
                200,
                20,
                e -> {
                    System.out.println(selectedReservation.getRoomId());
                    int result = guestController.unReserveRoom(selectedReservation);
                    if (result == 1) {
                        Alert.showSuccess("Room Un Reserved");
                    } else {
                        Alert.showError("Error !");
                    }
                });
        addComponent(unReserve);


    }
}
