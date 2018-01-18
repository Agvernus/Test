import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WestPanel extends JPanel {
    private ArrayList<WestButtonsPanel> westButtonsPanels = new ArrayList<>();
    private static int westButtonsQuantity = 0;

    public Component add(WestButtonsPanel westButtonsPanel) {
        westButtonsQuantity++;
        westButtonsPanels.add(westButtonsPanel);
        return super.add(westButtonsPanel);
    }

    public void remove(WestButtonsPanel westButtonsPanel) {
        westButtonsQuantity--;
        super.remove(westButtonsPanel);
        westButtonsPanels.remove(westButtonsQuantity);
    }

    public ArrayList<WestButtonsPanel> getWestButtonsPanels() {
        return westButtonsPanels;
    }

    public static int getWestButtonsQuantity() {
        return westButtonsQuantity;
    }
}
