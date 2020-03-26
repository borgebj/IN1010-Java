import java.util.Scanner;
import java.util.Date;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.lang.Math.*;

import java.nio.file.Files;
import java.nio.file.Paths;

// unntak
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.IOException;


public class Legesystem {

    // lister som holder styr paa pasienter, legemidler, leger og resepter
    Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
    Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();

    // timestamp-objekter
    Date date= new Date(System.currentTimeMillis() + 3600 * 1000);
    long time = date.getTime();
    Timestamp timestamp = new Timestamp(time);

    // variabler som holder antall feil paa de ulike objektene
    int pasientFeil = 0, middelFeil = 0, legeFeil = 0, reseptFeil = 0;

    // objekt som lager en tekstfil "ErrorLog.txt"
    PrintWriter writer;


    // konstruktoer initialiserer errorloggen
    public Legesystem() throws FileNotFoundException {
        writer = new PrintWriter("ErrorLog.txt");
    }

    // egen sleep-metode for "artifical-delay"
    public static void delay(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {}
    }

    // metode som gaar gjennom filen, oppretter objekter og legger de til i listen (kaster 2 unntak)
    public void lesFraFil(String fil) throws FileNotFoundException, IOException {

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
                    String fnrFoer = biter[1].trim();

                    // konverterer til long og tilbake til string for a sjekke om fNr er gyldig
                    long fnrEtter = Long.parseLong(fnrFoer);
                    String fnr = Long.toString(fnrEtter);

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
                    writer.format("%s %7s %-4d %-110s %s", timestamp, "Linje:", linjer,  line, e + "\n");
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
                    else if (type.equals("vanlig")) {
                        legemidler.leggTil(new Vanlig(navn, pris, virkestoff));
                    }
                }
                // om det ikke fungerer, finn linjen og skriv feilmeldingen i en error-log
                catch (Exception e) {
                    String line = Files.readAllLines(Paths.get(fil)).get(linjer-1);
                    writer.format("%s %7s %-4d %-110s %s", timestamp, "Linje:", linjer,  line, e + "\n");
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
                    writer.format("%s %7s %-4d %-110s %s", timestamp, "Linje:", linjer,  line, e + "\n");
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

                    if (godkjent) {
                        if (biter.length > 4) {
                            int reit = Integer.parseInt(biter[4]);
                            if (type.equals("blaa")) {
                                resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                            }
                            else if (type.equals("hvit")) {
                                resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                            }
                            else if (type.equals("militaer")) {
                                MilitaerResept militaer = lege.skrivMilitaerResept(legemiddel, pasient, reit);
                                resepter.leggTil(militaer);
                            }
                        } else {
                            if (type.equals("p")) {
                                resepter.leggTil(lege.skrivPResept(legemiddel, pasient));
                            }
                        }
                    }
                }
                // om det ikke fungerer, finn linjen og skriv feilmeldingen i en error-log
                catch (Exception e) {
                    String line = Files.readAllLines(Paths.get(fil)).get(linjer - 1);
                    writer.format("%s %7s %-4d %-45s %s", timestamp, "Linje:", linjer,  line, e + "\n");
                    reseptFeil++;
                }
            }
        }

        // starter programmet med aa printe feil
        System.out.println("---------------------------------------------------");
        System.out.println("Pasient-feil: "+pasientFeil);
        System.out.println("Middel-feil: "+middelFeil);
        System.out.println("Lege-feil: "+legeFeil);
        System.out.println("Resept-feil: "+reseptFeil);
        System.out.println("\nFor aa se disse feilene, aapne filen 'errorLog.txt'");
        System.out.println("---------------------------------------------------\n\n");

        // lukker writer-objektet som lager errorlog.txt
        writer.close();
    }



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
        System.out.println("------- [ Resepter ] ---------------------------------------------------\n");
        for (Resept x : resepter) {

            System.out.println("- " + x);
            delay(25);
        }
        delay(800);
        System.out.println("------------------------------------------------------------------------\n\n");
    }


    /* E4 */
    /** Console-interfacer **/
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

    public void pasientMeny() {
        // console-interface
        delay(500);
        System.out.println("\n ---[Oppretting]---");
        System.out.print ( " | 1. Pasient     |" +
                "\n | - - - -  - - - |" +
                "\n | a. Tilbake     |" +
                "\n ------------------\n");
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

    /** Deler av opprett**/
    // gir bruker mulighet for aa opprette leger
    public void leggTilLege() {
        Scanner scanner = new Scanner(System.in);

        legeMeny();
        System.out.print("Hva onsker du aa gjoere? \n > ");
        String legeKommando = scanner.nextLine().toLowerCase();


        try {
            // fortsetter til bruekren avbryter eller legger til lege
            while (!legeKommando.equals("a")) {

                // om bruker velger lege - spoer om navn, opprett og legg til
                if (legeKommando.equals("1")) {
                    delay(500);
                    System.out.print("\nNavn paa lege: ");
                    String legeNavn = scanner.nextLine().trim().toLowerCase();


                    // sjekker om navn finnes - kaster unntak
                    for (Lege x : leger) {
                        if (x.hentNavn().equals(legeNavn.trim().toLowerCase())) {
                            throw new Exception("Navn ugyldig [eksisterer fra foer av]");
                        }
                    }

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
                    String spesialistNavn = scanner.nextLine().trim().toLowerCase();

                    delay(500);
                    System.out.print("KontrollID?: ");
                    int kontrollID = scanner.nextInt();

                    // sjekker om navn finnes - kaster unntak
                    for (Lege x : leger) {
                        if (x.hentNavn().equals(spesialistNavn.trim().toLowerCase())) {
                            throw new Exception("Navn ugyldig [eksisterer fra foer av]");
                        }
                    }

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
        }
        catch (InputMismatchException e) {
            System.out.println("\nDette tegnet er ugyldig");
        }
        catch (Exception e) {
            System.out.println("\nLege finnes fra foer av\n");
        }

        delay(750);
        System.out.println("\n\nGaar tilbake...");
        delay(1000);
    }

    // gir bruker mulighet for aa opprette pasienter
    public void leggTilPasient() {
        Scanner scanner = new Scanner(System.in);

        // viser meny og spoer bruker om input
        pasientMeny();
        System.out.print("Hva onsker du aa gjoere? \n > ");
        String pasientKommando = scanner.nextLine().toLowerCase();

        try {
            // fortsetter til bruekren avbryter eller legger til pasient
            while (!pasientKommando.equals("a")) {

                // om bruker velger pasient - spoer om navn og fNr, opprett og legg til
                if (pasientKommando.equals("1")) {
                    delay(500);
                    System.out.print("\nNavn paa pasient: ");
                    String pasientNavn = scanner.nextLine();

                    delay(500);
                    System.out.print("Foedselsnummer: ");
                    String fnrFoer = scanner.nextLine().toLowerCase();

                    // konverterer til long og tilbake til string for a sjekke om fNr er gyldig
                    long fnrEtter = Long.parseLong(fnrFoer);
                    String fNr = Long.toString(fnrEtter);

                    // sjekker om navn eller foedselsnummer finnes - kaster unntak
                    for (Pasient x : pasienter) {
                        if (x.hentfNr().equals(fNr) || x.hentNavn().equals(pasientNavn)) {
                            throw new Exception("Navn/fNr ugyldig [eksisterer fra foer av]");
                        }
                    }

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
        }
        catch (NumberFormatException e) {
            System.out.println("\nFoedselsnummer er ikke gyldig");
        }
        catch (Exception e) {
            System.out.println("Pasient finnes fra foer av");
        }

        delay(750);
        System.out.println("\n\nGaar tilbake...");
        delay(1000);
    }

    // gir bruker mulighet for aa opprette resepter
    public void leggTilResept() throws UlovligUtskrift {
        Scanner scanner = new Scanner(System.in);

        reseptMeny();
        System.out.print("Hva onsker du aa gjoere? \n > ");
        String reseptKommando = scanner.nextLine().toLowerCase();


        try {
            // sjekker om input er gyldig i forhold til menyen
            if (reseptKommando.equals("1") || reseptKommando.equals("2") ||
                reseptKommando.equals("3") || reseptKommando.equals("4")) {

                // LINJE 564 TIL 630  (lag metode av dette, og bruk i "leggTilResept" og "lesFraFil"
                boolean middelMatch = false;
                boolean legeMatch = false;
                boolean pasientMatch = false;
                boolean godkjent = false;

                Legemiddel legemiddel = null;
                Lege lege = null;
                Pasient pasient = null;

                // LEGEMIDDEL-delen
                delay(500);
                System.out.print("\nHva er legemiddel-ID? \n > ");
                int legemiddelNummer = scanner.nextInt();
                scanner.nextLine();
                for (Legemiddel a : legemidler) {
                    if (legemiddelNummer == a.hentId()) {
                        middelMatch = true;
                        legemiddel = a;
                        break;
                    }
                }

                // LEGE-delen
                delay(500);
                System.out.print("Hva er lege navn? \n > ");
                String legeNavn = scanner.nextLine().trim().toLowerCase();
                for (Lege b : leger) {
                    if (legeNavn.equals(b.hentNavn().trim().toLowerCase())) {

                        // sjekker om lege er vanlig eller spesialist
                        if (b instanceof Spesialist) {
                            System.out.print("Hva er Kontroll-ID? \n > ");
                            int kontrollID = scanner.nextInt();
                            scanner.nextLine();

                            // vi vet at lege = spesialist, saa vi caster lege til spesialist for aa bruke "hentKontrollID()"
                            Spesialist spesialist = (Spesialist) b;

                            if (kontrollID == spesialist.hentKontrollID()) {
                                legeMatch = true;
                                lege = b;
                                break;
                            }
                        }
                        else if (b instanceof Lege) {
                                legeMatch = true;
                                lege = b;
                                break;
                        }
                    }
                }

                // PASIENT-delen
                delay(500);
                System.out.print("Hva er pasient ID? \n > ");
                int pasientID = scanner.nextInt();
                scanner.nextLine();
                for (Pasient c : pasienter) {
                    if (pasientID == c.hentId()) {
                        pasientMatch = true;
                        pasient = c;
                        break;
                    }
                }

                // SJEKKE-delen
                if (middelMatch == true && legeMatch == true && pasientMatch == true) {
                    godkjent = true;
                }

                // om godkjent, sjekk type
                if (godkjent) {

                    // om valg er 1 / 2 eller /4 > er det IKKE PResept - spoer om Reit
                    if (reseptKommando.equals("1") || reseptKommando.equals("2") || reseptKommando.equals("4")) {

                        delay(500);
                        System.out.print("Hva er reit? \n > ");
                        int reit = scanner.nextInt();
                        scanner.nextLine();

                        // oppretter>legger til hvite
                        if (reseptKommando.equals("1")) {
                            resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                        }

                        // oppretter>legger til militaer
                        else if (reseptKommando.equals("2")) {
                            resepter.leggTil(lege.skrivMilitaerResept(legemiddel, pasient, reit));
                        }

                        // oppretter>legger til blaa
                        else if (reseptKommando.equals("4")) {
                            resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                        }
                        reseptKommando = "a";
                    }
                    // oppretter>legger til presept
                    else if (reseptKommando.equals("3")) {
                        resepter.leggTil(lege.skrivPResept(legemiddel, pasient));

                        delay(750);
                        System.out.println("\nResepten er lagt til i systemet");
                    }

                    delay(750);
                    System.out.println("\nResepten er lagt til i systemet");
                }
                else {
                    System.out.println("\nUgyldig input, proev igjen\n");

                    delay(500);
                    reseptMeny();
                    System.out.print("Hva onsker du aa gjoere? \n > ");
                    reseptKommando = scanner.nextLine().toLowerCase();
                }
                }
            else { System.out.println("\nInput er ugyldig"); }
            }
        catch (InputMismatchException e) {
            System.out.println("\nDette tegnet er ugyldig");
        }
        catch (UlovligUtskrift e) {
            delay(750);
            System.out.println("\nDenne legen har ikke lov aa skrive ut narkotisk");
        }

        delay(750);
        System.out.println("\n\nGaar tilbake...");
        delay(1000);
    }

    // gir bruker mulighet for aa opprette legemiddel
    public void leggTilLegemiddel() {
        Scanner scanner = new Scanner(System.in);

        // viser meny og spoer bruker om input
        middelMeny();
        System.out.print("Hva onsker du aa gjoere? \n > ");
        String middelKommando = scanner.nextLine().toLowerCase();

        try {

            // sjekker om input er gyldig i forhold til menyen
            if (middelKommando.equals("1") || middelKommando.equals("2") || middelKommando.equals("3")){

                while (!middelKommando.equals("a")) {

                    System.out.print("\nNavn paa legemiddel: ");
                    String middelNavn = scanner.nextLine().toLowerCase();

                    delay(500);
                    System.out.print("Pris: ");
                    double pris = scanner.nextDouble();
                    scanner.nextLine();

                    delay(500);
                    System.out.print("Virkestoff: ");
                    double virkestoff = scanner.nextDouble();
                    scanner.nextLine();

                    // om det enten er vanedannende eller narkotisk
                    if (middelKommando.equals("2") || middelKommando.equals("3")) {

                        // spoer om styrke i tillegg
                        delay(500);
                        System.out.print("Styrke: ");
                        int styrke = scanner.nextInt();

                        // 2 = vanedannende - opprett og legg til
                        if (middelKommando.equals("2")) {
                            legemidler.leggTil(new Vanedannende(middelNavn, pris, virkestoff, styrke));
                        }

                        // 3 = narkotisk - opprett og legg til
                        else if (middelKommando.equals("3")) {
                            legemidler.leggTil(new Narkotisk(middelNavn, pris, virkestoff, styrke));
                        }
                    }

                    // 1 = vanlig - opprett og legg till
                    else if (middelKommando.equals("1")) {
                        legemidler.leggTil(new Vanlig(middelNavn, pris, virkestoff));
                    }

                    delay(500);
                    System.out.println("\n" + middelNavn + " er lagt til\n");
                    delay(1000);

                    // gaar ut av loekke
                    middelKommando = "a";
                }
            }
            else { System.out.println("\nInput er ugyldig"); }
        } catch (InputMismatchException e) {
            System.out.println("\nDette tegnet er ugyldig");
        }

        delay(750);
        System.out.println("\n\nGaar tilbake...");
        delay(1000);
    }


    /* E5 */
    // gir brukeren mulighet for aa bruke resepter til ulike pasienter
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

            while (!kommando.equals("a")) {

                // counter for antall reseter og pasientnavn for bruk senere
                int antResept = 0;
                String pasientNavn = " [ Ugyldig pasient-id ] ";

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

                    // ellers - skriv feilemdling avhengig av "pasientNavn" og "kommando"
                    else {
                        // hvis pasientNavn-placeholderen er endret, skriv ut:
                        if (!pasientNavn.equals(" [ Ugyldig pasient-id ] ")) {
                            delay(800);
                            System.out.println(" > Det er ingen resepter registrert for " + pasientNavn);
                            delay(1500);
                        }
                        else {
                            // hvis brukeren har skrevet "alle" skal ikke dette komme opp
                            if (!kommando.equals("alle")) {
                                delay(800);
                                System.out.println("\n"+pasientNavn);
                                delay(1500);
                            }
                        }
                    }
                }

                System.out.print("\nHvilken pasient vil du se resepter for? (alle/ID/a) \n > ");
                kommando = scanner.next();

                // bruker kan velge aa see alle paa nytt
                if (kommando.equals("alle")) {
                    System.out.println();
                    for (Pasient a : pasienter) {
                        System.out.println(a);
                        delay(25);
                    }
                    System.out.println("a: Avbryt");
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
    // printer ut alle vanedannende legemidler + antall
    public void hentVanedannende() {

        int antall = 0;

        System.out.println("\n\n---[ VANEDANNENDE ] ---------------");
        for (Resept x : resepter) {
            Legemiddel middel = x.hentLegemiddel();

            if (middel instanceof Vanedannende){
                System.out.println(x);
                delay(25);
                antall++;
            }
        }
        System.out.println("------------------------------------");
        System.out.println("Antall vanedannende: " + antall);
        System.out.println("------------------------------------\n");
        delay(2000);
    }

    // printer ut alle narkotiske legemidler + antall
    public void hentNarkotiske() {

        int antall = 0;

        System.out.println("\n\n---[ NARKOTISKE ] ------------------");
        for (Resept x : resepter) {
            Legemiddel middel = x.hentLegemiddel();

            if (middel instanceof Narkotisk){
                System.out.println(x);
                delay(25);
                antall++;
            }
        }
        System.out.println("------------------------------------");
        System.out.println(" - Antall narkotiske: " + antall);
        System.out.println("------------------------------------\n");
        delay(2000);
    }

    // printer ut alle leger som har skrevet ut minst en narkotisk resept + antall narkotiske
    public void muligMisbruk() {

    System.out.println("\n\n---[ LEGER MED NARKOTISKE ] ---");
        System.out.println("-------------------------------------------------------------------\n");

    // (gaar gjennom hver lege og henter resept-liste
    for (Lege lege : leger) {

        int antNarkotiske = 0;
        delay(25);
        /** //TODO: HEr kommer "Unsafe operation" **/
        Lenkeliste<Resept> liste = lege.hentResepter();

        // gaar gjennom hver resept i listen og henter legemiddel
        for (Resept resept : liste) {
            Legemiddel reseptMiddel = resept.hentLegemiddel();

            // sjekker om legemiddelet er narkotisk
            if (reseptMiddel instanceof Narkotisk) {
                System.out.println(resept);
                delay(25);
                antNarkotiske++;
            }
        }
        if (antNarkotiske > 0){
            System.out.println("-------------------------------------------------------------------");
            System.out.println(" { " +lege+ " } ");
            System.out.println(" - Antall narkotiske: { " + antNarkotiske + " } - ");
            System.out.println("-------------------------------------------------------------------\n\n");
        }
    }

    // venter i 5 sekunder for aa gi bruker tid til aa see
    delay(5000);
    }


    /* E8 */
    public void lagFil() throws IOException {
        PrintWriter writer = new PrintWriter("legesystem.txt");

        // henter verdier fra hver pasient, og skriver disse i fil
        writer.write("# Pasienter (navn, fnr)");
        for (Pasient pasient : pasienter) {
            String pasientNavn = pasient.hentNavn();
            String pasientID = pasient.hentfNr();

            writer.format("\n%s,%s" , pasientNavn, pasientID);
        }

        // henter verdier fra hvert legemiddel, og skriver disse i fil
        writer.write("\n# Legemidler (navn,type,pris,virkestoff,[styrke])");
        for (Legemiddel legemiddel : legemidler) {
            String navn = legemiddel.hentNavn();
            String type = "ukjent type";
            double pris = legemiddel.hentPris();
            double virkestoff = legemiddel.hentVirkestoff();

            if (legemiddel instanceof Vanlig) {
                type = "vanlig";
                writer.format("\n%s,%s,%.2f,%.2f" , navn, type, pris, virkestoff);
            }
            else if (legemiddel instanceof Narkotisk) {
                type = "narkotisk";
                Narkotisk midddel = (Narkotisk) legemiddel;         // maa kaste for aa bruke metode
                int styrke = midddel.hentNarkotiskStyrke();
                writer.format("\n%s,%s,%.2f,%.2f,%d" , navn, type, pris, virkestoff, styrke);
            }
            else if (legemiddel instanceof Vanedannende) {
                type = "vanedannende";
                Vanedannende middel = (Vanedannende) legemiddel;    // maa kaste for aa bruke metode
                int styrke = middel.hentVanedannendeStyrke();
                writer.format("\n%s,%s,%.2f,%.2f,%d" , navn, type, pris, virkestoff, styrke);
            }
        }

        // henter verdier fra hver lege, og skriver disse i fil
        writer.write("\n# Leger (navn,kontrollid / 0 hvis vanlig lege)");
        for (Lege lege : leger) {
            String navn = lege.hentNavn();
            if (lege instanceof Spesialist) {
                Spesialist spesialist = (Spesialist) lege;
                int kontrollID = spesialist.hentKontrollID();
                writer.format("\n%s,%d", navn, kontrollID);
            }
            else if (lege instanceof Lege) {
                String vanligLege = "0";
                writer.format("\n%s,%s", navn, vanligLege);
            }
        }

        // henter verdier fra hver resept, og skriver disse i fil
        //TODO: MANGLER RESEPT!!

        delay(1500);
        System.out.println("\n\n---------------------------------------------");
        System.out.println("Ferdig - fil kalt for 'legesystem.txt'");
        System.out.println("---------------------------------------------\n");
        delay(1500);


        writer.close();
    }
}