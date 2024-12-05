import java.io.Serializable;
import java.util.ArrayList;

public class Arbeitspaket implements Serializable {
    private int id;
    private  String name;
    private  int dauer;
    private  ArrayList<Integer> vorgaengerliste = new ArrayList<>();

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

    public ArrayList<Integer> getVorgaengerliste() {
        return vorgaengerliste;
    }

    public void setVorgaengerliste(ArrayList<Integer> vorgaengerliste) {
        this.vorgaengerliste = vorgaengerliste;
    }
//---------------------------------------------------------------------------------------Konstruktor---

    public Arbeitspaket(String name, int dauer,ArrayList<Integer> vorgaengerliste) {

    }
    public Arbeitspaket(){

    }

    //------------------------------------------------------------------------------


    public  String toSTring(){

        String s = "Name: " + getName() + "Dauer: " + getDauer() + "Vorgänger: " + getVorgaengerliste().toString();
        return s;
    }

    public static Arbeitspaket erstelleAp(final String name, final int dauer, final ArrayList<Integer> vorgaengerliste){
        Arbeitspaket arbeitspaket = new Arbeitspaket();

        arbeitspaket.setName(name);
        arbeitspaket.setDauer(dauer);
        arbeitspaket.setVorgaengerliste(vorgaengerliste);
        return arbeitspaket;
    }








}