package UI;


import components.CostumeLayout;
import components.CustomeButton;
import components.CustomeTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends CostumeLayout {

    public LoginPage(int width, int height, String frameName) {
        super(width, height, frameName, true);


        CustomeTextField emailField = new CustomeTextField("Email", 25, 50, 200, 30);
        CustomeTextField passwordField = new CustomeTextField("Password", 25, 100, 200, 30);


        addComponent(emailField);
        addComponent(passwordField);

        CustomeButton submitButton = new CustomeButton("Password", 25, 150, 200, 30,
                e -> {
                    String password = passwordField.getEnteredText();
                    String email = emailField.getEnteredText();

                });

        addComponent(submitButton);

    }


}
