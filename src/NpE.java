import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class NpE {
    private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private Dimension screensize;


    public NpE() {
        frame = new JFrame();
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textField = new JTextField(50);
        screensize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setSize(screensize.width, screensize.height - 5);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(textField);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getTextField() {
        return textField;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        textField.setText("");
    }
}
