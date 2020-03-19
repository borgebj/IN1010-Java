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

                            // hvis legenavnet matcher navn fra fil:
                            if (legeNavn.equals(navn)) {

                                // for hver pasient:
                                for (Pasient c : pasienter) {
                                    int pasientId = c.hentId(); // finner ID

                                    // om pasient-id matcher id fra fil:
                                    if (id == pasientId) { // sjekker om pasient-id finnes

                                        // om filen har over fire indekser (IKKE lag PResept)
                                        if (biter.length == 5) { // sjekker om filen har reit
                                            int reit = Integer.parseInt(biter[4]);  // sjekk om index 4 finnes !

                                            if (type.equals("hvit")) {
                                                HvitResept hvit = new HvitResept(a, b, c, reit);
                                                resepter.leggTil(hvit);
                                            }

                                            if (type.equals("blaa")) {
                                                BlaaResept blaa = new BlaaResept(a, b, c, reit);
                                                resepter.leggTil(blaa);
                                            }

                                            if (type.equals("millitaer")) { // endre til en L !
                                                MillitaerResept millitaer = new MillitaerResept(a, b, c, reit);
                                                resepter.leggTil(millitaer);
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
        }
    }

    // egne metoder
    public void skrivPasienter() {
        for (Pasient x : pasienter) {
            System.out.println(x.toString() + "\n");
        }
    }

    public void skrivLegemidler() {
        for (Legemiddel x : legemidler) {
            System.out.println(x.toString() + "\n");
        }
    }

    public void skrivLeger() {
        for (Lege x : leger) {
            System.out.println(x.toString() + "\n");
        }
    }

    public void skrivResepter() {
        for (Resept x : resepter) {
            System.out.println(x.toString() + "\n");
        }
    }
}

