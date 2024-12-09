import java.io.Serializable;
import java.util.ArrayList;

public class Arbeitspaket implements Serializable {
    private Netzplan netzplan;
    private int FAZ;
    private int FEZ;
    private int SAZ;
    private int SEZ;
    private String name;
    private int dauer;
    private int gesamtpuffer;
    private int freierPuffer;
    private ArrayList<Arbeitspaket> nachfolgerListe;
    private ArrayList<Arbeitspaket> vorgaengerliste;

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

    public ArrayList<Arbeitspaket> getNachfolgerListe() {
        return nachfolgerListe;
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
        this.nachfolgerListe = new ArrayList<>();
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
            //wird mit 0 initialisiert, um überschreibbar zu sein
            for (Arbeitspaket vorgaenger : vorgaengerliste) {
                //maxFEZ wird nur dann neu zugewiesen, wenn ein VorgängerFEZ größer als der vorherige ist
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
        if (nachfolgerListe.isEmpty()) {
            this.SAZ = this.FAZ;
            this.SEZ = this.FEZ;
        } else {
            int minSAZ = 100;
            for (Arbeitspaket nachfolger : nachfolgerListe) {
                if (nachfolger.getSAZ() < minSAZ) {
                    minSAZ = nachfolger.getSAZ();
                }
            }
            this.SEZ = minSAZ;
            this.SAZ = this.SEZ - this.dauer;
        }
    }

    public void berechneGesamtpuffer() {
        // GP = FolgeSAZmin - FEZ
        // SAZ - FAZ || SEZ - FEZ
    }

    public void berechneFreienPuffer() {}
        // FP = FolgeFAZmin - FEZ
        // FAZ Nachfolger - FEZ Aktuell
}

//letzes ap -> SEZ = FEZ
//          -> SAZ = SEZ - Dauer
//          maximum der SAZ der nachfolger = SEZ von ap
//









