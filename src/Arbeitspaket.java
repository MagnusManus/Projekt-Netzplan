public class Arbeitspaket {
    private String name = "";
    private int vorgang;
    private int vorgaenger;
    private int fruehesterAnfangszeitpunkt;
    private int fruehesterEndzeitpunkt;
    private int spaetesterAnfangszeitpunkt;
    private int spaetesterEndzeitpunkt;
    private int gesamtpuffer;
    private int freierPuffer;


    public Arbeitspaket(String name, int dauer) {

    }

    public Arbeitspaket(String name, int dauer, int vorgaenger) {

    }

    public Arbeitspaket(String name, int dauer, int vorgaenger, int vorgaenger2) {

    }

    public Arbeitspaket(String name, int dauer, int vorgaenger, int vorgaenger2, int vorgaenge3) {

    }

    public int getVorgaenger() {

    }


    public int getFruehesterAnfangszeitpunkt() {

    }

    public int getFruehesterEndzeitpunkt() {

    }

    public int getSpaetesterAnfangszeitpunkt() {

    }

    public int getSpaetesterEndzeitpunkt() {

    }

    public int getGesamtpuffer() {

    }

    public int getFreierPuffer() {

    }

    public String getName() {

    }

    public void setFruehesterAnfangszeitpunkt() {

    }

    public void setFruehesterEndzeitpunkt() {

    }

    public void setSpaetesterAnfangszeitpunkt() {

    }

    public void setSpaetesterEndzeitpunkt() {

    }

    public void setFreierPuffer() {

    }

    public void setGesamtpuffer() {

    }

    //getter überladen für Berechnungen
    public int getFruehesterAnfangszeitpunkt(int a, int b, int c) {
        return Math.max(a, (b, c));
    }

    public int getFruehesterAnfangszeitpunkt(int a, int b) {

    }

    public int getFruehesterAnfangszeitpunkt(int a) {

    }

}

