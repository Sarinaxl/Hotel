package UI.manager;

import alerts.Alert.Alert;
import components.CustomeButton;
import components.CustomeTextField;
import controllers.ManagerController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateHotel extends AdminLayout {
    public CreateHotel(int width, int height, String frameName) {
        super(width, height, frameName, false);

        ManagerController managerController = new ManagerController();


        int xAxis = 250;
        int yAxis = 25;

        CustomeTextField hotelName = new CustomeTextField("hotelName",
                xAxis,
                0,
                150,
                40);

        CustomeTextField availaleRooms = new CustomeTextField("availaleRooms",
                xAxis,
                75,
                150,
                40);

        CustomeTextField bankAccount = new CustomeTextField("bankAccount",
                xAxis,
                150,
                150,
                40);

        JCheckBox status = new JCheckBox("status");
        status.setBounds(
                xAxis,
                215,
                150,
                30); // Set the position and size of the checkbox


        addComponent(hotelName);
        addComponent(availaleRooms);
        addComponent(bankAccount);
        addComponent(status);
        addComponent(new CustomeButton("Submit",
                xAxis,
                290,
                150,
                30,
                e -> {
                    Alert.showSuccess(("ok"));
                }));


//        managerController.createHotel(ManagerController.MANAGER,10);


    }


}
