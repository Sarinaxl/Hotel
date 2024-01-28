package UI.manager;

import alerts.Alert.Alert;
import components.CustomLabel;
import components.CustomeButton;
import components.CustomeTextField;
import components.list.CustomList;
import controllers.ManagerController;
import models.Employee;
import models.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagerHotels extends AdminLayout {

    public static Hotel selectedHotel = null;
    public static Employee selectedEmployee = null;

    public ManagerHotels(int width, int height, String frameName) {
        super(width, height, frameName, true);


        ManagerController managerController = new ManagerController();
        ArrayList<Hotel> hotels = managerController.getHotels();
        String[] hotelNames = hotels.stream()
                .map(Hotel::getName)
                .toArray(String[]::new);


        ArrayList<Employee> hotelEmployess = managerController.getAllEmployeesNotEmpoyed();


        String[] hotelEmployessNames = hotelEmployess.stream()
                .map(Employee::getFullName)
                .toArray(String[]::new);


        addComponent(new CustomLabel("Availabel Emolyees For Work", 250, 80, 200, 20));
        CustomList<String> customListEmployes = new CustomList<>(
                hotelEmployessNames,
                270,
                100,
                300,
                200,
                e -> {
                    if (!e.getValueIsAdjusting()) {
                        JList<String> source = (JList<String>) e.getSource();
                        int selectedIndex = source.getSelectedIndex();
                        selectedEmployee = hotelEmployess.get(selectedIndex);
                    }
                }
        );


        CustomeButton deleteEmployee = new CustomeButton("Delete Employe",
                600,
                100,
                100,
                30,
                e -> {
                    Alert.showSuccess(selectedEmployee.getId());
                    int result = managerController.deleteEmployee(selectedEmployee.getNationalCode());
                    if (result == 1) {
                        Alert.showSuccess(("Succesfuly deleted from Hotel"));
                    } else {
                        Alert.showError("Somthing Error");
                    }
                });
        addComponent(deleteEmployee);


        addComponent(new CustomLabel("Hotels", 50, 80, 200, 20));
        CustomList<String> customListHotels = new CustomList<>(
                hotelNames,
                50,
                100,
                200,
                200,
                e -> {
                    if (!e.getValueIsAdjusting()) {
                        JList<String> source = (JList<String>) e.getSource();
                        int selectedIndex = source.getSelectedIndex();
                        selectedHotel = hotels.get(selectedIndex);
                    }
                }
        );



        CustomeButton addEmployee = new CustomeButton("Add Employee",
                350,
                320,
                150,
                50,
                e -> {
                    int result = managerController.addemployee(selectedHotel.getId(), selectedEmployee.getNationalCode());
                    if (result == 1) {
                        Alert.showSuccess("Employee Added To Hotel");
                    } else {
                        Alert.showSuccess("Employee Not Available");
                    }
                });


        addComponent(addEmployee);
        addComponent(customListHotels);
        addComponent(customListEmployes);

    }


}
