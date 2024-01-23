package UI.manager;

import components.CustomLabel;
import components.CustomeButton;
import globals.GlobalValues;
import models.Manager;


public class ManagerWelcomePage extends AdminLayout {
    public ManagerWelcomePage(int width, int height, String frameName) {
        super(width, height, frameName, true);

        int xAxis = 250;
        int yAxis = 25;
        int bWidth = 300;
        int bHeight = 50;
        addComponent(new CustomeButton(
                "Create Employee",
                xAxis,
                yAxis,
                bWidth,
                bHeight,
                e -> {
                }
        ));

        addComponent(new CustomeButton(
                "Create Hotel",
                xAxis,
                yAxis + 75,
                bWidth,
                bHeight,
                e -> {
                    CreateHotel createHotel = new CreateHotel(600, 600, "Create Hotel");
                    createHotel.showFrame();
                    this.closeFrame();
                }
        ));


        addComponent(new CustomeButton(
                "Hotels",
                xAxis,
                yAxis + 150,
                bWidth,
                bHeight,
                e -> {
                    ManagerHotelsPage managerHotelsPage = new ManagerHotelsPage(
                            600,
                            600,
                            "hotels");
                    ManagerHotels managerHotels = new ManagerHotels(800, 800, "Hotels");

                    this.closeFrame();
                }
        ));


        addComponent(new CustomLabel(
                "Welcome " + GlobalValues.manager.getFullName(),
                0,
                yAxis + 250,
                width,
                bHeight));


    }
}
