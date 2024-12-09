import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private Scanner sc;
    private String name;
    private int dauer;
    private int anzahlVorgaenger;
    private ArrayList<Arbeitspaket> arbeitspaketeListe;

    public static void main(String[] args) {
        new Main();
    }


    public Main() {
        arbeitspaketeListe = new ArrayList<>();

        sc = new Scanner(System.in);


        System.out.println("Bitte geben Sie den Namen des Arbeitspakets ein.");
        name = sc.nextLine();

        Arbeitspaket arbeitspaket = new Arbeitspaket();
        arbeitspaket.setName(name);

        System.out.println("Bitte geben Sie die Dauer des Arbeitspakets ein.");
        dauer = sc.nextInt();
        arbeitspaket.setDauer(dauer);

        sc.nextLine(); // Puffer leeren

        arbeitspaketeListe.add(arbeitspaket);

        System.out.println(arbeitspaket.toString());


        while (true) {
            System.out.println("Gibt es weitere Arbeitspakete? (j / n)");
            if (sc.nextLine().equals("j")) {

                Arbeitspaket nextArbeitspaket = new Arbeitspaket();
                arbeitspaketeListe.add(nextArbeitspaket);

                System.out.println("Bitte geben Sie den Namen des Arbeitspakets ein.");
                nextArbeitspaket.setName(sc.nextLine());

                System.out.println("Bitte geben Sie die Dauer des Arbeitspakets ein.");
                nextArbeitspaket.setDauer(sc.nextInt());
                sc.nextLine();

                System.out.printf("Von wie vielen Arbeitspaketen (Vorgänger) hängt %s ab?" + "\n", nextArbeitspaket.getName());
                anzahlVorgaenger = sc.nextInt();
                sc.nextLine();

                System.out.println("Von WELCHEN Arbeitspaketen hängt dieses ab?");

                for (int i = 0; i < anzahlVorgaenger; i++) {
                    System.out.printf("Es werden noch %d Eingaben erwartet..." + "\n", anzahlVorgaenger - i);
                    String vorgaenger = sc.nextLine();

                    for (Arbeitspaket value : arbeitspaketeListe) {

                        if (vorgaenger.equals(value.getName())) {
                            nextArbeitspaket.getVorgaengerliste().add(value);
                        }
                    }
                }
                System.out.println(nextArbeitspaket.toString() + nextArbeitspaket.includingList());
            } else {
                System.out.println("Alle Arbeitspakete wurden erstellt. Viel Glück. Das Programm wird beendet...");
                break;
            }
        }
    }
}


