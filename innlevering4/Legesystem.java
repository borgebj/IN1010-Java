import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


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


        /**
         * TODO: Remake denne X_X - Les oppgavetekst - FUCK
         * programmet registrerer ikke feil som skjer naar man forsoker aa opprette resepter!
         **/

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
                    int middelId = Integer.parseInt(biter[0]);
                    String navn = biter[1];
                    int id = Integer.parseInt(biter[2]);
                    String type = biter[3];

                    // for hvert legemiddel:
                    for (Legemiddel a : legemidler) {
                        int legemiddelId = a.hentId();

                        // hvis legemiddel matcher id fra fil:
                        if (middelId == legemiddelId) {

                            // for hver lege:
                            for (Lege b : leger) {
                                String legeNavn = b.hentNavn(); // finner navn

                                // sjekker om navn fra fil matcher navn i lenkelisten
                                if (legeNavn.equals(navn)) {

                                    // for hver pasient:
                                    for (Pasient c : pasienter) {
                                        int pasientId = c.hentId(); // finner ID

                                        // sjekker om id fra fil matcher id fra fil
                                        if (id == pasientId) {

                                            // omt alt matcher > sjekk om filen har over fire indekser (IKKE lag PResept) (om den har reit)
                                            if (biter.length > 4) {
                                                int reit = Integer.parseInt(biter[4]);

                                                // om typen er "hvit" - lag hvit resept og legg til
                                                if (type.equals("hvit")) {
                                                    HvitResept hvit = new HvitResept(a, b, c, reit);
                                                    resepter.leggTil(hvit);
                                                }

                                                // om typen er "blaa" - lag blaa resept og legg til
                                                if (type.equals("blaa")) {
                                                    BlaaResept blaa = new BlaaResept(a, b, c, reit);
                                                    resepter.leggTil(blaa);
                                                }

                                                // om typen er "militaer" - lag militaer resept og legg til
                                                if (type.equals("militaer")) {
                                                    MilitaerResept militaer = new MilitaerResept(a, b, c, reit);
                                                    resepter.leggTil(militaer);
                                                }
                                            }

                                            // ellers om den har 4 indekser: lag KUN PResept
                                            else if (type.equals("p")) {
                                                PResept p = new PResept(a, b, c);
                                                resepter.leggTil(p);
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

    // (E3 ?)

    // skriver ut alle pasienter
    public void skrivPasienter() {
        System.out.println("-------- [ Pasienter ] -----------\n");
        for (Pasient x : pasienter) {
            System.out.println(x + "\n");
        }
        System.out.println("----------------------------------\n");
    }

    // skriver ut alle legemidler
    public void skrivLegemidler() {
        System.out.println("-------- [ Legemidler ] ----------\n");
        for (Legemiddel x : legemidler) {
            System.out.println("- - - - - - - - - - - - ");

            System.out.println(x);

            System.out.println("- - - - - - - - - - - - \n");
        }
        System.out.println("----------------------------------");
    }

    // skriver ut alle leger
    public void skrivLeger() {
        System.out.println("----------- [ leger ] --------------\n");
        for (Lege x : leger) {
            System.out.println(x + "\n");
        }
        System.out.println("----------------------------------\n");
    }

    // skriver ut alle resepter
    public void skrivResepter() {
        System.out.println("------- [ Resepter ] ---------------\n");
        for (Resept x : resepter) {
            System.out.println("- - - - - - - - - - - - - - - - ");

            System.out.println(x);

            System.out.println("- - - - - - - - - - - - - - - - \n");
        }
        System.out.println("Pasient-feil: "+pasientFeil);
        System.out.println("Middel-feil: "+middelFeil);
        System.out.println("Lege-feil: "+legeFeil);
        System.out.println("Resept-feil: "+reseptFeil);
        System.out.println("\nFor aa se disse feilene, aapne filen 'error.txt'");
        System.out.println("\n----------------------------------");
    }
}