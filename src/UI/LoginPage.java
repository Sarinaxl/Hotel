package UI;


import components.CostumeLayout;
import components.CustomeButton;
import components.CustomeTextField;
import controllers.GuestController;
import models.Guest;

public class LoginPage extends CostumeLayout {

    public LoginPage(int width, int height, String frameName) {
        super(width, height, frameName, true);


        CustomeTextField nationalCode = new CustomeTextField("nationalCode", 25, 50, 200, 30);
        CustomeTextField passwordField = new CustomeTextField("Password", 25, 100, 200, 30);


        addComponent(nationalCode);
        addComponent(passwordField);

        CustomeButton submitButton = new CustomeButton("Password", 25, 150, 200, 30,
                e -> {
                    String passwordText = passwordField.getEnteredText();
                    String nationalCodeText = nationalCode.getEnteredText();
                    GuestController guestController = new GuestController();
                    Guest guest = guestController.loginGuest(nationalCodeText, passwordText);
                    System.out.println(guest.toString());
                });

        addComponent(submitButton);

    }


}
