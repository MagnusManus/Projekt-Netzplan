import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NpE {
    private JFrame frame;
    private JPanel panel;
    private JTextField eingabe;
    private JTextArea ausgabe;
    private Dimension screensize;
    private int width;
    private int height;
    private boolean inputFlag;


    public NpE(Main main) {

        screensize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screensize.width / 2;
        height = screensize.height;

        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(width, screensize.height - 5);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(0, 0);


        eingabe = new JTextField();
        eingabe.setBounds(width / 2- 300, height / 2 - 75, 600, 55);
        eingabe.setBackground(Color.LIGHT_GRAY);
        eingabe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        eingabe.requestFocusInWindow();


        ausgabe = new JTextArea();
        ausgabe.setBounds(width / 2- 300, (height / 2) - 150, 600, 55);
        ausgabe.setBackground(Color.LIGHT_GRAY);
        ausgabe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        ausgabe.setLineWrap(true);
        ausgabe.setWrapStyleWord(true);
        ausgabe.setEditable(false);


        eingabe.addActionListener(new ActionListener() { // Trigger bei Enter
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hole die Benutzereingabe aus dem Textfeld
                String benutzerEingabe = eingabe.getText().trim(); // Entferne führende/trailing Leerzeichen

                // Übergibt die Eingabe an `Main`, um sie dort zu verarbeiten
                main.handlingInput(benutzerEingabe);

                // Eingabetextfeld leeren
                eingabe.setText("");
            }
        });

        frame.add(eingabe);
        frame.add(ausgabe);

        frame.setVisible(true);
    }

    public JTextField getEingabe() {
        return eingabe;
    }

    public JTextArea getAusgabe() {
        return ausgabe;
    }


}
