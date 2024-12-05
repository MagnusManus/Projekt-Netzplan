import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.ErrorManager;

public class Main {
    private Scanner sc;
    private String name;
    private int dauer;
    private ArrayList<Arbeitspaket> vorgaenger;
    private int anzahlvorgaenger;

    public static void main(String[] args) {
        new Main();
    }


    public Main() {
        vorgaenger = new ArrayList<>();
        sc = new Scanner(System.in);
        System.out.println("Bitte geben Sie den Namen des Arbeitspakets ein.");
        name = sc.nextLine();

        Arbeitspaket arbeitspaket = new Arbeitspaket();
        arbeitspaket.setName(name);

        sc.next();

        System.out.println("Bitte geben Sie die Dauer des Arbeitspakets ein.");
        dauer = sc.nextInt();
        arbeitspaket.setDauer(dauer);

        sc.next();

        System.out.println("Von wie vielen Arbeitspaketen (Vorgängern) hängt dieses Arbeitspaket ab?");
        anzahlvorgaenger = sc.nextInt();

        sc.next();

        System.out.println("Nennen Sie den/ die Vorgänger: ");
        for (int i = 0; i < anzahlvorgaenger; i++) {
            System.out.printf("Noch %d Eingaben erwartet..." + "\n", anzahlvorgaenger - i);

        }







    }
}