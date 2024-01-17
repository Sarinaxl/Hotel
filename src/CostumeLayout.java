import javax.swing.*;
import java.awt.*;

public class CostumeLayout {
    public JFrame frame;
    JPanel panel;
    int width;
    int height;
    String frameName;
    private Boolean visibility = false;

    public CostumeLayout(int width, int height, String frameName, Boolean visibility) {
        this.width = width;
        this.height = height;
        this.frameName = frameName;
        this.visibility = visibility;
        initLayout();

    }


    private void initLayout() {
        frame = new JFrame(this.frameName);
        panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(this.width, this.height);
        panel.setLayout(null);
        frame.add(panel);
        frame.setVisible(this.visibility);

    }

    public void addComponent(Component component) {
        panel.add(component);
    }


    public void setVisibility(Boolean state) {
        this.visibility = state;
        frame.setVisible(state);
    }

    public void showFrame() {
        this.visibility = true;
        frame.setVisible(true);
    }

    public void closeFrame() {
        this.visibility = false;
        frame.setVisible(false);
    }

    public void hideFrame() {

    }

}
