import swing.InputHandler;

import java.util.ArrayList;

public class Main implements InputHandler {
    private Netzplan netzplan;
    private NpE npE; // Benutzeroberfläche
    private ArrayList<Arbeitspaket> arbeitspaketeListe; // Liste der Arbeitspakete
    private int step; // Iterator für Schritte
    private int anzahlVorgaenger; // Anzahl der Vorgänger
    private Arbeitspaket nextArbeitspaket; // Das aktuelle Arbeitspaket

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        // Initialisiere Variablen
        step = 0;
        arbeitspaketeListe = new ArrayList<>();
        netzplan = new Netzplan();
        npE = new NpE(this);

        // Initiale Benutzerausgabe
        npE.getAusgabe().setText("Bitte geben Sie den Namen des ersten Arbeitspakets ein:");

    }

    public void handlingInput(String input) {
        try {
            switch (step) {
                case 0: // Initialer Schritt: Neues Arbeitspaket erstellen und Name festlegen
                    nextArbeitspaket = new Arbeitspaket();
                    arbeitspaketeListe.add(nextArbeitspaket);
                    nextArbeitspaket.setName(input);
                    step++; // Gehe zu Schritt 1
                    npE.getAusgabe().setText("Bitte geben Sie die Dauer ein:");
                    break;

                case 1: // Schritt 1: Dauer eingeben
                    int dauer = Integer.parseInt(input); // Versuche, die Eingabe zu einer Zahl zu konvertieren
                    nextArbeitspaket.setDauer(dauer);
                    step++; // Gehe zu Schritt 2
                    npE.getAusgabe().setText("Wie viele Vorgänger hat dieses Arbeitspaket?");
                    break;

                case 2: // Schritt 2: Anzahl der Vorgänger

                    anzahlVorgaenger = Integer.parseInt(input); // Konvertiere Eingabe
                    if (anzahlVorgaenger > 0) {
                        npE.getAusgabe().setText("Bitte geben Sie die Namen der Vorgänger ein:");
                        step++;
                    } else {
                        npE.getAusgabe().setText("Keine Vorgänger. Arbeitspaket abgeschlossen. Möchten Sie weitere Arbeitspakete erstellen? (j / n)");
                        step += 2;
                    }
                    break;

                case 3: // Schritt 3: Vorgängername eingeben
                    String vorgaenger = input;
                    boolean found = false;
                    for (Arbeitspaket ap : arbeitspaketeListe) {
                        if (ap.getName().equals(vorgaenger)) {
                            nextArbeitspaket.getVorgaengerliste().add(ap);
                            ap.getNachfolgerListe().add(nextArbeitspaket);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        npE.getAusgabe().setText("Vorgänger nicht gefunden. Bitte erneut eingeben.");
                    } else {
                        anzahlVorgaenger--;
                        if (anzahlVorgaenger > 0) {
                            npE.getAusgabe().setText("Bitte geben Sie weitere Vorgänger ein.");
                        } else {
                            npE.getAusgabe().setText("Alle Vorgänger wurden eingegeben. Möchten Sie weitere Arbeitspakete erstellen?");
                            step++; // Weiter mit Schritt 5
                        }
                    }
                    break;

                case 4:// Schritt 5: Weitere Arbeitspakete erstellen?
                    System.out.println("Text wird gesetzt");
                    provisorischeAusgabe();
                    if (input.trim().equals("j")) {
                        npE.getAusgabe().setText("Bitte geben Sie den Namen des nächsten Arbeitspakets ein:");
                        step = 0; // Beginne von vorne
                    } else {
                        npE.getAusgabe().setText("Berechnung des Netzplans wird gestartet...");
                        berechneNetzplan();
                    }
                    break;

                default:
                    npE.getAusgabe().setText("Unbekannter Schritt. Programm beendet.");
                    break;
            }
        } catch (NumberFormatException ex) {
            npE.getAusgabe().setText("Fehler: Bitte eine gültige Zahl eingeben!");
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
            sb.append(String.format("FAZ: %s\t\tFEZ: %s%n\tVorgang: %s%nDauer: %d\tGP: %d\tFP: %d%nSAZ: %d\t\tSEZ: %d%n------------------------------------------------------%n", ap.getFAZ(), ap.getFEZ(), ap.getName(), ap.getDauer(), ap.getGesamtpuffer(), ap.getFreierPuffer(), ap.getSAZ(), ap.getSEZ()));
        }
        netzplan.setTextToScrollPane(sb.toString());
    }

    public void provisorischeAusgabe() {
        StringBuilder sb = new StringBuilder();
        for (Arbeitspaket ap : arbeitspaketeListe) {
            sb.append(String.format("Name: %s\tDauer: %d;", ap.getName(), ap.getDauer()));
        }
        netzplan.setTextToScrollPane(sb.toString());
    }

}