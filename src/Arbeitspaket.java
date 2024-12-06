import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Arbeitspaket implements Serializable {
    private Netzplan netzplan;
    private int id;
    private String name;
    private int dauer;
    private ArrayList<Arbeitspaket> vorgaengerliste = new ArrayList<>();
    private static ArrayList<Arbeitspaket> arbeitspaketeListe = new ArrayList();

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

//---------------------------------------------------------------------------------------Konstruktor---

    public Arbeitspaket() {
        this.vorgaengerliste = new ArrayList<>();
    }

    //------------------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("Name:\t\t%s%nDauer:\t\t%d%n", name, dauer);
    }

    public String includingList() {
        if (vorgaengerliste.isEmpty()) {
            return String.format("Das Arbeitspaket %s hat keine Vorgänger.\n", this.name);
        }

        StringBuilder sb = new StringBuilder(
                String.format("Das Arbeitspaket %s ist abhängig von folgenden Arbeitspaketen:\n", this.name)
        );

        for (Arbeitspaket arbeitspaket : vorgaengerliste) {
            sb.append("- ").append(arbeitspaket.getName()).append("\n");
        }

        return sb.toString();
    }
}
