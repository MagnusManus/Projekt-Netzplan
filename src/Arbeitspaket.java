public class Arbeitspaket {
    private String name = "";
    private int id;
    private int vorgaenger;
    private int fruehesterAnfangszeitpunkt;
    private int fruehesterEndzeitpunkt;
    private int spaetesterAnfangszeitpunkt;
    private int spaetesterEndzeitpunkt;
    private int gesamtpuffer;
    private int freierPuffer;
    private int dauer;
    private int vorgaenger2;
    private int vorgaenger3;



    public Arbeitspaket(String name, int dauer) {
        this.name = name;
        this.dauer = dauer;
    }

    public Arbeitspaket(String name, int dauer, int vorgaenger) {
        this.name = name;
        this.dauer = dauer;
        this.vorgaenger = vorgaenger;
    }


    public Arbeitspaket(String name, int dauer, int vorgaenger, int vorgaenger2) {
        this.name = name;
        this.dauer = dauer;
        this.vorgaenger = vorgaenger;
        this.vorgaenger2 = vorgaenger2;
    }

    public Arbeitspaket(String name, int dauer, int vorgaenger, int vorgaenger2, int vorgaenge3) {
        this.name = name;
        this.dauer = dauer;
        this.vorgaenger = vorgaenger;
        this.vorgaenger2 = vorgaenger2;
        this.vorgaenger3 = vorgaenge3;
    }

    public int getVorgaenger() {
        return vorgaenger;
    }


    public int getFruehesterAnfangszeitpunkt() {
        return fruehesterAnfangszeitpunkt;
    }

    public int getFruehesterEndzeitpunkt() {
        return fruehesterEndzeitpunkt;
    }

    public int getSpaetesterAnfangszeitpunkt() {
        return spaetesterAnfangszeitpunkt;
    }

    public int getSpaetesterEndzeitpunkt() {
        return spaetesterEndzeitpunkt;
    }

    public int getGesamtpuffer() {
        return gesamtpuffer;
    }

    public int getFreierPuffer() {
        return freierPuffer;
    }

    public String getName() {
        return name;
    }

    public void setFruehesterAnfangszeitpunkt(int fruehesterAnfangszeitpunkt) {
        this.fruehesterAnfangszeitpunkt = fruehesterAnfangszeitpunkt;
    }

    public void setFruehesterEndzeitpunkt(int fruehesterEndzeitpunkt) {
        this.fruehesterEndzeitpunkt = fruehesterEndzeitpunkt;
    }

    public void setSpaetesterAnfangszeitpunkt(int spaetesterAnfangszeitpunkt) {
        this.spaetesterAnfangszeitpunkt = spaetesterAnfangszeitpunkt;
    }

    public void setSpaetesterEndzeitpunkt(int spaetesterEndzeitpunkt) {
        this.spaetesterEndzeitpunkt = spaetesterEndzeitpunkt;
    }

    public void setFreierPuffer(int freierPuffer) {
        this.freierPuffer = freierPuffer;
    }

    public void setGesamtpuffer(int gesamtpuffer) {
        this.gesamtpuffer = gesamtpuffer;
    }

    //getter überladen für Berechnungen
    public int getFruehesterAnfangszeitpunkt(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    public int getFruehesterAnfangszeitpunkt(int a, int b) {
        return Math.max(a, b);
    }

    public int getFruehesterAnfangszeitpunkt(int a) {
        return a;
    }

}
