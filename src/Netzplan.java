import java.io.*;
import java.util.ArrayList;

public class Netzplan {
    private ArrayList<Arbeitspaket> listeDerArbeitspakete = new ArrayList<>();

    public Netzplan() {

    }


    public void arbeitspaketeAbrufen() {
        ArrayList<Arbeitspaket> listeDerArbeitsPakete = null;
        try
                (FileInputStream fis = new FileInputStream("/home/manuelpalluotto/Downloads/arbeitspaket");
                 ObjectInputStream ois = new ObjectInputStream(fis);) {
            listeDerArbeitsPakete = (ArrayList) ois.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
        }
        for (Arbeitspaket ap : listeDerArbeitsPakete) {
            System.out.println(ap.getName() + ap.getDauer() + ap.getVorgaengerliste());
        }
    }


}