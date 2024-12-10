import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private Netzplan netzplan;
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
        netzplan = new Netzplan();

        arbeitspaketeListe = new ArrayList<>();

        sc = new Scanner(System.in);

        while (true) {
            System.out.println("Bitte geben Sie den Namen des Arbeitspakets ein.");
            String chkname = sc.nextLine();
            if (chkname.trim() != "") {
                name = chkname;
                break;
            } else {
                System.out.println("Feld darf nicht leer sein!");
            }

        }

        Arbeitspaket arbeitspaket = new Arbeitspaket();

        arbeitspaket.setName(name);

        while (true) {
            System.out.println("Bitte geben Sie die Dauer des Arbeitspakets ein.");
            try {
                dauer = sc.nextInt();
                arbeitspaket.setDauer(dauer);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Bitte geben sie eine ->Zahl<- ein");
                sc.nextLine();
            }
        }

        sc.nextLine(); // Puffer leeren

        arbeitspaketeListe.add(arbeitspaket);

        System.out.println(arbeitspaket.toString());


        while (true) {
            System.out.println("Gibt es weitere Arbeitspakete? (j / n)");
            String check = sc.nextLine();
            if (check.equals("j")) {

                Arbeitspaket nextArbeitspaket = new Arbeitspaket();
                arbeitspaketeListe.add(nextArbeitspaket);


                while (true) {
                    System.out.println("Bitte geben Sie den Namen des Arbeitspakets ein.");
                    String chkname = sc.nextLine();
                    if (chkname.trim() != "") {
                        nextArbeitspaket.setName(chkname);
                        break;
                    } else {
                        System.out.println("Feld darf nicht leer sein!");
                    }
                }


                while (true) {
                    try {
                        System.out.println("Bitte geben Sie die Dauer des Arbeitspakets ein.");
                        nextArbeitspaket.setDauer(sc.nextInt());
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Bitte geben sie eine ->Zahl<- ein");
                        sc.nextLine();
                    }
                }


                while (true) {
                    try {
                        System.out.printf("Von wie vielen Arbeitspaketen (Vorgänger) hängt %s ab? -> Zahl muss kleinergleich Anzahl bisheriger Arbeitspakete sein!" + "\n", nextArbeitspaket.getName());
                        int chk = sc.nextInt();
                        if (chk < arbeitspaketeListe.size()) {
                            anzahlVorgaenger = chk;
                            break;
                        } else {
                            continue;
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Bitte geben sie eine ->Zahl<- ein");
                        sc.nextLine();
                    }
                }
                sc.nextLine();

                boolean chkSize = true;
                while (chkSize) {
                    for (int i = 0; i < anzahlVorgaenger; i++) {

                        System.out.println("Von WELCHEN Arbeitspaketen hängt dieses ab?");

                        System.out.printf("Es werden noch %d Eingaben erwartet..." + "\n", anzahlVorgaenger - i);
                        String vorgaenger = sc.nextLine();

                        for (Arbeitspaket ap : arbeitspaketeListe) {

                            if (vorgaenger.equals(ap.getName())) {
                                nextArbeitspaket.getVorgaengerliste().add(ap);
                                ap.getNachfolgerListe().add(nextArbeitspaket);
                                System.out.printf("Vorgänger %s wurde zu %s hinzugefügt.%n", ap.getName(), nextArbeitspaket.getName());
                                System.out.printf("Nachfolger %s wurde zu %s hinzugefügt.%n", nextArbeitspaket.getName(), ap.getName());

                            }
                            if (anzahlVorgaenger == nextArbeitspaket.getVorgaengerliste().size()) {
                                chkSize = false;
                                break;
                            }
                        }
                    }
                }
            } else if (check.equals("n")) {
                System.out.println("Berechnung des Netzplans wird durchgeführt...");
                berechneNetzplan();
                System.out.println("Alle Arbeitspakete mit berechneten FAZ und FEZ:");
                for (Arbeitspaket ap : arbeitspaketeListe) {
                    System.out.printf(ANSI_GREEN + "FAZ: %s\t\t\t\t\t\t\tFEZ: %s%n\t\tVorgang: %s%n\tDauer: %d\tGP: %d\t\tFP: %d%nSAZ: %d\t\t\t\t\t\t\tSEZ: %d%n-----------------------------------------------%n" + ANSI_RESET, ap.getFAZ(), ap.getFEZ(), ap.getName(), ap.getDauer(), ap.getGesamtpuffer(), ap.getFreierPuffer(), ap.getSAZ(), ap.getSEZ());
                }


                System.out.println("Alle Arbeitspakete wurden erstellt. Viel Glück. Das Programm wird beendet...");
                break;
            } else {
                System.out.println("Bitte j oder n eingeben");
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
            ap.berechneFreienPuffer();
        }

        StringBuilder sb = new StringBuilder();
        for (Arbeitspaket ap : arbeitspaketeListe) {
            sb.append(String.format(
                    "FAZ: %s\t\tFEZ: %s%n\tVorgang: %s%nDauer: %d\tGP: %d\tFP: %d%nSAZ: %d\t\tSEZ: %d%n------------------------------------------------------%n", ap.getFAZ(), ap.getFEZ(), ap.getName(), ap.getDauer(), ap.getGesamtpuffer(), ap.getFreierPuffer(), ap.getSAZ(), ap.getSEZ()
            ));
        }
        netzplan.setTextToScrollPane(sb.toString());
    }
}


