import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    public  JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginPage window = new LoginPage();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginPage() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel loginPanel = new JPanel();
        frame.getContentPane().add(loginPanel, BorderLayout.CENTER);
        loginPanel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                // اینجا می‌توانید اعتبارسنجی نام کاربری و رمز عبور را انجام دهید

                if (isValidLogin(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                    // اینجا می‌توانید به صفحه اصلی یا بخش مورد نظر منتقل شوید
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Try again.");
                }
            }
        });
    }

    private boolean isValidLogin(String username, char[] password) {
        // اینجا می‌توانید اعتبارسنجی نام کاربری و رمز عبور را انجام دهید
        // مثال ساده: اگر نام کاربری "admin" و رمز عبور "password" باشد، ورود موفقیت‌آمیز است.
        return username.equals("admin") && new String(password).equals("password");
    }


}
