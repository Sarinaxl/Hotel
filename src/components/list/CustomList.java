package components.list;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomList<T> extends JList<T> {
    private T[] items;
    private int xAxis;
    private int yAxis;
    private int width;
    private int height;
    private ListSelectionListener selectionListener;

    public CustomList(
            T[] items,
            int xAxis,
            int yAxis,
            int width,
            int height,
            ListSelectionListener selectionListener) {
        this.items = items;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.width = width;
        this.height = height;
        this.selectionListener = selectionListener;
        initializeComponent();
    }

    private void initializeComponent() {
        setListData(items);
        setBounds(xAxis, yAxis, width, height);

        // Add ListSelectionListener
        addListSelectionListener(selectionListener);

        // Add MouseListener to capture item clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int index = locationToIndex(e.getPoint());
                    if (index >= 0) {
                        T selectedItem = items[index];
                        // Perform actions with the selected item
                        System.out.println("Selected Item: " + selectedItem);
                    }
                }
            }
        });
    }
}
