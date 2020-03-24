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



    // TODO: Kan forbredes mes "isValid" metoden???
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
                    String navn = biter[0].trim();
                    String fnr = biter[1].trim();

                    //TODO: Sjekk om foedselsnummer er gyldig ved aa konvertere til int og tilbake (gaar den ikke er det feil)

                    // sjekker om navn eller foedselsnummer finnes - kaster unntak
                    for (Pasient x : pasienter) {
                        if (x.hentfNr().equals(fnr) || x.hentNavn().equals(navn)) {
                            throw new Exception("Navn/fNr ugyldig [eksisterer fra foer av]");
                        }
                    }

                    // legg til pasient-objekt i lenkelisten "pasienter"
                    pasienter.leggTil(new Pasient(navn, fnr));

                }
                // om det ikke fungerer, finn linjen og skriv feilmeldingen i en error-log
                catch (Exception e) {
                    String line = Files.readAllLines(Paths.get(fil)).get(linjer-1);
                    writer.format("%s %7s %-4d %-30s %s", timestamp, "Linje:", linjer,  line, e + "\n");
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

                    String navn = biter[0].trim();
                    String type = biter[1].trim();
                    Double pris = Double.parseDouble(biter[2]);
                    Double virkestoff = Double.parseDouble(biter[3]);

                    // sjekker om listen "biter" har styrke (indeks 4)
                    if (biter.length > 4) {
                        int styrke = Integer.parseInt(biter[4]);

                        // sjekker om type er "Vanedannende" og legger til
                        if (type.equals("vanedannende")) {
                            legemidler.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));
                        }

                        // sjekker om type er "narkotisk" og legger til
                        if (type.equals("narkotisk")) {
                            legemidler.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));
                        }
                    }

                    // om styrke ikke er definert, sjekker om typen er vanlig og legger til
                    if (type.equals("vanlig")) {
                        legemidler.leggTil(new Vanlig(navn, pris, virkestoff));
                    }
                }
                // om det ikke fungerer, finn linjen og skriv feilmeldingen i en error-log
                catch (Exception e) {
                    String line = Files.readAllLines(Paths.get(fil)).get(linjer-1);
                    writer.format("%s %7s %-4d %-30s %s", timestamp, "Linje:", linjer,  line, e + "\n");
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
                    String fnr = biter[1];

                    // sjekker om navn finnes - kaster unntak
                    for (Lege x : leger) {
                        if (x.hentNavn().equals(navn)) {
                            throw new Exception("Navn ugyldig [eksisterer fra foer av]");
                        }
                    }
                    // om andre indeks er 0 - lag vanlige leger og legg till ( .trim() fjerner whitespace )
                    if (biter[1].trim().equals("0")) {
                        leger.leggTil(new Lege(navn));
                    }

                    // ellers lager den spesialist-objekter og legger till
                    else {
                        int kontrollID = Integer.parseInt(biter[1]);
                        leger.leggTil(new Spesialist(navn, kontrollID));
                    }
                }
                // om det ikke fungerer, finn linjen og skriv feilmeldingen i en error-log
                catch (Exception e) {
                    String line = Files.readAllLines(Paths.get(fil)).get(linjer-1);
                    writer.format("%s %7s %-4d %-30s %s", timestamp, "Linje:", linjer,  line, e + "\n");
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
                    String type = biter[3].trim();

                    boolean middelMatch = false;
                    boolean legeMatch = false;
                    boolean pasientMatch = false;
                    boolean godkjent = false;

                    Legemiddel legemiddel = null;
                    Lege lege = null;
                    Pasient pasient = null;

                    // sjekker om middelId er gyldig
                    for (Legemiddel a : legemidler) {
                        if (a.hentId() == legemiddelNummer) {
                            middelMatch = true;
                            legemiddel = a;
                            break;
                        }
                    }

                    // sjekker om legenavnet er gyldig
                    for (Lege b : leger) {
                        if (b.hentNavn().equals(legeNavn)) {
                            legeMatch = true;
                            lege = b;
                            break;
                        }
                    }

                    // sjekker om pasientID er gyldig
                    for (Pasient c : pasienter) {
                        if (c.hentId() == pasientID) {
                            pasientMatch = true;
                            pasient = c;
                            break;
                        }
                    }

                    // sjekker om resepten er godkjent
                    if (middelMatch==true && legeMatch==true && pasientMatch==true) { godkjent = true; }

                    // sjekker om vanlig lege proever aa opprette narkotisk resept
                    if (lege instanceof Lege && legemiddel instanceof Narkotisk) {
                        throw new UlovligUtskrift(lege, legemiddel, pasient.hentId());
                    }

                    if (biter.length > 4) {
                        int reit = Integer.parseInt(biter[4]);

                        if (type.equals("blaa")) {
                            resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                        }
                        else if (type.equals("hvit")) {
                            resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                        }
                        else if (type.equals("militaer")) {
                            resepter.leggTil(lege.skrivMilitaerResept(legemiddel, pasient, reit));
                        }
                    }
                    else {
                        if (type.equals("p")) {
                            resepter.leggTil(lege.skrivPResept(legemiddel, pasient));
                        }
                    }

                }
                // om det ikke fungerer, finn linjen og skriv feilmeldingen i en error-log
                catch (Exception e) {
                    String line = Files.readAllLines(Paths.get(fil)).get(linjer - 1);
                    writer.format("%s %7s %-4d %-30s %s", timestamp, "Linje:", linjer,  line, e + "\n");
                    reseptFeil++;
                }
            }
        }


            /*
            while (!lesFil.hasNext("#") && lesFil.hasNextLine()) {
                linjer++;

                //deler opp linjen i biter og tildeler verdier til ulike indekser
                String[] biter = lesFil.nextLine().split(",");

                // forsoker aa lage variabler av de ulike ordene i hver linje
                try {

                    int legemiddelNummer = Integer.parseInt(biter[0]);
                    String legeNavn = biter[1];
                    int pasientID = Integer.parseInt(biter[2]);
                    String type = biter[3];

                    boolean x = false;
                    boolean y = false;
                    boolean z = false;
                    boolean godkjent = false;

                    Legemiddel legemiddel = null;
                    Lege lege = null;
                    Pasient pasient = null;

                    for (Legemiddel a : legemidler) {
                        if (legemiddelNummer == a.hentId()) {
                            x = true;
                            legemiddel = a;
                        }
                    }
                    for (Lege b : leger){
                        if (legeNavn.equals(b.hentNavn())) {
                            y = true;
                            lege = b;
                        }
                    }
                    for (Pasient c : pasienter) {
                        if (pasientID == c.hentId()) {
                            z = true;
                            pasient = c;
                        }
                    }
                    if (x==true && y==true && z==true) {
                        godkjent = true;
                    }


                    if (godkjent) {
                        if (type.equals("blaa")) {
                            int reit = Integer.parseInt(biter[4]);
                            resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                        }
                        else if (type.equals("hvit")) {
                            int reit = Integer.parseInt(biter[4]);
                            resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                        }
                        else if (type.equals("militaer")) {
                            int reit = Integer.parseInt(biter[4]);
                            resepter.leggTil(lege.skrivMilitaerResept(legemiddel, pasient, reit));
                        }
                        else if (type.equals("p")) {
                            resepter.leggTil(lege.skrivPResept(legemiddel, pasient));
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
            */


        // lukker writer-objektet som lager errorlog.txt
        writer.close();
    }

    
    // egen sleep-metode for "artifical-delay"
    public static void delay(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {}
    }


    /*
    //CHECK TO SEE IF EXISTS FROM BEFORE FUNCTION - (or in resept if it uses valid components)
    public boolean isValid(T obj) {

        if (obj instanceof Lege) { //if we are checking a lege
            for (Lege x : leger) {
                if (x.hentNavn().toLowerCase() == obj.hentNavn().toLowerCase()) return false;
            }
        }
        else if (obj instanceof Legemiddel) { //if we are checking a legemiddel
            for (Legemiddel x : legemidler) {
                if (x.hentNavn().toLowerCase() == obj.hentNavn().toLowerCase()) return false;
            }
        }
        else if (obj instanceof Pasient) { //if we are checking a pasient
            for (Pasient x : pasienter) {
                if (x.hentNavn().toLowerCase() == obj.hentNavn().toLowerCase()) return false;
            }
        }
        else if (obj instanceof Resept) { //if we are checking a resept
            String legeNavn = obj.hentLege().navn.toLowerCase();
            String pasientNavn = obj.hentPasient().navn.toLowerCase();
            String legemiddelNavn = obj.hentLegemiddel().navn.toLowerCase();
            boolean found = false;
            boolean gyldigOverall = true;
            for (Lege a : leger) { //loop through alle leger - se om vi kan finne a match
                if (a.navn.toLowerCase() == legeNavn) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                gyldigOverall = false;
                System.out.println("Ugyldig Lege");
            }
            found = false;
            for (Pasient b : pasienter) { //loop through alle pasienter - se om vi kan finne a match
                if (b.navn.toLowerCase() == pasientNavn) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                gyldigOverall = false;
                System.out.println("Ugyldig Pasient");
            }
            found = false;
            for (Legemiddel c : legemidler) { //loop through alle pasienter - se om vi kan finne a match
                if (c.navn.toLowerCase() == legemiddelNavn) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                gyldigOverall = false;
                System.out.println("Ugyldig Legemiddel");
            }
            if (!gyldigOverall) return false; //what to output for resept
            else return true;
        }
        return true;
    }
     */



    /* E3 */

    // skriver ut alle pasienter
    public void skrivPasienter() {
        System.out.println("-------- [ Pasienter ] -----------");
        for (Pasient x : pasienter) {

            System.out.println("- " + x);
            delay(25);
        }
        System.out.println("----------------------------------\n");
    }

    // skriver ut alle legemidler
    public void skrivLegemidler() {
        System.out.println("-------- [ Legemidler ] ----------");
        for (Legemiddel x : legemidler) {

            System.out.println("- " + x);
            delay(25);

        }
        System.out.println("----------------------------------");
    }

    // skriver ut alle leger
    public void skrivLeger() {
        System.out.println("----------- [ leger ] --------------");
        for (Lege x : leger) {

            System.out.println("- " + x);
            delay(25);
        }
        System.out.println("----------------------------------\n");
    }

    // skriver ut alle resepter
    public void skrivResepter() {
        System.out.println("------- [ Resepter ] ---------------");
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
            int legemiddelNummer = 0; 
            String legeNavn;
            int pasientID = 0;
            int reit = 0;
            boolean middelMatch = false;
            boolean legeMatch = false;
            boolean pasientMatch = false;

            if (reseptKommando.equals("1") || reseptKommando.equals("2") || reseptKommando.equals("4")) {
              //Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit
              System.out.println("TEST");
              System.out.print("Hva er legemiddel ID? \n > ");
              legemiddelNummer = Integer.parseInt(scanner.nextLine());

              System.out.print("Hva er lege navn? \n > ");                
              legeNavn = scanner.nextLine().toLowerCase();

              System.out.print("Hva er pasient ID? \n > ");                
              pasientID = Integer.parseInt(scanner.nextLine());

              System.out.print("Hva er reit? \n > ");                
              reit = Integer.parseInt(scanner.nextLine());
              Legemiddel minMiddel = null;
              Pasient minPasient = null;
              
              for (Legemiddel a : legemidler) {
                if (a.hentId() == legemiddelNummer) {
                    middelMatch = true;
                    minMiddel = a;
                    break;
                }
              }

              // sjekker om pasientID er gyldig
              for (Pasient c : pasienter) {
                if (c.hentId() == pasientID) {
                    pasientMatch = true;
                    minPasient = c;
                    break;
                }
              }
    //1 Hvite
    //2 Militar
    //3 PR
    //4 Blaa
              for (Lege lege : leger) {
                if (legeNavn.equals(lege.hentNavn())) {
                    legeMatch = true;
                    //create the appropriate resept
                    if (reseptKommando == "1") lege.skrivHvitResept(minMiddel,minPasient,reit); 
                    else if (reseptKommando == "2") lege.skrivMilitaerResept(minMiddel,minPasient,reit);
                    else if (reseptKommando == "4") lege.skrivBlaaResept(minMiddel,minPasient,reit);
                    
                    System.out.println("COMPLETED CHOICE " + reseptKommando);
                }
              }
              System.out.println("Middel: "+ middelMatch+ "Pasient: "+ pasientMatch+ "Lege: "+legeMatch);
              reseptKommando = "a";
            }

            else if (reseptKommando.equals("3")) { //////////////////////////////////////////////////////////////////
              System.out.print("Hva er legemiddel ID? \n > ");
              legemiddelNummer = Integer.parseInt(scanner.nextLine());

              System.out.print("Hva er lege navn? \n > ");                
              legeNavn = scanner.nextLine().toLowerCase();

              System.out.print("Hva er pasient ID? \n > ");                
              pasientID = Integer.parseInt(scanner.nextLine());

              Legemiddel minMiddel = null;
              Pasient minPasient = null;
              
              for (Legemiddel a : legemidler) {
                if (a.hentId() == legemiddelNummer) {
                    middelMatch = true;
                    minMiddel = a;
                    break;
                }
              }

              // sjekker om pasientID er gyldig
              for (Pasient c : pasienter) {
                if (c.hentId() == pasientID) {
                    pasientMatch = true;
                    minPasient = c;
                    break;
                }
              }
              for (Lege lege : leger) {
                if (legeNavn.equals(lege.hentNavn())) {
                    legeMatch = true;
                    lege.skrivPResept(minMiddel,minPasient);
                    System.out.println("COMPLETED CHOICE " + reseptKommando);
                }
              }
              System.out.println("Middel: "+ middelMatch+ "Pasient: "+ pasientMatch+ "Lege: "+legeMatch);
              reseptKommando = "a";
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
        for (Lege x : leger) {
            System.out.println("\n"+x);

            Lenkeliste liste = x.hentResepter();

        }
        delay(5000);
    }
}