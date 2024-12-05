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

    public static void main(String[] args) {
        new Main();
    }


    public Main() {
        sc = new Scanner(System.in);
        name = sc.nextLine();

        Arbeitspaket arbeitspaket = new Arbeitspaket();
        arbeitspaket.setName(name);







    }
}