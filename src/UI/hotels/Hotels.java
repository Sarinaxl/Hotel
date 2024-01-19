package UI.hotels;

import components.CostumeLayout;
import components.CustomeButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hotels extends CostumeLayout {
    public Hotels(int width, int height, String frameName) {
        super(width, height, frameName, true);

        CustomeButton createHotelButton = new CustomeButton(
                "Create",
                25,
                10,
                200,
                50,
                e -> {

                });

        addComponent(createHotelButton);

    }




}
