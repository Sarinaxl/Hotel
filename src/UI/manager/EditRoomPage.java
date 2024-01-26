package UI.manager;

import alerts.Alert.Alert;
import components.CustomLabel;
import components.CustomeButton;
import components.CustomeTextField;
import models.Room;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EditRoomPage extends AdminLayout {
    public EditRoomPage(int width, int height, String frameName, Room room) {
        super(width, height, frameName, true);


        int yAxis = 100;
        int formWidth = 200;


        CustomeTextField name = new CustomeTextField(room.getName(),
                250,
                yAxis,
                formWidth,
                50
        );
        addComponent(new CustomLabel("name", 150, yAxis, 50, 25));
        addComponent(name);


        addComponent(new CustomLabel("Room Number", 150, yAxis + 50, 100, 25));
        CustomeTextField roomNumber = new CustomeTextField(room.getRoomNumber(),
                250,
                yAxis + (50),
                formWidth,
                50);
        addComponent(roomNumber);


        addComponent(new CustomLabel("Number of Beds", 150, yAxis + (50 * 2), 100, 25));
        CustomeTextField numberOfBeds = new CustomeTextField(String.valueOf(room.getNumberOfBeds()),
                250,
                yAxis + (50 * 2),
                formWidth,
                50);
        addComponent(numberOfBeds);


        addComponent(new CustomLabel("Reserved", 150, yAxis + (50 * 3), 100, 25));
        CustomeTextField isReserved = new CustomeTextField(String.valueOf(room.getIsReserved()),
                250,
                yAxis + (50 * 3),
                formWidth,
                50);
        addComponent(isReserved);


        addComponent(new CustomLabel("Price", 150, yAxis + (50 * 4), 100, 25));
        CustomeTextField price = new CustomeTextField(String.valueOf(room.getPrice()),
                250,
                yAxis + (50 * 4),
                formWidth,
                50);
        addComponent(price);


        addComponent(new CustomLabel("Hotel ID", 150, yAxis + (50 * 5), 100, 25));
        CustomeTextField hotelId = new CustomeTextField(String.valueOf(room.getHotelId()),
                250,
                yAxis + (50 * 5),
                formWidth,
                50);
        addComponent(hotelId);


        addComponent(new CustomeButton("Submit",
                250,
                yAxis + (50 * 6),
                formWidth,
                50,
                e -> {
                    try {
                        String query = "UPDATE room SET roomNumber = ? , numberOfBeds = ?, isReserved = ?, price = ?, name = ? WHERE id = ?;";
                        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                        preparedStatement.setString(1, roomNumber.getEnteredText());
                        preparedStatement.setString(2, numberOfBeds.getEnteredText());
                        preparedStatement.setString(3, isReserved.getEnteredText());
                        preparedStatement.setString(4, price.getEnteredText());
                        preparedStatement.setString(5, name.getEnteredText());
                        preparedStatement.setString(6, hotelId.getEnteredText());
                        int result = preparedStatement.executeUpdate();
                        if (result == 1) {
                            Alert.showSuccess("Updated!");
                        } else {
                            Alert.showSuccess("error!");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }));


    }
}
