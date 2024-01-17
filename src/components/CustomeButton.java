package components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CustomeButton extends JButton {
    String label;
    int xAxis;
    int yAxis;
    int width;
    int height;
    ActionListener actionListener;

    public CustomeButton(
            String label,
            int xAxis,
            int yAxis,
            int width,
            int height,
            ActionListener actionListener) {
        this.label = label;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.width = width;
        this.height = height;
        this.actionListener = actionListener;
        intitlizeComponent();
    }


    void intitlizeComponent() {
        setText(label);
        addActionListener(this.actionListener);
        setBounds(this.xAxis, this.yAxis, this.width, this.height);
    }


}
