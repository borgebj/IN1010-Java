import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import java.util.InputMismatchException;


public class Legesystem {

    // lister som holder styr paa pasienter, legemidler, leger og resepter
    Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
    Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();

    // timestamp-objekter
    Date date= new Date();
    long time = date.getTime();
    Timestamp timestamp = new Timestamp(time);

    // variabler som holder antall feil paa de ulike objektene
    int pasientFeil = 0, middelFeil = 0, legeFeil = 0, reseptFeil = 0;


    // konstruktoer gaar gjennom filen, oppretter objekter og legger de til i listen (kaster 2 unntak)
    public Legesystem(String fil) throws FileNotFoundException, IOException {

        // objekt som lager en tekstfil "ErrorLog.txt"
        PrintWriter writer = new PrintWriter("ErrorLog.txt");

        // scanner + fil objekt
        Scanner lesFil = new Scanner(new File(fil));

        // linjer-counter for antall linjer
        int linjer = 0;

        // om linjen har "# Pasienter" - lag pasient-objekter og legg til i liste
        if (lesFil.nextLine().startsWith("# Pasienter")) {

            // skriver typen i error-log og inkrementerer antall linjer (pga nextLine i if-sjekket tar opp en linje)
            writer.write("\n# Pasient-feil\n");
            linjer++;

            // mens filen har neste linje og ikke inneholder "#", fortsetter den
            while (!lesFil.hasNext("#") && lesFil.hasNextLine()) {
                linjer++;

                // forsoker aa lage variabler av de ulike ordene i hver linje
                try {
                    //deler opp linjen i biter og tildeler verdier til ulike indekser
                    String[] biter = lesFil.nextLine().split(",");
                    String navn = biter[0];
                    String fnr = biter[1];

                    // sjekker om navn eller foedselsnummer finnes - kaster unntak
                    for (Pasient x : pasienter) {
                        if (x.hentfNr().equals(fnr) || x.hentNavn().equals(navn)) {
                            throw new Exception("Navn/fNr ugyldig");
                        }
                    }
                    // oppretter objekt og legger til i lenkeliste
                    Pasient pasient = new Pasient(navn, fnr);
                    pasienter.leggTil(pasient);
                }
                // om det ikke fungerer, finn linjen og skriv feilmeldingen i en error-log
                catch (Exception e) {
                    String line = Files.readAllLines(Paths.get(fil)).get(linjer-1);
                    writer.format("%s %7s %-4d %s", timestamp, "Linje:", linjer,  line + "\n");
                    pasientFeil++;
                }
            }
        }


        // om linjen har "# Legemidler" - lag legemiddel-objekter og legg til i liste
        if (lesFil.nextLine().startsWith("# Legemidler")) {

            // skriver typen i error-log og inkrementerer antall linjer
            writer.write("\n# Legemiddel-feil\n");
            linjer++;

            // mens filen har neste linje og ikke inneholder "#", fortsetter den
            while (!lesFil.hasNext("#") && lesFil.hasNextLine()) {
                linjer++;

                // forsoker aa lage variabler av de ulike ordene i hver linje
                try {
                    //deler opp linjen i biter og tildeler verdier til ulike indekser
                    String[] biter = lesFil.nextLine().split(",");

                    String navn = biter[0];
                    String type = biter[1];
                    Double pris = Double.parseDouble(biter[2]);
                    Double virkestoff = Double.parseDouble(biter[3]);

                    // sjekker om listen "biter" har styrke (indeks 4)
                    if (biter.length > 4) {
                        int styrke = Integer.parseInt(biter[4]);

                        // sjekker om type er "Vanedannende"
                        if (type.equals("vanedannende")) {
                            Vanedannende vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
                            legemidler.leggTil(vanedannende);  // legger til legemiddel i lenkelisten
                        }

                        // sjekker om type er "narkotisk"
                        if (type.equals("narkotisk")) {
                            Narkotisk narkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
                            legemidler.leggTil(narkotisk);  // legger til legemiddel i lenkelisten
                        }
                    }

                    // om styrke ikke er definert, sjekker om typen er vanlig
                    if (type.equals("vanlig")) {
                        Vanlig vanlig = new Vanlig(navn, pris, virkestoff);
                        legemidler.leggTil(vanlig);  // legger til legemiddel i lenkelisten
                    }
                }
                // om det ikke fungerer, finn linjen og skriv feilmeldingen i en error-log
                catch (Exception e) {
                    String line = Files.readAllLines(Paths.get(fil)).get(linjer-1);
                    writer.format("%s %7s %-4d %s", timestamp, "Linje:", linjer,  line + "\n");
                    middelFeil++;
                }
            }
        }


        // om linjen har "# Leger" - lag lege-objekter og legg til
        if (lesFil.nextLine().startsWith("# Leger")) {

            // skriver typen i error-log og inkrementerer antall linjer
            writer.write("\n# Lege-feil\n");
            linjer++;


            // mens filen har neste linje og ikke inneholder "#", fortsetter den
            while (!lesFil.hasNext("#") && lesFil.hasNextLine()) {
                linjer++;

                // forsoker aa lage variabler av de ulike ordene i hver linje
                try {
                    //deler opp linjen i biter og tildeler verdier til ulike indekser
                    String[] biter = lesFil.nextLine().split(",");
                    String navn = biter[0];

                    // sjekker om navn finnes - kaster unntak
                    for (Lege x : leger) {
                        if (x.hentNavn().equals(navn)) {
                            throw new Exception("Navn ugyldig");
                        }
                    }
                    // om andre indeks er 0 - lag vanlige leger
                    if (biter[1].equals("0")) {
                        Lege lege = new Lege(navn);
                        leger.leggTil(lege);
                    }

                    // ellers lager den spesialist-objekter
                    else {
                        int kontrollID = Integer.parseInt(biter[1]);
                        Spesialist spesialist = new Spesialist(navn, kontrollID);
                        leger.leggTil(spesialist);
                    }
                }
                // om det ikke fungerer, finn linjen og skriv feilmeldingen i en error-log
                catch (Exception e) {
                    String line = Files.readAllLines(Paths.get(fil)).get(linjer-1);
                    writer.format("%s %7s %-4d %s", timestamp, "Linje:", linjer,  line + "\n");
                    legeFeil++;
                }
            }
        }


        // om linjen har "# Resepter" - lag Resept-objekter og legg til
        if (lesFil.nextLine().startsWith("# Resepter")) {

            // skriver typen i error-log og inkrementerer antall linjer
            writer.write("\n# Resept-feil\n");
            linjer++;


            while (!lesFil.hasNext("#") && lesFil.hasNextLine()) {
                linjer++;

                // forsoker aa lage variabler av de ulike ordene i hver linje
                try {
                    //deler opp linjen i biter og tildeler verdier til ulike indekser
                    String[] biter = lesFil.nextLine().split(",");
                    int legemiddelNummer = Integer.parseInt(biter[0]);
                    String legeNavn = biter[1];
                    int pasientID = Integer.parseInt(biter[2]);
                    String type = biter[3];

                    for (Legemiddel legemiddel : legemidler) {
                        if (legemiddel.hentId() == legemiddelNummer) {

                            for (Lege lege : leger) {
                                if (lege.hentNavn().equals(legeNavn)) {

                                    for (Pasient pasient : pasienter) {
                                        if (pasient.hentId() == pasientID) {

                                            // omt alt matcher > sjekk om filen har over fire indekser (IKKE lag PResept) (om den har reit)
                                            if (biter.length > 4) {
                                                int reit = Integer.parseInt(biter[4]);

                                                // om typen er "hvit" - lag hvit resept og legg til
                                                if (type.equals("hvit")) {
                                                    resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                                                }

                                                // om typen er "blaa" - lag blaa resept og legg til
                                                if (type.equals("blaa")) {
                                                    resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                                                }

                                                // om typen er "militaer" - lag militaer resept og legg til
                                                if (type.equals("militaer")) {
                                                    resepter.leggTil(lege.skrivMilitaerResept(legemiddel, pasient, reit));
                                                }
                                            }

                                            // ellers om den har 4 indekser: lag KUN PResept
                                            else if (type.equals("p")) {
                                                resepter.leggTil(lege.skrivPResept(legemiddel, pasient));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                // om det ikke fungerer, finn linjen og skriv feilmeldingen i en error-log
                catch (Exception e) {
                    String line = Files.readAllLines(Paths.get(fil)).get(linjer-1);
                    writer.format("%s %7s %-4d %s", timestamp, "Linje:", linjer,  line + "\n");
                    reseptFeil++;
                }
            }
        }

        // lukker writer-objektet som lager errorlog.txt
        writer.close();
    }



    // egen sleep-metode for "artifical-delay"
    public static void delay(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {}
    }
// <<<<<<< HEAD
    //CHECK TO SEE IF EXISTS FROM BEFORE FUNCTION
    public boolean isFound(String n, String lN) {
      String navn = navn.toLowerCase();
      String listeNavn = lN.toLowerCase();
      if (listeNavn == leger) {
        for (Lege x : leger) {
          if (navn == x.hentNavn()) return true;
        }
      } 
      else if (listeNavn == legemidler) {
        for (Legemiddel x : legemidler) {
          if (navn == x.hentNavn()) return true;
        }
      }
      else if (listeNavn == resepter) {
        for (Resept x : resepter) {
          if (navn == x.hentNavn()) return true;
        }
      }
      else if (listeNavn == pasienter) {
        for (Pasient x : pasienter) {
          if (navn == x.hentNavn()) return true;
        }
      }
      return false;   
    }
    // E3


    /* E3 */

    // skriver ut alle pasienter
    public void skrivPasienter() {
        System.out.println("-------- [ Pasienter ] -----------\n");
        for (Pasient x : pasienter) {

            System.out.println("- " + x);
            delay(25);
        }
        System.out.println("----------------------------------\n");
    }

    // skriver ut alle legemidler
    public void skrivLegemidler() {
        System.out.println("-------- [ Legemidler ] ----------\n");
        for (Legemiddel x : legemidler) {

            System.out.println("- " + x);
            delay(25);

        }
        System.out.println("----------------------------------");
    }

    // skriver ut alle leger
    public void skrivLeger() {
        System.out.println("----------- [ leger ] --------------\n");
        for (Lege x : leger) {

            System.out.println("- " + x);
            delay(25);
        }
        System.out.println("----------------------------------\n");
    }

    // skriver ut alle resepter
    public void skrivResepter() {
        System.out.println("------- [ Resepter ] ---------------\n");
        for (Resept x : resepter) {

            System.out.println("- " + x);
            delay(25);
        }
        delay(800);
        System.out.println("\n- - - - - - - - - - - - - - - - ");
        System.out.println("Pasient-feil: "+pasientFeil);
        System.out.println("Middel-feil: "+middelFeil);
        System.out.println("Lege-feil: "+legeFeil);
        System.out.println("Resept-feil: "+reseptFeil);
        System.out.println("\nFor aa se disse feilene, aapne filen 'errorLog.txt'");
        System.out.println("----------------------------------");
    }

    // TEST!!


    /* E4 */

    public void legeMeny() {
        // console-interface
        delay(500);
        System.out.println("\n ---[Oppretting]----");
        System.out.print( " | 1. Lege         |" +
                "\n | 2. Spesialist   |" +
                "\n | - - - - - - - - |" +
                "\n | a. Tilbake      |" +
                "\n -------------------\n");
    }
    public void leggTilLege() {

        // objekt for input
        Scanner scanner = new Scanner(System.in);

        legeMeny();
        System.out.print("Hva onsker du aa gjoere? \n > ");
        String legeKommando = scanner.nextLine().toLowerCase();


        // fortsetter til bruekren avbryter eller legger til lege
        while (!legeKommando.equals("a")) {

            // om bruker velger lege - spoer om navn, opprett og legg til
            if (legeKommando.equals("1")) {
                delay(500);
                System.out.print("\nNavn paa lege: ");
                String legeNavn = scanner.nextLine();

                //TODO: SJEKK OM EKSISTERER FOER LEGGES TIL!!

                delay(500);
                Lege lege = new Lege(legeNavn);
                leger.leggTil(lege);

                delay(750);
                System.out.println("\n" + legeNavn + " er lagt til\n");
                delay(1000);

                // gaar ut av loekke
                legeKommando = "a";
            }

            // ellers om bruker velger spesialis - spoer om navn + kontrollId, opprett og legg til
            else if (legeKommando.equals("2")) {
                delay(500);
                System.out.print("\nNavn paa spesialist: ");
                String spesialistNavn = scanner.nextLine();

                delay(500);
                System.out.print("KontrollID?: ");
                int kontrollID = scanner.nextInt();

                //TODO: SJEKK OM EKSISTERER FOER LEGGES TIL!!

                Spesialist spesialist = new Spesialist(spesialistNavn, kontrollID);
                leger.leggTil(spesialist);

                delay(750);
                System.out.println("\n" + spesialistNavn + " er lagt til\n");
                delay(1000);

                // gaar ut av loekke
                legeKommando = "a";
            }

            else {

                System.out.println("\nUgyldig input, proev igjen");

                delay(500);
                legeMeny();
                System.out.print("Hva onsker du aa gjoere? \n > ");
                legeKommando = scanner.nextLine().toLowerCase();
            }
        }

        delay(750);
        System.out.println("\n\nGaar tilbake...");
        delay(1000);
    }

    public void pasientMeny() {
        // console-interface
        delay(500);
        System.out.println("\n ---[Oppretting]---");
        System.out.print ( " | 1. Pasient     |" +
                "\n | - - - -  - - - |" +
                "\n | a. Tilbake     |" +
                "\n ------------------\n");
    }
    public void leggTilPasient() {

        // objekt for input
        Scanner scanner = new Scanner(System.in);

        pasientMeny();
        System.out.print("Hva onsker du aa gjoere? \n > ");
        String pasientKommando = scanner.nextLine().toLowerCase();


        // fortsetter til bruekren avbryter eller legger til pasient
        while (!pasientKommando.equals("a")) {

            // om bruker velger pasient - spoer om navn og fNr, opprett og legg til
            if (pasientKommando.equals("1")) {
                delay(500);
                System.out.print("\nNavn paa pasient: ");
                String pasientNavn = scanner.nextLine();

                delay(500);
                System.out.print("Foedselsnummer: ");
                String fNr = scanner.nextLine().toLowerCase();

                //TODO: SJEKK OM EKSISTERER FOER LEGGES TIL!!

                Pasient pasient = new Pasient(pasientNavn, fNr);
                pasienter.leggTil(pasient);

                delay(750);
                System.out.println("\n" + pasientNavn + " er lagt til\n");
                delay(1000);

                // gaar ut av loekke
                pasientKommando = "a";
            }

            else {

                System.out.println("\nUgyldig input, proev igjen");

                delay(500);
                pasientMeny();
                System.out.print("Hva onsker du aa gjoere? \n > ");
                pasientKommando = scanner.nextLine().toLowerCase();
            }
        }

        delay(750);
        System.out.println("\n\nGaar tilbake...");
        delay(1000);
    }

    public void reseptMeny() {
        // console-interface
        delay(500);
        System.out.println("\n -----[Resept]------");
        System.out.print( " | 1. Hvite        |" +
                "\n | 2. Millitaer    |" +
                "\n | 3. PResept      |" +
                "\n | 4. Blaa         |" +
                "\n | - - - - - - - - |" +
                "\n | a. Tilbake      |" +
                "\n -------------------\n");
    }
    //TODO: ikke ferdig!!
    public void leggTilResept() throws UlovligUtskrift {
        // objekt for input
        Scanner scanner = new Scanner(System.in);

        reseptMeny();
        System.out.print("Hva onsker du aa gjoere? \n > ");
        String reseptKommando = scanner.nextLine().toLowerCase();


        // fortsetter til bruekren avbryter eller legger til pasient
        while (!reseptKommando.equals("a")) {

            // alle unntatt PResept skal ha reit !
            // > 1. 2. 4. - reit
            // 2. Ikke reit

            if (reseptKommando.equals("1")) {

                System.out.println("\nResept ikke ferdig!!");
            }

            if (reseptKommando.equals("2")) {

                System.out.println("\nResept ikke ferdig!!");
            }

            if (reseptKommando.equals("3")) {

                System.out.println("\nResept ikke ferdig!!");
            }

            if (reseptKommando.equals("4")) {

                System.out.println("\nResept ikke ferdig!!");
            }

            else {
                System.out.println("\nUgyldig input, proev igjen");

                delay(500);
                reseptMeny();
                System.out.print("Hva onsker du aa gjoere? \n > ");
                reseptKommando = scanner.nextLine().toLowerCase();
            }
        }

        delay(750);
        System.out.println("\n\nGaar tilbake...");
        delay(1000);
        }

    public void middelMeny() {
    // console-interface
    delay(500);
    System.out.println("\n ---[Oppretting]----");
    System.out.print( " | 1. Vanlig       |" +
            "\n | 2. Vanedannende |" +
            "\n | 3. Narkotisk    |" +
            "\n | - - - - - - - - |" +
            "\n | a. Tilbake      |" +
            "\n -------------------\n");
    }
    public void leggTilLegemiddel() {

        // objekt for input
        Scanner scanner = new Scanner(System.in);

        middelMeny();
        System.out.print("Hva onsker du aa gjoere? \n > ");
        String middelKommando = scanner.nextLine().toLowerCase();


        // fortsetter til bruekren avbryter eller legger til legemiddel
        while (!middelKommando.equals("a")) {

            // om bruker velger vanlig - spoer om navn, pris og virkestoff
            if (middelKommando.equals("1")) {
                delay(500);
                System.out.print("\nNavn paa legemiddel: ");
                String middelNavn = scanner.nextLine().toLowerCase();

                delay(500);
                System.out.print("Pris: ");
                double pris = scanner.nextDouble();

                delay(500);
                System.out.print("Virkestoff: ");
                double virkestoff = scanner.nextDouble();

                //TODO: SJEKK OM EKSISTERER FOER LEGGES TIL!!

                Vanlig vanlig = new Vanlig(middelNavn, pris, virkestoff);
                legemidler.leggTil(vanlig);


                delay(500);
                System.out.println("\n" + middelNavn + " er lagt til\n");
                delay(1000);

                // gaar ut av loekke
                middelKommando = "a";
            }

            // ellers om bruker velger vanedannende - spoer om navn, pris, virkestoff og styrke
            else if (middelKommando.equals("2")) {
                delay(500);
                System.out.print("\nNavn paa legemiddel: ");
                String middelNavn = scanner.nextLine().toLowerCase();

                delay(500);
                System.out.print("Pris: ");
                double pris = scanner.nextDouble();

                delay(500);
                System.out.print("Virkestoff: ");
                double virkestoff = scanner.nextDouble();

                delay(500);
                System.out.print("Styrke: ");
                int styrke = scanner.nextInt();

                //TODO: SJEKK OM EKSISTERER FOER LEGGES TIL!!

                Vanedannende vanedannende = new Vanedannende(middelNavn, pris, virkestoff, styrke);
                legemidler.leggTil(vanedannende);


                delay(750);
                System.out.println("\n" + middelNavn + " er lagt til\n");
                delay(1000);

                // gaar ut av loekke
                middelKommando = "a";
            }

            // ellers om bruker velger narkotisk - spoer om navn, pris, virkestoff og styrke
            else if (middelKommando.equals("3")) {
                delay(500);
                System.out.print("\nNavn paa legemiddel: ");
                String middelNavn = scanner.nextLine().toLowerCase();

                delay(500);
                System.out.print("Pris: ");
                double pris = scanner.nextDouble();

                delay(500);
                System.out.print("Virkestoff: ");
                double virkestoff = scanner.nextDouble();

                delay(500);
                System.out.print("Styrke: ");
                int styrke = scanner.nextInt();

                //TODO: SJEKK OM EKSISTERER FOER LEGGES TIL!!

                Narkotisk narkotisk = new Narkotisk(middelNavn, pris, virkestoff, styrke);
                legemidler.leggTil(narkotisk);


                delay(750);
                System.out.println("\n" + middelNavn + " er lagt til\n");
                delay(1000);

                // gaar ut av loekke
                middelKommando = "a";
            }

            // ellers telles input som ugyldig, og maa prove igjen
            else {
                System.out.println("\nUgyldig input, proev igjen");

                delay(500);
                middelMeny();
                System.out.print("Hva onsker du aa gjoere? \n > ");
                middelKommando = scanner.nextLine().toLowerCase();
            }
        }

        delay(750);
        System.out.println("\n\nGaar tilbake...");
        delay(1000);
    }


    /* E5 */

    public void reseptBruk() {
        Scanner scanner = new Scanner(System.in);


        // viser fram alle pasienter og deres ID
        System.out.println();
        for (Pasient a : pasienter) {
            System.out.println(a);
            delay(25);
        }
        System.out.println("a: Avbryt");

        // forsoker aa kjoere koden
        try {
            // spoer om hvilken pasient de vil se - input her blir altsaa ID-en til pasienten
            System.out.print("\nHvilken pasient vil du se resepter for? \n > ");
            String kommando = scanner.next();

            // counter for antall reseter og pasientnavn for bruk senere
            int antResept = 0;
            String pasientNavn = " [ Ingen pasient ]";

            if (kommando.equals("a")) {
                System.out.println("\n\nOk avslutter\n\n");
                delay(1000);
            } else {

                // gaar gjennom alle pasienter og sjekker ID til hver for aa finne pasient fra input
                for (Pasient b : pasienter) {
                    int pasientID = b.hentId();

                    // sjekker "kommando" med pasientID etter konvertering til String
                    if (kommando.equals(Integer.toString(pasientID))) {

                        // pasienten er funnet, vises til terminalen
                        System.out.print("\nPasient valgt: ");
                        pasientNavn = b.hentNavn();
                        System.out.println(b.hentNavn());
                        delay(1000);


                        // gaar gjennom resept-listen for aa see hvilke resepter tilhorer pasienten
                        for (Resept resept : resepter) {
                            pasientID = resept.hentPasient().hentId();

                            // sjekker "kommando" med pasientID etter konvertering til String
                            if (kommando.equals(Integer.toString(pasientID))) {
                                antResept++;
                                System.out.println(resept);
                                delay(25);
                            }
                        }
                    }
                }

                // om det komer opp resepter
                if (antResept > 0) {
                    System.out.print("\nHvilken resept vil du bruke? \n > ");
                    int reseptInput = scanner.nextInt();
                    delay(800);

                    // gaar gjennom alle resepter
                    for (Resept resept : resepter) {
                        int reseptID = resept.hentId();

                        // hvis resept-id matcher brukerinput - bruk resept og fortell bruker
                        if (reseptID == reseptInput) {

                            // sjekker antall reit /om tomt eller ikke/
                            if (resept.hentReit() > 0) {
                                resept.bruk();
                                System.out.println("\nBrukte resept paa " + resept.hentLegemiddel().hentNavn() + ".  Antall gjenvaerende reit: " + resept.hentReit());
                                delay(750);
                            } else {
                                System.out.println("\nKunne ikke bruke resept paa " + resept.hentLegemiddel().hentNavn() + ": Ingen reit igjen\n");
                                delay(1000);
                            }
                        }
                    }
                }

                // ellers gi feilmelding
                else {
                    delay(800);
                    System.out.println(" > Det er ingen resepter registrert for " + pasientNavn);
                    delay(1500);
                }
            }
            delay(750);
            System.out.println("\nGaar tilbake ...\n");
            delay(1000);
        }
        // fanger feil om tegnet til scanner er ugyldig
        catch (InputMismatchException e) {
            delay(750);
            System.out.println("\n\nDette tegnet er ugyldig ");
            System.out.println("Gaar tilbake ... \n\n");
            delay(1000);
        }
    }

    /* E6 */

    public void hentVanedannende() {

        int antall = 0;

        System.out.println("\n\n---[ VANEDANNENDE ] ---------------");
        for (Resept x : resepter) {
            Legemiddel middel = x.hentLegemiddel();

            if (middel instanceof Vanedannende){
                System.out.println(x);
                antall++;
            }
        }
        System.out.println("------------------------------------");
        System.out.println("Antall vanedannende: " + antall);
        System.out.println("------------------------------------\n");
        delay(2000);
    }

    public void hentNarkotiske() {

        int antall = 0;

        System.out.println("\n\n---[ NARKOTISKE ] ------------------");
        for (Resept x : resepter) {
            Legemiddel middel = x.hentLegemiddel();

            if (middel instanceof Narkotisk){
                System.out.println(x);
                antall++;
            }
        }
        System.out.println("------------------------------------");
        System.out.println(" - Antall narkotiske: " + antall);
        System.out.println("------------------------------------\n");
        delay(2000);
    }

    public void muligMisbruk() {

    }
}