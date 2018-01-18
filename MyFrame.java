import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension dimension = toolkit.getScreenSize();

    public MyFrame() throws HeadlessException {
        super();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(dimension.width/2 - dimension.width/3, dimension.height/2 - dimension.height/3);
        this.setTitle("Subset Calculator");
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {}
    }
}
