import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WestButtonsPanel extends JPanel {
    private JButton addSubsetUnitButton = new JButton("+");
    private JButton removeSubsetUnitButton = new JButton("-");

    public WestButtonsPanel(SubsetPanel connectedSubsetPanel) {
        super();
        this.add(addSubsetUnitButton);
        this.add(removeSubsetUnitButton);

        addSubsetUnitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubsetUnitPanel subsetUnitPanel = new SubsetUnitPanel();
                connectedSubsetPanel.add(subsetUnitPanel);
                int newWidth;
                if (274*SubsetPanel.getMaxSubsetUnitQuantity() < MyFrame.dimension.width) {
                    newWidth = 274*SubsetPanel.getMaxSubsetUnitQuantity();
                } else {
                    newWidth = MyFrame.dimension.width - 150;
                }
                int newHeight = 52*BodyPanel.getSubsetQuantity();
                Main.scrollPane.setPreferredSize(new Dimension(newWidth, newHeight));
                Main.myFrame.pack();
            }
        });
        removeSubsetUnitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (connectedSubsetPanel.getSubsetUnitQuantity() > 1) {
                    connectedSubsetPanel.remove(connectedSubsetPanel.getSubsetUnitPanels().get(connectedSubsetPanel.getSubsetUnitQuantity() - 1));
                    int newWidth;
                    if (274*SubsetPanel.getMaxSubsetUnitQuantity() < MyFrame.dimension.width) {
                        newWidth = 274*SubsetPanel.getMaxSubsetUnitQuantity();
                    } else {
                        newWidth = MyFrame.dimension.width - 150;
                    }
                    int newHeight = 52*BodyPanel.getSubsetQuantity();
                    Main.scrollPane.setPreferredSize(new Dimension(newWidth, newHeight));
                    Main.myFrame.pack();
                }
            }
        });
    }
}
