import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Legesystem {

    // lister som holder styr paa pasienter, legemidler, leger og resepter
    Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
    Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();

    // konstruktoer - initierer listene
    public Legesystem(String fil) throws FileNotFoundException {
        System.out.println();

        // scanner + fil objekt
        Scanner lesFil = new Scanner(new File(fil));


        // om linjen har "# Pasienter" - lag pasient-objekter og legg til i liste
        if (lesFil.nextLine().startsWith("# Pasienter")) {

            // mens filen har neste linje og ikke inneholder "#", fortsetter den
            while (!lesFil.hasNext("#") && lesFil.hasNextLine()) {
                String[] biter = lesFil.nextLine().split(",");
                String navn = biter[0];
                String fnr = biter[1];

                // oppretter objekt og legger til i lenkeliste
                Pasient pasient = new Pasient(navn, fnr);
                pasienter.leggTil(pasient);
            }
        }


        // om linjen har "# Legemidler" - lag legemiddel-objekter og legg til i liste
        if (lesFil.nextLine().startsWith("# Legemidler")) {

            // mens filen har neste linje og ikke inneholder "#", fortsetter den
            while (!lesFil.hasNext("#") && lesFil.hasNextLine()) {
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
        }


        // om linjen har "# Leger" - lag lege-objekter og legg til
        if (lesFil.nextLine().startsWith("# Leger")) {

            // mens filen har neste linje og ikke inneholder "#", fortsetter den
            while (!lesFil.hasNext("#") && lesFil.hasNextLine()) {
                String[] biter = lesFil.nextLine().split(",");
                String navn = biter[0];

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
        }


        // IKKE FERDIG! Problem med storEksempelFil.txt
        if (lesFil.nextLine().startsWith("# Resepter")) {

            while (!lesFil.hasNext("#") && lesFil.hasNextLine()) {
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

                                        // sjekker om filen har over fire indekser (IKKE lag PResept) (om den har reit)
                                        if (biter.length > 4) {
                                            int reit = Integer.parseInt(biter[4]);

                                            // om typen er "hvit" - lag hvit resept og legg til
                                            if (type.equals("hvit")) {
                                                HvitResept hvit = new HvitResept(a, b, c, reit);
                                                resepter.leggTil(hvit); }

                                            // om typen er "blaa" - lag blaa resept og legg til
                                            if (type.equals("blaa")) {
                                                BlaaResept blaa = new BlaaResept(a, b, c, reit);
                                                resepter.leggTil(blaa); }

                                            // om typen er "militaer" - lag militaer resept og legg til
                                            if (type.equals("militaer")) {
                                                MilitaerResept militaer = new MilitaerResept(a, b, c, reit);
                                                resepter.leggTil(militaer); }
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
        }
    }

    // skriver ut alle pasienter
    public void skrivPasienter() {
        System.out.println("-------- [ Pasienter ] -----------");
        for (Pasient x : pasienter) {
            System.out.println("\n- - - - - - - - - - - - - - - ");

            System.out.println(x.toString());

            System.out.println("- - - - - - - - - - - - - - - \n");
        }
        System.out.println("----------------------------------\n");
    }

    // skriver ut alle legemidler
    public void skrivLegemidler() {
        System.out.println("-------- [ Legemidler ] ----------");
        for (Legemiddel x : legemidler) {
            System.out.println("\n- - - - - - - - - - - - ");

            System.out.println(x.toString());

            System.out.println("- - - - - - - - - - - - \n");
        }
        System.out.println("----------------------------------\n");
    }

    // skriver ut alle leger
    public void skrivLeger() {
        System.out.println("----------- [ leger ] --------------");
        for (Lege x : leger) {
            System.out.println("\n- - - - - - - - - - - - - - - ");

            System.out.println(x.toString());

            System.out.println("- - - - - - - - - - - - - - - \n");
        }
        System.out.println("----------------------------------\n");
    }

    // skriver ut alle resepter
    public void skrivResepter() {
        System.out.println("---------- [ Resepter ] ------------");
        for (Resept x : resepter) {
            System.out.println("\n- - - - - - - - - - - - - - - - ");

            System.out.println(x.toString());

            System.out.println("- - - - - - - - - - - - - - - - \n");
        }
        System.out.println("----------------------------------\n");
    }
}

