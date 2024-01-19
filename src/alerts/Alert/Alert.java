package alerts.Alert;


import javax.swing.*;

public class Alert {

    public static void showSuccess(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "Message",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static void showError(String message) {
        JOptionPane.showMessageDialog(null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

}
