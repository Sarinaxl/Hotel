package UI;

import components.CostumeLayout;
import components.CustomeButton;
import components.CustomeTextField;
import interfaces.InsertCallbacks;
import interfaces.PasswordInterface;
import utils.EmailValidator;
import utils.Password;

import javax.swing.*;

public class SignUpPage extends CostumeLayout {


    public SignUpPage(int width, int height, String frameName) {
        super(width, height, frameName, true);

        CustomeTextField nationalId = new CustomeTextField("National ID", 25, 50, 250, 25);
        CustomeTextField name = new CustomeTextField("Name", 25, 75, 250, 25);
        CustomeTextField family = new CustomeTextField("Family", 25, 100, 250, 25);
        CustomeTextField email = new CustomeTextField("email", 25, 125, 250, 25);
        CustomeTextField password = new CustomeTextField("password", 25, 150, 250, 25);
        CustomeButton submitButton = new CustomeButton("Submit", 25, 200, 250, 25,
                e -> {
                    String nationalIdText = nationalId.getEnteredText();
                    String nameText = name.getEnteredText();
                    String familyText = family.getEnteredText();
                    String emailText = email.getEnteredText();
                    String passwordText = password.getEnteredText();
                    if (nationalIdText.isEmpty() || nameText.isEmpty() || familyText.isEmpty() || emailText.isEmpty() || passwordText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!EmailValidator.isValid(emailText)) {
                        System.out.println(emailText);
                        JOptionPane.showMessageDialog(null, "Email Not vlaid !", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Password.checkPasswordStrong(passwordText, new PasswordInterface() {
                        @Override
                        public void onPasswordGranted() {
                            String[] guestTableColums = getColumns("guest");
                            String[] values = new String[]{nationalIdText, nameText, familyText, emailText, passwordText};

                            insertIntoTable(
                                    "guest",
                                    guestTableColums,
                                    values, new InsertCallbacks() {
                                        @Override
                                        public void onSuccess(int affectedRows) {
                                            System.out.println("You are Succefully Registered!");
                                        }

                                        @Override
                                        public void onError(Exception e) {
                                            System.out.println("Error");
                                        }
                                    }
                            );
                        }

                        @Override
                        public void onPasswordDenied(String error) {
                            JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });


                });
        addComponent(nationalId);
        addComponent(name);
        addComponent(family);
        addComponent(email);
        addComponent(password);
        addComponent(submitButton);

    }


}
