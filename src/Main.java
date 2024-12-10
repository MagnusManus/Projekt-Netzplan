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
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

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

                    for (Arbeitspaket ap : arbeitspaketeListe) {

                        if (vorgaenger.equals(ap.getName())) {
                            nextArbeitspaket.getVorgaengerliste().add(ap);
                            ap.getNachfolgerListe().add(nextArbeitspaket);
                            System.out.printf("Vorgänger %s wurde zu %s hinzugefügt.%n", ap.getName(), nextArbeitspaket.getName());
                            System.out.printf("Nachfolger %s wurde zu %s hinzugefügt.%n", nextArbeitspaket.getName(), ap.getName());
                        }
                    }
                }
                System.out.println(nextArbeitspaket.toString() + nextArbeitspaket.includingList());
            } else {
                System.out.println("Berechnung des Netzplans wird durchgeführt..." + "\n");
                berechneNetzplan();
                System.out.println("Alle Arbeitspakete berechnet:" + "\n");
                for (Arbeitspaket ap : arbeitspaketeListe) {
                    System.out.printf(ANSI_GREEN + "FAZ: %s\t\t\t\t\t\t\tFEZ: %s%n\t\tVorgang: %s%n\tDauer: %d\tGP: %d\t\tFP: %d%nSAZ: %d\t\t\t\t\t\t\tSEZ: %d%n-----------------------------------------------%n" + ANSI_RESET, ap.getFAZ(), ap.getFEZ(), ap.getName(), ap.getDauer(), ap.getGesamtpuffer(), ap.getFreierPuffer(), ap.getSAZ(), ap.getSEZ());
                }

                System.out.println("Alle Arbeitspakete wurden erstellt. Viel Glück. Das Programm wird beendet...");
                break;
            }
        }
    }

    public void berechneNetzplan() {
        for (Arbeitspaket arbeitspaket : arbeitspaketeListe) {
            arbeitspaket.berechneFAZundFEZ();
        }
        for (int i = arbeitspaketeListe.size() - 1; i >= 0; i--) {
            arbeitspaketeListe.get(i).berechneSAZundSEZ();
        }
        for (Arbeitspaket ap : arbeitspaketeListe) {
            ap.berechneGesamtpuffer();
            ap.berechneFreienPuffer(arbeitspaketeListe);
        }

    }
}



