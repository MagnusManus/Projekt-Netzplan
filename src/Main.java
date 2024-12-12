import swing.InputHandler;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main implements InputHandler {
    private Netzplan netzplan;
    private NpE npE;
    private String name;
    private int dauer;
    private int anzahlVorgaenger;
    private ArrayList<Arbeitspaket> arbeitspaketeListe;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private int step;

    public static void main(String[] args) {
        new Main();
    }


    public Main() {
        step = 0;
        netzplan = new Netzplan();
        npE = new NpE(this);
        arbeitspaketeListe = new ArrayList<>();

//        while (true) {
//            npE.getAusgabe().setText("Bitte geben Sie die Dauer des Arbeitspakets ein.");
//            try {
//                arbeitspaket.setDauer(Integer.parseInt(npE.getEingabe().getText()));
//                break;
//            } catch (InputMismatchException e) {
//                npE.getAusgabe().setText("Bitte geben sie eine ->Zahl<- ein");
//            }
//        }
        while (true) {
            System.out.println("Gibt es weitere Arbeitspakete? (j / n)");
            String check = npE.getEingabe().getText();
            if (check.equals("j")) {
                Arbeitspaket nextArbeitspaket = new Arbeitspaket();
                arbeitspaketeListe.add(nextArbeitspaket);
                while (true) {
                    System.out.println("Bitte geben Sie den Namen des Arbeitspakets ein.");
                    String chkname = npE.getEingabe().getText();
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
                        nextArbeitspaket.setDauer(Integer.parseInt(npE.getEingabe().getText()));
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Bitte geben sie eine ->Zahl<- ein");
                    }
                }
                while (true) {
                    try {
                        System.out.printf("Von wie vielen Arbeitspaketen (Vorgänger) hängt %s ab? -> Zahl muss kleinergleich Anzahl bisheriger Arbeitspakete sein!" + "\n", nextArbeitspaket.getName());
                        int chk = Integer.parseInt(npE.getEingabe().getText());
                        if (chk < arbeitspaketeListe.size()) {
                            anzahlVorgaenger = chk;
                            break;
                        } else {
                            continue;
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Bitte geben sie eine ->Zahl<- ein");
                    }
                }
                while (true) {
                    for (int i = 0; i < anzahlVorgaenger; i++) {

                        npE.getAusgabe().setText("Von WELCHEN Arbeitspaketen hängt dieses ab?");

                        npE.getAusgabe().setText(String.format("Es werden noch %d Eingaben erwartet..." + "\n", anzahlVorgaenger - i));
                        String vorgaenger = npE.getEingabe().getText();

                        for (Arbeitspaket ap : arbeitspaketeListe) {

                            if (vorgaenger.equals(ap.getName())) {
                                nextArbeitspaket.getVorgaengerliste().add(ap);
                                ap.getNachfolgerListe().add(nextArbeitspaket);
                                npE.getAusgabe().setText(String.format("Vorgänger %s wurde zu %s hinzugefügt.%n", ap.getName(), nextArbeitspaket.getName()));
                                npE.getAusgabe().setText(String.format("Nachfolger %s wurde zu %s hinzugefügt.%n", nextArbeitspaket.getName(), ap.getName()));

                            }
                            if (anzahlVorgaenger == nextArbeitspaket.getVorgaengerliste().size()) {
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

    public String wartenAufEingabe() {
        while (!npE.isInputReady()) {
            try {
                Thread.sleep(100); // Warten, bis Eingabe bereit ist
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String input = npE.getInputText();  // Text aus dem GUI holen
        npE.resetInputFlag();  // Reset der Eingabebereitschaft
        return input;  // Gib den Text zurück
    }


    public void handlingInput(String input) {

        Arbeitspaket nextArbeitspaket = new Arbeitspaket();
        arbeitspaketeListe.add(nextArbeitspaket);


        switch (step) {
            case 0:
                nextArbeitspaket.setName(input);
                npE.getAusgabe().setText(name + " wurde als Name gespeichert.");
                break;
            case 2:
                nextArbeitspaket.setDauer(Integer.parseInt(input));
                npE.getAusgabe().setText(dauer + "wurde als Dauer in Tagen festgelegt.");
                break;
            case 3:
                anzahlVorgaenger = Integer.parseInt(input);
                if (anzahlVorgaenger == 0) {
                    step = 4;
                } else {
                    npE.getAusgabe().setText("Von welchen Arbeitspaketen ist dieses abhängig?");
                    break;
                }
            case 4:
                for (int i = 0; i < anzahlVorgaenger; i++) {

                    npE.getAusgabe().setText("Noch " + (anzahlVorgaenger - i) + " Eingaben erwartet...");
                    String vorgaenger = npE.getEingabe().getText();

                    for (Arbeitspaket ap : arbeitspaketeListe) {

                        if (vorgaenger.equals(ap.getName())) {
                            nextArbeitspaket.getVorgaengerliste().add(ap);
                            ap.getNachfolgerListe().add(nextArbeitspaket);
                            npE.getAusgabe().setText(String.format("Vorgänger %s wurde zu %s hinzugefügt.%n", ap.getName(), nextArbeitspaket.getName()));
                            npE.getAusgabe().setText(String.format("Nachfolger %s wurde zu %s hinzugefügt.%n", nextArbeitspaket.getName(), ap.getName()));

                        }
                        if (anzahlVorgaenger == nextArbeitspaket.getVorgaengerliste().size()) {
                            break;
                        }
                    }
                }

                break;
            case 5:
        }

    }

    public void updatePrompt() {
        switch (step) {
            case 0:
                npE.getAusgabe().setText("Bitte geben Sie den Namen des Arbeitspaketes ein.");
                break;
            case 2:
                npE.getAusgabe().setText("Bitte geben Sie die Dauer des Arbeitspaketes ein.");
                break;
            default:
                npE.getAusgabe().setText("Vielen Dank für Ihre Eingaben.");
                npE.getEingabe().setEditable(false);
        }
        npE.getEingabe().setText("");
    }
}


