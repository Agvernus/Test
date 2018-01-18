import myExceptions.InconsequenceException;
import myLibrary.Subset;
import myLibrary.SubsetUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    static MyFrame myFrame = new MyFrame();

    static JScrollPane scrollPane;

    static JLabel answer;

    static JTextField x;

    public static void main(String[] args) {
        myFrame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        JButton addSubsetButton = new JButton("+");
        JButton removeSubsetButton = new JButton("-");
        mainPanel.add(addSubsetButton);
        mainPanel.add(removeSubsetButton);
        myFrame.add(mainPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        BodyPanel bodyPanel = new BodyPanel();
        bodyPanel.setLayout(new GridLayout(1, 1));
        SubsetPanel subsetPanel = new SubsetPanel();
        SubsetUnitPanel subsetUnitPanel = new SubsetUnitPanel();
        subsetPanel.add(subsetUnitPanel);
        bodyPanel.add(subsetPanel);
        panel.add(bodyPanel);
        scrollPane = new JScrollPane(bodyPanel);
        scrollPane.setPreferredSize(new Dimension(274, 52));
        panel.add(scrollPane);
        myFrame.add(panel, BorderLayout.CENTER);

        WestPanel westPanel = new WestPanel();
        westPanel.setLayout(new GridLayout(1, 1));
        WestButtonsPanel westButtonsPanel = new WestButtonsPanel(subsetPanel);
        westPanel.add(westButtonsPanel);
        myFrame.add(westPanel, BorderLayout.WEST);

        JPanel footer = new JPanel();
        footer.setLayout(new GridLayout(1, 1));
        JPanel insideFooter = new JPanel();
        JLabel xLabel = new JLabel("x: ");
        x = new JTextField(5);
        JButton findNearestNumber = new JButton("Найти ближайшее к x число из пересечения подмножеств");
        insideFooter.add(xLabel);
        insideFooter.add(x);
        insideFooter.add(findNearestNumber);
        JButton subsetsIntersection = new JButton("Найти пересечение подмножеств");
        insideFooter.add(subsetsIntersection);
        footer.add(insideFooter);
        myFrame.add(footer, BorderLayout.SOUTH);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 0;
        JButton infinityButton1 = new JButton("-∞");
        infinityButton1.setFocusable(false);
        eastPanel.add(infinityButton1, constraints);
        constraints.gridy = 1;
        JButton infinityButton2 = new JButton("+∞");
        infinityButton2.setFocusable(false);
        eastPanel.add(infinityButton2, constraints);
        myFrame.add(eastPanel, BorderLayout.EAST);

        myFrame.pack();

        addSubsetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (BodyPanel.getSubsetQuantity() < 10) {
                    SubsetPanel subsetPanel = new SubsetPanel();
                    SubsetUnitPanel subsetUnitPanel = new SubsetUnitPanel();
                    subsetPanel.add(subsetUnitPanel);
                    bodyPanel.add(subsetPanel);
                    bodyPanel.setLayout(new GridLayout(BodyPanel.getSubsetQuantity(), 1));
                    westPanel.add(new WestButtonsPanel(subsetPanel));
                    westPanel.setLayout(new GridLayout(WestPanel.getWestButtonsQuantity(), 1));
                    int newWidth;
                    if (274*SubsetPanel.getMaxSubsetUnitQuantity() < MyFrame.dimension.width) {
                        newWidth = 274*SubsetPanel.getMaxSubsetUnitQuantity();
                    } else {
                        newWidth = MyFrame.dimension.width - 150;
                    }
                    int newHeight = 52*BodyPanel.getSubsetQuantity();
                    Main.scrollPane.setPreferredSize(new Dimension(newWidth, newHeight));
                    myFrame.pack();
                }
            }
        });
        removeSubsetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (BodyPanel.getSubsetQuantity() > 1) {
                    bodyPanel.remove(BodyPanel.getSubsetPanels().get(BodyPanel.getSubsetQuantity() - 1));
                    bodyPanel.setLayout(new GridLayout(BodyPanel.getSubsetQuantity(), 1));
                    westPanel.remove(westPanel.getWestButtonsPanels().get(WestPanel.getWestButtonsQuantity() - 1));
                    westPanel.setLayout(new GridLayout(WestPanel.getWestButtonsQuantity(), 1));
                    int newWidth;
                    if (274*SubsetPanel.getMaxSubsetUnitQuantity() < MyFrame.dimension.width) {
                        newWidth = 274*SubsetPanel.getMaxSubsetUnitQuantity();
                    } else {
                        newWidth = MyFrame.dimension.width - 150;
                    }
                    int newHeight = 52*BodyPanel.getSubsetQuantity();
                    Main.scrollPane.setPreferredSize(new Dimension(newWidth, newHeight));
                    myFrame.pack();
                }
            }
        });

        subsetsIntersection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Subset intersection = intersectionFromFrame(bodyPanel, footer);
                if (intersection != null) {
                    if (answer != null) footer.remove(answer);
                    footer.setLayout(new GridLayout(2, 1));
                    answer = new JLabel("Пересечение подмножеств: " + intersection.toString());
                    footer.add(answer);
                    myFrame.pack();
                }
            }
        });

        findNearestNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Subset intersection = intersectionFromFrame(bodyPanel, footer);
                try {
                    if (intersection != null) {
                        if (answer != null) footer.remove(answer);
                        footer.setLayout(new GridLayout(2, 1));
                        double x = Double.parseDouble(Main.x.getText());
                        answer = new JLabel("Ближайшее к x число из пересечения подмножеств: " + intersection.getNearestX(x));
                        footer.add(answer);
                        myFrame.pack();
                    }
                }
                catch (NumberFormatException exception) {
                    if (answer != null) footer.remove(answer);
                    footer.setLayout(new GridLayout(2, 1));
                    answer = new JLabel("x должно быть числом. Исправьте ошибку и попробуйте снова");
                    footer.add(answer);
                    myFrame.pack();
                }
            }
        });

        infinityButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = myFrame.getMostRecentFocusOwner();
                if (component instanceof JTextField) {
                    ((JTextField) component).setText("-inf");
                }
            }
        });

        infinityButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = myFrame.getMostRecentFocusOwner();
                if (component instanceof JTextField) {
                    ((JTextField) component).setText("+inf");
                }
            }
        });
    }

    public static Subset intersectionFromFrame(BodyPanel bodyPanel, JPanel footer) {
        try {
            ArrayList<Subset> subsets = new ArrayList<>();
            for (int i = 0; i < BodyPanel.getSubsetPanels().size(); i++) {
                Subset subset = new Subset();
                String stringLastNumber = BodyPanel.getSubsetPanels().get(i).getSubsetUnitPanels().get(0).getFirstNumericField().getText();
                double lastNumber = stringLastNumber.equals("-inf") ? Double.NEGATIVE_INFINITY : (stringLastNumber.equals("+inf") ? Double.POSITIVE_INFINITY : (stringLastNumber.equals("inf") ? Double.POSITIVE_INFINITY : Double.parseDouble(stringLastNumber)));
                for (int j = 0; j < BodyPanel.getSubsetPanels().get(i).getSubsetUnitPanels().size(); j++) {
                    SubsetUnit subsetUnit = new SubsetUnit();
                    String bracket1 = BodyPanel.getSubsetPanels().get(i).getSubsetUnitPanels().get(j).getBracketButton1().getText();
                    subsetUnit.setLeftBracket(bracket1.equals("["));
                    String numField1 = BodyPanel.getSubsetPanels().get(i).getSubsetUnitPanels().get(j).getFirstNumericField().getText();
                    if (numField1.equals("-inf")) {
                        if (Double.compare(Double.NEGATIVE_INFINITY, lastNumber) < 0) throw new InconsequenceException();
                        subsetUnit.setX1(lastNumber = Double.NEGATIVE_INFINITY);
                    }
                    else if (numField1.equals("+inf") || numField1.equals("inf")) {
                        if (Double.compare(Double.POSITIVE_INFINITY, lastNumber) < 0) throw new InconsequenceException();
                        subsetUnit.setX1(lastNumber = Double.POSITIVE_INFINITY);
                    }
                    else {
                        if (Double.compare(Double.parseDouble(numField1), lastNumber) < 0) throw new InconsequenceException();
                        subsetUnit.setX1(lastNumber = Double.parseDouble(numField1));
                    }
                    String numField2 = BodyPanel.getSubsetPanels().get(i).getSubsetUnitPanels().get(j).getSecondNumericField().getText();
                    if (numField2.equals("-inf")) {
                        if (Double.compare(Double.NEGATIVE_INFINITY, lastNumber) < 0) throw new InconsequenceException();
                        subsetUnit.setX2(lastNumber = Double.NEGATIVE_INFINITY);
                    }
                    else if (numField2.equals("+inf") || numField2.equals("inf")) {
                        if (Double.compare(Double.POSITIVE_INFINITY, lastNumber) < 0) throw new InconsequenceException();
                        subsetUnit.setX2(lastNumber = Double.POSITIVE_INFINITY);
                    }
                    else {
                        if (Double.compare(Double.parseDouble(numField2), lastNumber) < 0) throw new InconsequenceException();
                        subsetUnit.setX2(lastNumber = Double.parseDouble(numField2));
                    }
                    String bracket2 = BodyPanel.getSubsetPanels().get(i).getSubsetUnitPanels().get(j).getBracketButton2().getText();
                    subsetUnit.setRightBracket(bracket2.equals("]"));
                    subset.addSubsetUnits(subsetUnit);
                }
                subsets.add(subset);
            }
            Subset intersection = subsets.get(0);
            for (int i = 1; i < subsets.size(); i++) {
                intersection = intersection.getIntersection(subsets.get(i));
            }
            return intersection;
        } catch (NumberFormatException exception) {
            if (answer != null) footer.remove(answer);
            footer.setLayout(new GridLayout(2, 1));
            answer = new JLabel("Элементы подмножеств должны быть числами. Исправьте ошибку и попробуйте снова");
            footer.add(answer);
            myFrame.pack();
            return null;
        } catch (Exception exception) {
            if (answer != null) footer.remove(answer);
            footer.setLayout(new GridLayout(2, 1));
            answer = new JLabel(exception.getMessage());
            footer.add(answer);
            myFrame.pack();
            return null;
        }
    }
}
