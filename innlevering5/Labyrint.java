import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// "Vi bruker koordinater på formen (kolonne, rad)"

public class Labyrint {

    //  rad-kol
    private Rute [][] rutenett;
    Liste<String> utveier;
    int antRader;
    int antKol;


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
            for (int i=0; i < brett.length; i++) {
                String[] line = lesFil.nextLine().split("");

                for (int j=0; j < brett[i].length; j++) {

                    // hvis tegn er "." - sjekk om den er ved kant foer oppretter rute
                    if (line[j].equals(".")) {

                        // sjekker kantene (hvis i/j er 0 eller i/j er lengden paa radene og kolonnene
                        if (j == 0 || i == 0 || j == kolonner - 1 || i == rader - 1) {
                            brett[i][j] = new Aapning(i, j);
                        }
                        else {
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

        // fordeler naboer og gir labyrint-referanse
        for (int i=0; i < rutenett.length; i++) {
            for (int j=0; j < rutenett[i].length; j++) {

                Rute nord = null;
                Rute sor = null;
                Rute ost = null;
                Rute vest = null;

                // sjekker og tildeler x-aksen (i/rad)
                // hvis forste rad, ingen nord
                if (i==0) {
                    sor = rutenett[i+1][j];
                }
                // hvis siste rad, ingen sor
                else if (i==rutenett.length-1) {
                    nord = brett[i-1][j];
                }
                // rader ellers, har begge
                else {
                    nord = rutenett[i-1][j];
                    sor = rutenett[i+1][j];
                }

                // sjekker og tildeler y-aksen (j/kolonne)
                // hvis forste kolonne, ingen vest
                if (j==0) {
                    ost = rutenett[i][j+1];
                }
                // hvis siste kolonne, ingen ost
                else if (j==rutenett[i].length-1) {
                    vest = rutenett[i][j-1];
                }
                // kolonner ellers, har begge
                else {
                    ost = rutenett[i][j+1];
                    vest = rutenett[i][j-1];
                }
                rutenett[i][j].settReferanse(labyrint);
                rutenett[i][j].settNaboer(nord, sor, ost, vest);
            }
        }
        return new Labyrint(rader, kolonner, brett);
    }

    // metode som kaller paa finnUtvei() paa node i posisjon fra parameter, og returnerer en liste med utveier
    public Liste<String> finnUtveiFra(int kol, int rad) {

        // for hvert kall lages en ny lenkeliste med utveier
        utveier = new Lenkeliste<String>();

        rutenett[rad][kol].finnUtvei();

        return utveier;
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

        System.out.print("  ");
        for (int i=0; i < antKol; i++) {
            if (i>9) {
                System.out.print(i);
            }
            else {
                System.out.print(i + " ");
            }
        }

        System.out.println();
        for (Rute[] a : rutenett) {
            System.out.print(teller+" ");
            for (Rute b : a) {
                System.out.print(b.tilTegn() + " ");
            }
            System.out.println();
            teller++;
        }
    }

    // test metode - skriver ut alle aapninger til rutenettet
    public void finnApninger() {

        System.out.println();
        for (int i=0; i < rutenett.length; i++) {
            for (int j=0; j < rutenett[i].length; j++) {
                if (rutenett[i][j] instanceof Aapning) {
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

    // test metode - skriver ut koordinater til en rute
    public void hentKoordinater(int kol, int rad) {
        System.out.println("Rad: " + rutenett[rad][kol].rad);
        System.out.println("Kolonne: " + rutenett[rad][kol].kolonne);
    }
}

