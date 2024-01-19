package UI.manager;

import components.CustomeButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                    CreateHotel createHotel = new CreateHotel(600,600,"Create Hotel");
                    createHotel.showFrame();
                    this.closeFrame();

                }
        ));


    }
}
