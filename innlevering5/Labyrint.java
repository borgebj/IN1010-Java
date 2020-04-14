import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// "Vi bruker koordinater på formen (kolonne, rad)"

public class Labyrint {

    private Rute [][] rutenett;
    int antRader;
    int antKol;
    Liste<String> utveier;

    // tar inn antall rader, antall kolonner, og et array av Ruter
    private Labyrint(int antRader, int antKol, Rute[][] rutenett) {
        this.antRader = antRader;
        this.antKol = antKol;
        this.rutenett = rutenett;
    }

    // metode som leser fra fil og initierer labyrintens-konstruktor
    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner lesFil = new Scanner(fil);

        // henter de 2 foerste tallene og lagrer de som "rader" og "kolonner"
        String [] tall = lesFil.nextLine().trim().split(" ");
        int rader = Integer.parseInt(tall[0]);
        int kolonner = Integer.parseInt(tall[1]);

        Rute[][] brett = new Rute[rader][kolonner];

        // leser gjennom selve labyrinten, tegn for tegn
        while (lesFil.hasNext()) {

            // oppretter ruter
            for (int i = 0; i < brett.length; i++) {
                String[] line = lesFil.nextLine().split("");

                for (int j = 0; j < brett[i].length; j++) {

                    // hvis tegn er "." - sjekk om den er ved kant foer oppretter rute
                    if (line[j].equals(".")) {

                        // sjekker kantene (hvis i/j er 0 eller i/j er lengden paa radene og kolonnene
                        if (j == 0 || i == 0 || j == kolonner - 1 || i == rader - 1) {
                            brett[i][j] = new Aapning(i, j);
                        } else {
                            brett[i][j] = new HvitRute(i, j);
                        }
                    }
                    else if (line[j].equals("#")) {
                        brett[i][j] = new SortRute(i, j);
                    }
                }
            }
        }

        // oppretter labyrint-objektet
        Labyrint labyrint = new Labyrint(rader, kolonner, brett);
        Rute[][] rutenett = labyrint.rutenett;

        // fordeler naboer og gir labyrint-referanse v2 - inspirasjon fra regneovelser 14.04.20
        for (int rad = 0; rad < rader; rad++) {
            for (int kol = 0; kol < kolonner; kol++) {
                rutenett[rad][kol].settReferanse(labyrint);
                if (rad > 0)
                    rutenett[rad][kol].settNord(rutenett[rad - 1][kol]);
                if (rad < rader - 1)
                    rutenett[rad][kol].settSor(rutenett[rad + 1][kol]);
                if (kol > 0)
                    rutenett[rad][kol].settVest(rutenett[rad][kol - 1]);
                if (kol < kolonner - 1)
                    rutenett[rad][kol].settOst(rutenett[rad][kol + 1]);
            }
        }

        // returner labyrinten vi lagde tidligere - med baade naboer og labyrint-referanse
        return labyrint;
    }

    // metode som kaller paa finnUtvei() paa node i posisjon fra parameter, og returnerer en liste med utveier
    public Liste<String> finnUtveiFra(int kol, int rad) {

        // oppretter ny liste for hver gang vi kaller finnUtveiFra
        utveier = new Lenkeliste<String>();

        // kaller paa finnUtvei() paa ruten i koordinater fra parameter
        rutenett[rad][kol].finnUtvei();

        return utveier;
    }

    // metode som returner den korteste utveien
    public String finnKortestUtvei() {

        // sjekker om utveier finnes
        if (utveier.stoerrelse() != 0) {

            // gaar gjennom alle utveiene - oppdater minste om stringen er mindre enn stringen i "minste"
            String minste = utveier.hent(0);
            for (String s : utveier) {
                if (s.length() < minste.length() ) {
                    minste = s;
                }
            } return minste;
        } return "Ingen utveier";
    }

    @Override // toString metode som printer ut brettet som String
    public String toString() {
        StringBuilder nett = new StringBuilder();

        for (Rute[] a : rutenett) {
            for (Rute b : a) {
                nett.append(b.tilTegn() + " ");
            }
            nett.append("\n");
        }
        nett.deleteCharAt(nett.length()-1);
        return nett.toString();
    }



    // test metode - toString() med tall paa topp og siden for oversikt
    public void skrivMedTall() {
        int teller = 0;

        System.out.println();
        // overste kant + tellere
        System.out.print("   | ");
        for (int i=0; i < antKol; i++) {
            if (i>9) {
                System.out.print(i);
            }
            else {
                System.out.print(i + " ");
            }
        }
        System.out.print("|");

        System.out.println();
        for (int i=0; i < antKol; i++) {
            System.out.print("--");
        } System.out.print("-----|");

        // selve rutenettet
        System.out.println();
        for (Rute[] a : rutenett) {
            System.out.printf("%2d | ", teller);
            for (Rute b : a) {
                System.out.print(b.tilTegn() + " ");
            }
            System.out.print("|\n");
            teller++;
        }

        // nederste kant
        for (int i=0; i < antKol; i++) {
            System.out.print("--");
        } System.out.print("-----");
        System.out.println("\n");
    }

    // test metode - skriver ut alle aapninger til rutenettet
    public void finnApninger() {

        for (int i=0; i < rutenett.length; i++) {
            for (int j=0; j < rutenett[i].length; j++) {
                if ( rutenett[i][j].erAapning() ) {
                    System.out.println("Fant aapning!");
                    System.out.println("Rad "+(i+1)+" Kolonne: "+(j+1)+ "  -  " + rutenett[i][j] + "\n");
                }
            }
        }
    }

    // test metode - skriver ut alle naboer til en celle fra parameter
    public void finnNaboer(int kol, int rad) {
        Rute denne = rutenett[rad][kol];
        System.out.println("Nord: [" + denne.nord + "]");
        System.out.println("Sor: [" + denne.sor + "]");
        System.out.println("Ost: [" + denne.ost + "]");
        System.out.println("Vest: [" + denne.vest + "]");
    }

}

