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

    /** Deler av hovedmenyen**/
    // metode som kjoerer "Utskriftsdelen" av programmet
    public static void utskriftsDel(Legesystem lege) {
        Scanner scanner = new Scanner(System.in);

        // viser meny og spoer bruker om input
        utskriftMeny();
        System.out.print("Hva onsker du aa skrive ut? \n > ");
        String kommando = scanner.next().toLowerCase();
        delay(800);


        // kommando-loekke som fortsetter bruker-input
        while (!kommando.equals("a")) {

            switch (kommando) {

                // om input er 1 - skriv alle pasienter
                case "1":
                    lege.skrivPasienter();
                    delay(500);
                    break;

                // om input er 2 - skriv alle leger
                case "2":
                    lege.skrivLeger();
                    delay(500);
                    break;

                // om input er 3 - skriv alle legemidler
                case "3":
                    lege.skrivLegemidler();
                    delay(500);
                    break;

                // om input er 4 - skriv alle resepter
                case "4":
                    lege.skrivResepter();
                    delay(500);
                    break;

                // om ingenting slaar til - gi feilmelding
                default:
                    System.out.println("\nUgyldig kommando - prov igjen\n");
            }

            // spoer bruker paa nytt og oppdaterer loekke
            utskriftMeny();
            System.out.print("Hva onsker du aa skrive ut? \n > ");
            kommando = scanner.next().toLowerCase();
            delay(800);
        }

        System.out.println("\n\nGaar tilbake...");
        delay(1500);
    }

    public static void opprettDel(Legesystem lege) {
        Scanner scanner = new Scanner(System.in);

        // viser meny og spoer bruker om input
        opprettMeny();
        System.out.print("Hva onsker du aa gjoere? \n > ");
        String kommando = scanner.next().toLowerCase();
        delay(800);


        // kommando-loekke som fortsetter bruker-input
        while (!kommando.equals("a")) {

            switch (kommando) {

                // om input er 1 - opprett Lege
                case "1":
                    lege.leggTilLege();
                    delay(500);
                    break;

                // om input er 2 - opprett Pasient
                case "2":
                    lege.leggTilPasient();
                    delay(500);
                    break;

                // om input er 3 - opprett resept (skrivResept ...)
                case "3":
                    delay(500);
                    break;

                // om input er 4 - opprett legemiddel
                case "4":
                    lege.leggTilLegemiddel();
                    delay(500);
                    break;

                // om ingenting slaar til - gi feilmelding
                default:
                    System.out.println("\nUgyldig kommando - prov igjen\n");
            }

            opprettMeny();
            System.out.print("Hva onsker du aa gjoere? \n > ");
            kommando = scanner.next().toLowerCase();
            delay(800);
        }

        System.out.println("\n\nGaar tilbake... (2)");
        delay(1500);
    }


    /** Console-interfacer **/
    // Terminal "User-interface" for Hovedmeny
    public static void hovedMeny() {
        System.out.println("\n\n----[Hovedmeny]----");
        System.out.println("| 1. Utskrift   - |");
        System.out.println("| 2. Opprette   - |");
        System.out.println("| 5. Bruke      - |");
        System.out.println("| 4. Statistikk - |");
        System.out.println("| - - - - - - - - |");
        System.out.println("|  a.  avslutt    |");
        System.out.println("-------------------\n");
    }

    // Terminal "User-interface" for Utskrift
    public static void utskriftMeny() {
        System.out.println("\n\n----[Utskrift]------");
        System.out.println("| 1. pasienter   - |");
        System.out.println("| 2. Leger       - |");
        System.out.println("| 3. Legemiddler - |");
        System.out.println("| 4. Resepter    - |");
        System.out.println("| - - - -  - - - - |");
        System.out.println("| a.  Tilbake    - |");
        System.out.println("--------------------\n");
    }

    public static void opprettMeny() {
        System.out.println("\n\n----[Opprette]----");
        System.out.println("| 1. Lege       - |");
        System.out.println("| 2. Pasient    - |");
        System.out.println("| 3. Resept     - |");
        System.out.println("| 4. Legemiddel - |");
        System.out.println("| - - - - - - - - |");
        System.out.println("| a.  Tilbake   - |");
        System.out.println("--------------------\n");
    }



    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {

        // lager null-objekt slik at "default" kan slaa av
         Legesystem lege = null;

         // sjekker argument for klassen, og lager legesystem avhengig av tall
        switch(args[0]) {

            // 1 vil si legesystem med LitenEksempelFil
            case "1":
                lege = new Legesystem("LitenEksempelFil.txt"); break;

            // 2 vil si legesystem med StorEksempelFil
            case "2":
                lege = new Legesystem("StorEksempelFil.txt"); break;

            // ellers vil feilmelding slaa av pga objekt med null-verdi
            default:
                throw new NullPointerException("[ Ingen objekt tildelt verdien "+args[0]+", prov igjen med 1 / 2 ]"); }

        // scanner-objekt for aa gi tilgang til bruker-input
        Scanner scanner = new Scanner(System.in);

        // viser meny og spoer bruker om input
        hovedMeny();
        System.out.print("Hva onsker du aa gjoere? \n > ");
        String kommando = scanner.next().toLowerCase();
        delay(800);

        // om input ikke er "a" (avslutt), fortsett og sjekk input med switch-case
        while (!kommando.equals("a")) {
            switch (kommando) {

                // om tall er 1 - kall paa "utskriftsdel"
                case "1":
                    delay(500);
                    utskriftsDel(lege);

                    hovedMeny();
                    System.out.print("Hva onsker du aa gjoere? \n > ");
                    kommando = scanner.next().toLowerCase();
                    delay(800);
                    break;

                    // om tall er 2 - kall paa "opprettDel"
                case "2":
                    delay(500);
                    opprettDel(lege);

                    hovedMeny();
                    System.out.print("Hva onsker du aa gjoere ut? \n > ");
                    kommando = scanner.next().toLowerCase();
                    delay(800);
                    break;

                // om tall er 3 - kall paa "brukDel"
                case "3":
                    delay(500);
                    System.out.println("\nIkke implementert\n");

                    System.out.print("Hva onsker du aa gjoere? \n > ");
                    kommando = scanner.next().toLowerCase();
                    delay(800);
                    break;

                // om tall er 4 - kall paa "statistikkDel"
                case "4":
                    delay(500);
                    System.out.println("\nIkke implementert\n");

                    System.out.print("Hva onsker du aa gjoere? \n > ");
                    kommando = scanner.next().toLowerCase();
                    delay(800);
                    break;

                // ellers er det ugyldig kommando
                default:
                    delay(350);
                    System.out.println("\nUgyldig kommando - prov igjen\n");
                    System.out.print("Hva onsker du aa gjoere? \n > ");
                    kommando = scanner.next().toLowerCase();
                    delay(800);
            }
        }
        System.out.println("\n\nAvslutter...\n\n");
        delay(2000);
    }
}







