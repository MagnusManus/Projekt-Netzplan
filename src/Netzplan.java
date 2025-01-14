import javax.swing.*;
import java.awt.*;

import static java.awt.Font.BOLD;

public class Netzplan {
    private JFrame frame;
    private JPanel panel;
    private Dimension screensize;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private int width;
    private int height;

    public Netzplan() {

        frame = new JFrame("Netzplan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screensize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screensize.width / 2, screensize.height - 5);
        width = screensize.width;
        height = screensize.height;
        frame.setLocation(width / 2, 0);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());


        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Times New Roman", BOLD, 18));
        textArea.setEditable(false);

        scrollPane = new JScrollPane(textArea);

        panel.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel);

        frame.setVisible(true);
    }

    public void setTextToScrollPane(String text) {
        textArea.setText(text);
    }
}