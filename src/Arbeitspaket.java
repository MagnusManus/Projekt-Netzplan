import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Arbeitspaket implements Serializable{
    private Netzplan netzplan;
    private int id;
    private String name;
    private int dauer;
    private ArrayList<Arbeitspaket> vorgaengerliste = new ArrayList<>();
    private static ArrayList<Arbeitspaket> arbeitspaketeListe= new ArrayList();

    //--------------------------------------------------------------------------------------Getter und Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public ArrayList<Arbeitspaket> getVorgaengerliste() {
        return vorgaengerliste;
    }

    public void setVorgaenger(Arbeitspaket arbeitspaket) {
        this.vorgaengerliste.add(arbeitspaket);
    }
//---------------------------------------------------------------------------------------Konstruktor---

    public Arbeitspaket(String name, int dauer, ArrayList<Integer> vorgaengerliste) {

    }

    public Arbeitspaket() {
        this.vorgaengerliste = new ArrayList<>();
    }

    //------------------------------------------------------------------------------


    public String toSTring() {

        String s = "Name: " + getName() + "Dauer: " + getDauer() + "Vorgänger: " + getVorgaengerliste().toString();
        return s;
    }

//    public static Arbeitspaket creatingAP(String name) {
//        Arbeitspaket arbeitspaket = new Arbeitspaket();
//        setName(name);
//        return arbeitspaket;
//    }

    public void setCredentials(String name, int dauer) {
        setName(name);
        setDauer(dauer);
    }

    public void setCredentials(String name, int dauer, Arbeitspaket arbeitspaket) {
        setName(name);
        setDauer(dauer);
        setVorgaenger(arbeitspaket);
    }

    public void setCredentials(String name, int dauer,Arbeitspaket arbeitspaket, Arbeitspaket arbeitspaket2) {
        setName(name);
        setDauer(dauer);
        setVorgaenger(arbeitspaket);
        setVorgaenger(arbeitspaket2);
    }

    public void setCredentials(String name, int dauer,Arbeitspaket arbeitspaket, Arbeitspaket arbeitspaket2, Arbeitspaket arbeitspaket3) {
        setName(name);
        setDauer(dauer);
        setVorgaenger(arbeitspaket);
        setVorgaenger(arbeitspaket2);
        setVorgaenger(arbeitspaket3);
    }


    public void checkReadOrWrite() {
        //Prüfung, ob der Nutzer APs anlegen oder bereits bestehende abrufen will
        System.out.println("Möchten Sie Arbeitspakete anlegen (1) oder abrufen (2) ?");
        Scanner scanner = new Scanner(System.in);
        if ((scanner.nextLine().equals("2"))) {
            netzplan.arbeitspaketeAbrufen();
        } else {
            System.out.println("Ein neues Arbeitspaket wird angelegt...");
        }
    }


    public ArrayList<Arbeitspaket> setVorgaengerInListe(Arbeitspaket arbeitspaket) {
        this.vorgaengerliste.add(arbeitspaket);
        return vorgaengerliste;
    }

    public ArrayList<Arbeitspaket> setVorgaengerInListe(Arbeitspaket arbeitspaket, Arbeitspaket arbeitspaket2) {
        this.vorgaengerliste.add(arbeitspaket);
        this.vorgaengerliste.add(arbeitspaket2);
        return vorgaengerliste;
    }

    public ArrayList<Arbeitspaket> setVorgaengerInListe(Arbeitspaket arbeitspaket, Arbeitspaket arbeitspaket2, Arbeitspaket arbeitspaket3) {
        this.vorgaengerliste.add(arbeitspaket);
        this.vorgaengerliste.add(arbeitspaket2);
        this.vorgaengerliste.add(arbeitspaket3);
        return vorgaengerliste;
    }


}