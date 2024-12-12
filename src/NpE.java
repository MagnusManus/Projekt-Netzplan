import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NpE {
    private JFrame frame;
    private JPanel panel;
    private JTextField eingabe;
    private JTextField ausgabe;
    private Dimension screensize;
    private int width;
    private int height;
    private final ActionListener inputListener;
    private boolean inputFlag;


    public NpE(Main main) {
        inputListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputFlag = true;


                // Hole den Text aus dem Textfeld
                String benutzerEingabe = eingabe.getText();

                main.handlingInput(eingabe.getText().trim());

                // Setze das Feld leer und resette das Flag
                eingabe.setText("");
                resetInputFlag();
            }
        };

        screensize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screensize.width / 2;
        height = screensize.height;

        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(width, screensize.height - 5);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(0, 0);


        eingabe = new JTextField();
        eingabe.setBounds(width / 2- 300, height / 2 - 75, 600, 75);
        eingabe.setBackground(Color.LIGHT_GRAY);
        eingabe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        eingabe.addActionListener(inputListener);
        eingabe.requestFocusInWindow();


        ausgabe = new JTextField();
        ausgabe.setBounds(width / 2- 300, (height / 2) - 150, 600, 75);
        ausgabe.setEditable(false);
        ausgabe.setBackground(Color.LIGHT_GRAY);
        ausgabe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));


        frame.add(eingabe);
        frame.add(ausgabe);

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getEingabe() {
        return eingabe;
    }

    public JTextField getAusgabe() {
        return ausgabe;
    }




    public boolean isInputReady() {
        return inputFlag;
    }

    public String getInputText() {
        return eingabe.getText();
    }

    public boolean resetInputFlag() {
        return inputFlag = false;
    }

}
