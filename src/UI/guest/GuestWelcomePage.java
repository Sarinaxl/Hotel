package UI.guest;

import UI.manager.AdminLayout;
import components.CostumeLayout;
import components.CustomeButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestWelcomePage extends CostumeLayout {
    public GuestWelcomePage(int width, int height, String frameName) {
        super(width, height, frameName, true);

        int xAxis = 25;
        int yAxis = 100;
        int buttonsWidth = 200;
        int buttonsHegiht = 50;
        CustomeButton reservHotel = new CustomeButton(
                "Reserve Hotel",
                xAxis,
                yAxis * 2,
                buttonsWidth,
                buttonsHegiht,
                e -> {
                    ReserveHotelPage reserveHotelPage = new ReserveHotelPage(1000, 1000, "Reserve Hotel");
                    this.closeFrame();
                });
        addComponent(reservHotel);


        CustomeButton unReservHotel = new CustomeButton(
                "Un - Reserve Hotel",
                xAxis,
                yAxis,
                buttonsWidth,
                buttonsHegiht,
                e -> {
                    UnReserveRoom reserveHotelPage = new UnReserveRoom(
                            900,
                            700,
                            "Un Reserve");
                    this.closeFrame();
                });

        addComponent(unReservHotel);

    }
}
