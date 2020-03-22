import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
// fil for testing av legesystem! Midlertidig (?)

public class Main {

    // egen"sleep-metode for "artifical-delay"
    public static void delay(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {}
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {

         Legesystem lege = null;

        switch(args[0]) {
            case "1":
                lege = new Legesystem("LitenEksempelFil.txt"); break;
            case "2":
                lege = new Legesystem("StorEksempelFil.txt"); break;
            default:
                throw new NullPointerException("[ Ingen objekt tildelt verdien "+args[0]+", prov igjen med 1 / 2 ]"); }

        Scanner scanner = new Scanner(System.in);


        System.out.println("\n\n- - { Hovedeny } - -");
        System.out.println("\n----[Utskrift]----");
        System.out.println("  - pasienter   -");
        System.out.println("  - Leger       -");
        System.out.println("  - Legemiddler -");
        System.out.println("  - Resepter    -");
        System.out.println("-------------------");
        System.out.println("  - avslutt     -");
        System.out.println("- - - - - - - - - - -");

        System.out.print("Hva onsker du aa skrive ut?: \n > ");
        String kommando = scanner.nextLine().toLowerCase().trim();
        delay(1200);


        while (!kommando.equals("a")) {
            switch (kommando) {
                case "pasienter":
                    lege.skrivPasienter();
                    break;
                case "leger":
                    lege.skrivLeger();
                    break;
                case "legemidler":
                    lege.skrivLegemidler();
                    break;
                case "resepter":
                    lege.skrivResepter();
                    break;
                default:
                    System.out.println("\nUgyldig kommando - prov igjen\n");
            }
            System.out.print("Hva onsker du aa skrive ut? (Pasienter/Leger/Legemidler/Resepter/a): ");
            kommando = scanner.nextLine().toLowerCase().trim();
            delay(2000);
        }


    }
}