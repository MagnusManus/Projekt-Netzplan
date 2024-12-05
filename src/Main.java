import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.ErrorManager;

public class Main {

    public static void main(String[] args) {
        ArrayList<Arbeitspaket> listeDerArbeitspakete = new ArrayList<>();
        boolean nochEinArbeitspaket = true;
        boolean wolleSpeichern = false;


        System.out.println("Möchten Sie Arbeitspakete anlegen (1) oder abrufen (2) ?");
        Scanner scanner = new Scanner(System.in);
        if ((scanner.nextLine().equals("2"))) {
            Netzplan.arbeitspaketeAbrufen();


        } else {


            while (nochEinArbeitspaket) {
                ArrayList<Integer> result = new ArrayList<>();
                int dauer = 0;
                String name;


                System.out.println("Bitte geben Sie den Namen des Vorgangs ein: ");
                name = scanner.nextLine();

                System.out.println("Bitte geben Sie die Dauer des Vorgangs ein: ");
                try {
                    dauer = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Bitte geben Sie eine Zahl ein: ");
                }
                scanner.nextLine();

                System.out.println("Bitte geben Sie den oder die (komma-getrennt) Vorgänger ein: ");
                try {
                    String[] temp = (scanner.nextLine().split(","));
                    for (String s : temp) {
                        result.add(Integer.parseInt(s));
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Bitte eine kommaseparierte Liste an Zahlen eingeben");
                }

                listeDerArbeitspakete.add(Arbeitspaket.erstelleAp(name, dauer, result));

                System.out.println("Möchten Sie ein weiteres Arbeitspaket anlegen? (j/n) ");
                if (scanner.nextLine().equals("n")) {
                    nochEinArbeitspaket = false;
                }


                for (int i = 0; i < listeDerArbeitspakete.size(); i++) {
                    System.out.print("| Name: " + listeDerArbeitspakete.get(i).getName() + "|");
                    System.out.print(" Dauer " + listeDerArbeitspakete.get(i).getDauer() + "|");
                    System.out.println(" Vorgänger " + listeDerArbeitspakete.get(i).getVorgaengerliste().toString() + "|");
                }

                System.out.println("Möchten Sie die Arbeitspakete abspeichern? (j/n)");
                if
                (scanner.nextLine().equals("j")) {

                    for (int i = 0; i < listeDerArbeitspakete.size(); i++) {
                        try (FileOutputStream fos = new FileOutputStream("/home/oliverkirsch/Downloads/arbeitspaketeDaten/arbeitspaket");
                             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                            oos.writeObject(listeDerArbeitspakete);

                        } catch (FileNotFoundException e) {
                            System.out.println("File not found " + e);
                            throw new RuntimeException(e);
                        } catch (IOException ioe) {
                            System.out.println("Error writing data " + ioe);
                            ioe.printStackTrace();
                        }
                    }
                }
            }
        }


    }
}