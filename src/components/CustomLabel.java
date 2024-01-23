package components;

import javax.swing.*;

public class CustomLabel extends JLabel {
    String text;
    int xAxis;
    int yAxis;
    int width;
    int height;

    public CustomLabel(String text, int xAxis, int yAxis, int width, int height) {
        this.text = text;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.width = width;
        this.height = height;
        initializeComponent();
    }

    void initializeComponent() {
        setText(text);
        setBounds(xAxis, yAxis, width, height);
    }
}
