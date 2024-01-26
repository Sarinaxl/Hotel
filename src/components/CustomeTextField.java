package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomeTextField extends JTextField {
    public CustomeTextField(String placeholder, int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setForeground(Color.GRAY);
        setText(placeholder);
        addFocusListener(new FocusListener());
    }




    public String getEnteredText() {
        return getText().equals(getToolTipText()) ? "" : getText();
    }

    private class FocusListener extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            if (getText().equals(getToolTipText())) {
                setText("");
                setForeground(Color.BLACK);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (getText().isEmpty()) {
                setText(getToolTipText());
                setForeground(Color.GRAY);
            }
        }

    }
}
