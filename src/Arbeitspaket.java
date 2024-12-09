import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Arbeitspaket implements Serializable {
    private Netzplan netzplan;
    private int FAZ;
    private int FEZ;
    private int SAZ;
    private int SEZ;
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

    public int getFAZ() {
        return FAZ;
    }

    public void setFAZ(int FAZ) {
        this.FAZ = FAZ;
    }

    public int getFEZ() {
        return FEZ;
    }

    public void setFEZ() {
        FEZ = FAZ + dauer;
    }

    public void setFEZ(int fez) {
        this.FEZ = fez;
    }

    public int getSAZ() {
        return SAZ;
    }

    public int getSEZ() {
        return SEZ;
    }

    public void setSAZ(int SAZ) {
        this.SAZ = SAZ;
    }

    public void setSEZ(int SEZ) {
        this.SEZ = SEZ;
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

    public void berechneFAZundFEZ() {
        if (vorgaengerliste.isEmpty()) {
            // Kein Vorgänger, FAZ ist 0
            this.FAZ = 0;
        } else {
            // Der FAZ ist das höchste FEZ aller Vorgänger
            int maxFEZ = 0;
            for (Arbeitspaket vorgaenger : vorgaengerliste) {
                if (vorgaenger.getFEZ() > maxFEZ) {
                    maxFEZ = vorgaenger.getFEZ();
                }
            }
            this.FAZ = maxFEZ;
        }
        // FEZ = FAZ + Dauer
        this.FEZ = this.FAZ + this.dauer;
    }

    public void berechneSAZundSEZ() {

    }






}



