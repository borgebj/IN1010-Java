
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Oblig5 {
    public static void main(String[] args) {
        String filnavn;

        if (args.length > 0) {
            filnavn = args[0];
        } else {
            System.out.println("FEIL! Riktig bruk av programmet: "
                    +"java Oblig5 <labyrintfil>");
            return;
        }
        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }

        // lagt til selV: skriver ut brettet med hjelpe-tall paa siden og toppen
        l.skrivMedTall();

        // les start-koordinater fra standard input
        Scanner inn = new Scanner(System.in);
        System.out.print("Skriv inn koordinater <kolonne> <rad> ('a' for aa avslutte) \n > ");
        String[] ord = inn.nextLine().split(" ");
        System.out.println("\nAlle utveier:");
        while (!ord[0].equals("a")) {

            try {
                int startKol = Integer.parseInt(ord[0]);
                int startRad = Integer.parseInt(ord[1]);

                Liste<String> utveier = l.finnUtveiFra(startKol, startRad);
                if (utveier.stoerrelse() != 0) {
                    for (String s : utveier) {
                        System.out.println(s);
                    }
                    // lagt til selv: Skriver ut antall utveier og den korteste utveien
                    System.out.println("\nAntall utveier: " + utveier.stoerrelse());
                    System.out.println("Korteste utvei: " + l.finnKortestUtvei() + "\n");

                } else {
                    System.out.println("Ingen utveier.");
                }
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input!");
            }

            System.out.println("Skriv inn nye koordinater ('a' for aa avslutte)");
            ord = inn.nextLine().split(" ");
        }
    }
}