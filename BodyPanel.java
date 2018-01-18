import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BodyPanel extends JPanel {
    private static ArrayList<SubsetPanel> subsetPanels = new ArrayList<>();
    private static int subsetQuantity = 0;

    public Component add(SubsetPanel subsetPanel) {
        subsetQuantity++;
        subsetPanels.add(subsetPanel);
        return super.add(subsetPanel);
    }

    @Override
    public void add(Component component, Object constraints) {
        if (component instanceof SubsetPanel) {
            subsetQuantity++;
            subsetPanels.add((SubsetPanel)component);
        }
        super.add(component, constraints);
    }

    public void remove(SubsetPanel subsetPanel) {
        subsetQuantity--;
        super.remove(subsetPanel);
        subsetPanels.remove(subsetQuantity);
        repaint();
    }

    public static int getSubsetQuantity() {
        return subsetQuantity;
    }

    public static ArrayList<SubsetPanel> getSubsetPanels() {
        return subsetPanels;
    }
}
