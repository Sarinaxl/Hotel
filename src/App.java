import components.CustomeButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

public class App {

    public static void main(String[] args) {
        MainPage mainPage = new MainPage(500, 500, "Main Page");
        CustomeButton button = new CustomeButton(
                "Login",
                25,
                25,
                75,
                45,
                e -> {
                    LoginPage loginPage = new LoginPage();
                }
        );



        mainPage.addComponent(button);
        mainPage.showFrame();


    }
}
