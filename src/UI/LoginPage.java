package UI;


import UI.guest.GuestWelcomePage;
import UI.hotels.Hotels;
import UI.manager.ManagerWelcomePage;
import alerts.Alert.Alert;
import components.CostumeLayout;
import components.CustomeButton;
import components.CustomeTextField;
import controllers.GuestController;
import controllers.ManagerController;
import globals.GlobalValues;
import models.Guest;
import models.Manager;

public class LoginPage extends CostumeLayout {


    public LoginPage(int width, int height, String frameName) {
        super(width, height, frameName, true);


        CustomeTextField nationalCode = new CustomeTextField("5555", 25, 50, 200, 30);
        CustomeTextField passwordField = new CustomeTextField("alirezayi@1380", 25, 100, 200, 30);


        addComponent(nationalCode);
        addComponent(passwordField);

        CustomeButton loginAsGuestButton = new CustomeButton(
                "Login as Guest",
                25,
                150,
                200,
                30,
                e -> {
                    String passwordText = passwordField.getEnteredText();
                    String nationalCodeText = nationalCode.getEnteredText();
                    GuestController guestController = new GuestController();
                    Guest guest = guestController.loginGuest(nationalCodeText, passwordText);
                    if (guest != null) {
                        Alert.showSuccess("Welcome Back ! " + guest.getFullName());
                        GlobalValues.guest = guest;
                        GuestWelcomePage guestWelcomePage = new GuestWelcomePage(600, 600, "Welcome!");
                        this.closeFrame();
                    } else
                        Alert.showError("Password or NationalCode Wrong.");

                });

        CustomeButton loginAsEmployeetButton = new CustomeButton("Login as Employee",
                250,
                150,
                200,
                30,
                e -> {
                    String passwordText = passwordField.getEnteredText();
                    String nationalCodeText = nationalCode.getEnteredText();
                    GuestController guestController = new GuestController();
                    Guest guest = guestController.loginGuest(nationalCodeText, passwordText);
                    if (guest != null) {
                        Alert.showSuccess("Welcome Back ! " + guest.getFullName());
                        this.closeFrame();
                        Hotels hotels = new Hotels(1000, 1000, "Hotels");
                    } else
                        Alert.showError("Password or NationalCode Wrong.");

                });

        CustomeButton loginAsManagerButton = new CustomeButton("Login as Manager",
                25,
                200,
                200,
                30,
                e -> {
                    String passwordText = passwordField.getEnteredText();
                    String nationalCodeText = nationalCode.getEnteredText();
                    ManagerController managerController = new ManagerController();
                    Manager manager = managerController.loginManager(nationalCodeText, passwordText);
                    if (manager != null) {
                        Alert.showSuccess("Welcome Back ! " + manager.getFullName());
                        GlobalValues.manager = manager;
                        ManagerWelcomePage managerWelcomePage =
                                new ManagerWelcomePage(600, 600, "Admin Panel");
                        this.closeFrame();
                    } else {
                        Alert.showError("Code / Password Wrong .");
                    }

                });

        addComponent(loginAsGuestButton);
        addComponent(loginAsEmployeetButton);
        addComponent(loginAsManagerButton);

    }


}
