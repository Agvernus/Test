import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubsetUnitPanel extends JPanel {
    private JLabel delimiter = new JLabel("U");
    private JButton bracketButton1;
    private JTextField firstNumericField;
    private JLabel comma;
    private JTextField secondNumericField;
    private JButton bracketButton2;

    public SubsetUnitPanel() {
        super();
        this.add(this.bracketButton1 = new JButton("["));
        this.add(this.firstNumericField = new JTextField(5));
        this.add(this.comma = new JLabel(", "));
        this.add(this.secondNumericField = new JTextField(5));
        this.add(this.bracketButton2 = new JButton("]"));

        bracketButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bracketButton1.getText().equals("[")) {
                    bracketButton1.setText("(");
                }
                else {
                    bracketButton1.setText("[");
                }
                repaint();
            }
        });
        bracketButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bracketButton2.getText().equals("]")) {
                    bracketButton2.setText(")");
                }
                else {
                    bracketButton2.setText("]");
                }
                repaint();
            }
        });
    }

    public JLabel getDelimiter() {
        return delimiter;
    }

    public JButton getBracketButton1() {
        return bracketButton1;
    }

    public JTextField getFirstNumericField() {
        return firstNumericField;
    }

    public JLabel getComma() {
        return comma;
    }

    public JTextField getSecondNumericField() {
        return secondNumericField;
    }

    public JButton getBracketButton2() {
        return bracketButton2;
    }
}
