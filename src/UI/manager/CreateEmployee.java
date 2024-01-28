package UI.manager;

import alerts.Alert.Alert;
import components.CustomeButton;
import components.CustomeTextField;
import controllers.ManagerController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class CreateEmployee extends AdminLayout {
    public CreateEmployee(int width, int height, String frameName) {
        super(width, height, frameName, true);


        int xAxis = 50;
        int yAxis = 100;
        int formWidth = 100;
        int formHeight = 30;

        ManagerController managerController = new ManagerController();


        CustomeTextField name = new CustomeTextField("name", xAxis, yAxis, formWidth, formHeight);
        yAxis += formHeight + 20;
        addComponent(name);


        CustomeTextField family = new CustomeTextField("family", xAxis, yAxis, formWidth, formHeight);
        yAxis += formHeight + 20;
        addComponent(family);

        CustomeTextField email = new CustomeTextField("email", xAxis, yAxis, formWidth, formHeight);
        yAxis += formHeight + 20;
        addComponent(email);

        CustomeTextField password = new CustomeTextField("password", xAxis, yAxis, formWidth, formHeight);
        yAxis += formHeight + 20;
        addComponent(password);

        CustomeTextField nationalCode = new CustomeTextField("national Code", xAxis, yAxis, formWidth, formHeight);
        yAxis += formHeight + 20;
        addComponent(nationalCode);


        CustomeTextField salary = new CustomeTextField("salary", xAxis, yAxis, formWidth, formHeight);
        yAxis += formHeight + 20;
        addComponent(salary);

        CustomeTextField bankAccount = new CustomeTextField("bank Account Balance", xAxis, yAxis, formWidth, formHeight);
        yAxis += formHeight + 20;
        addComponent(bankAccount);


        CustomeButton submitButton = new CustomeButton("Submit", 400, 50, 100, 30, e -> {
            String query = "INSERT INTO employee (firstName, lastName, email, password, nationalCode, salary, bankAccountBalance)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);\n";
            try {
                PreparedStatement createEmployee = getConnection().prepareStatement(query);
                createEmployee.setString(1, name.getEnteredText());
                createEmployee.setString(2, family.getEnteredText());
                createEmployee.setString(3, email.getEnteredText());
                createEmployee.setString(4, password.getEnteredText());
                createEmployee.setString(5, nationalCode.getEnteredText());
                createEmployee.setString(6, salary.getEnteredText());
                createEmployee.setString(7, bankAccount.getEnteredText());
                int result = createEmployee.executeUpdate();
                if (result == 1) {
                    Alert.showSuccess("employee Created !");
                } else {
                    Alert.showError("Error !");
                }
            } catch (Exception ex) {
                System.out.println(ex);
                ex.printStackTrace();
            }
        });
        addComponent(submitButton);


    }
}
