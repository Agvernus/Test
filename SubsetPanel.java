import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SubsetPanel extends JPanel {
    private int subsetUnitQuantity = 0;
    private static int maxSubsetUnitQuantity = 0;
    private ArrayList<SubsetUnitPanel> subsetUnitPanels = new ArrayList<>();
    private JLabel label = new JLabel();

    public SubsetPanel() {
        super();
        label.setText("" + (BodyPanel.getSubsetQuantity() + 1) + ". ");
        this.add(label);
    }

    public Component add(SubsetUnitPanel subsetUnitPanel) {
        subsetUnitQuantity++;
        if (maxSubsetUnitQuantity < subsetUnitQuantity) maxSubsetUnitQuantity++;
        if (subsetUnitQuantity > 1) this.add(subsetUnitPanel.getDelimiter());
        subsetUnitPanels.add(subsetUnitPanel);
        return super.add(subsetUnitPanel);
    }

    public void remove(SubsetUnitPanel subsetUnitPanel) {
        subsetUnitQuantity--;
        int max = BodyPanel.getSubsetPanels().get(0).getSubsetUnitQuantity();
        for (SubsetPanel x : BodyPanel.getSubsetPanels()) {
            if (x.getSubsetUnitQuantity() > max) max = x.getSubsetUnitQuantity();
        }
        if (max < maxSubsetUnitQuantity) maxSubsetUnitQuantity--;
        super.remove(subsetUnitPanel);
        super.remove(subsetUnitPanel.getDelimiter());
        subsetUnitPanels.remove(subsetUnitQuantity);
        repaint();
    }

    public int getSubsetUnitQuantity() {
        return subsetUnitQuantity;
    }

    public ArrayList<SubsetUnitPanel> getSubsetUnitPanels() {
        return subsetUnitPanels;
    }

    public static int getMaxSubsetUnitQuantity() {
        return maxSubsetUnitQuantity;
    }
}
