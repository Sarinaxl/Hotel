package UI.manager;

import components.CostumeLayout;
import components.CustomeButton;
import models.Manager;

public class AdminLayout extends CostumeLayout {
    public static Manager manager = null;

    public AdminLayout(int width,
                       int height,
                       String frameName,
                       Boolean visibility) {
        super(width, height, frameName, visibility);
        CustomeButton backButton = new CustomeButton("Back To Dashboard", 25, 25,
                150,
                40,
                e -> {

                    this.closeFrame();
                    ManagerWelcomePage welcomPage = new ManagerWelcomePage(600, 600, "Admin Panel");
                    welcomPage.showFrame();
                }
        );
        addComponent(backButton);
    }



}
