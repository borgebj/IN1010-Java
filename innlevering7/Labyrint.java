import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// "Vi bruker koordinater på formen (kolonne, rad)"

public class Labyrint {

    private final Rute [][] rutenett;
    int antRader;
    int antKol;
    Liste<String> utveier;  // monitor?

    // tar inn antall rader, antall kolonner, og et array av Ruter
    private Labyrint(int antRader, int antKol, Rute[][] rutenett) {
        this.antRader = antRader;
        this.antKol = antKol;
        this.rutenett = rutenett;
    }

    // metode som leser fra fil og initierer labyrintens-konstruktor
    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner lesFil = new Scanner(fil);

        /// oppretter variabel for rader og kolonner - og hopper til neste linje
        int rader = lesFil.nextInt(), kolonner = lesFil.nextInt(); lesFil.nextLine();
        Rute[][] brett = new Rute[rader][kolonner];

        // kaller paa hjelepmetode for aa fylle tomt (Rute[][]) brett med Ruter fra - leser fra scanner gitt i parameter
        lagBrett(brett, rader, kolonner, lesFil);

        // oppretter labyrint-objektet for aa legge til referanse og naboer
        Labyrint labyrint = new Labyrint(rader, kolonner, brett);

        // kaller paa hjelpemetode for aa sette naboer
        settNaboer(labyrint);

        // returner labyrinten vi lagde tidligere - med baade naboer og labyrint-referanse
        return labyrint;
    }



    // hjelpemetode for aa fylle et tomt (Rute[][]) brett med Ruter
    private static void lagBrett(Rute[][] brett, int rader, int kolonner, Scanner lesFil) {
        for (int i = 0; i < rader; i++) {
            String line = lesFil.nextLine();
            for (int j = 0; j < kolonner; j++) {
                brett[i][j] = lagRute(line.charAt(j), i, j, rader, kolonner);   // inspirasjon fra regneovelser 14.04.20
            }
        }
    }

    // hjelpemtode for aa avgjoere rute-objekt til koordinater fra parameter - inspirasjon fra regneovelser 14.04.20
    private static Rute lagRute(char tegn, int rad, int kolonne, int antRader, int antKolonner) {
        if (tegn=='#')
            return new SortRute(rad, kolonne);  // SortRute lages om tegn er "#"
        if (rad==0 || kolonne==0 || rad==antRader-1 || kolonne==antKolonner-1 && tegn=='.')
            return new Aapning(rad, kolonne); // Aapning lages om tegn er "." og er paa kanten
        return new HvitRute(rad, kolonne);  // ellers lages en HvitRute
    }

    // hjelpemetode for "lesFraFil" - setter naboer og referanse til labyrint-objekt fra parameter
    private static void settNaboer(Labyrint labyrint) {

        // fordeler naboer og gir labyrint-referanse v2 - inspirasjon fra regneovelser 14.04.20
        for (int rad = 0; rad < labyrint.antRader; rad++) {
            for (int kol = 0; kol < labyrint.antKol; kol++) {
                sjekkNaboer(rad, kol, labyrint);
            }
        }
    }

    // hjelpemetode for "settNaboer" - sjekker og legger til naboer for koordianter fra parameter
    private static void sjekkNaboer(int rad, int kol, Labyrint labyrint) {
        Rute[][] rutenett = labyrint.rutenett;

        // setter referanse for alle rutene foerst
        rutenett[rad][kol].settReferanse(labyrint);

        // sjekker hvor i brettet ruten er, og gir naboer tilsvarende
        if (rad > 0)
            rutenett[rad][kol].settNabo(rutenett[rad - 1][kol], 'N');
        if (rad < labyrint.antRader - 1)
            rutenett[rad][kol].settNabo(rutenett[rad + 1][kol], 'S');
        if (kol > 0)
            rutenett[rad][kol].settNabo(rutenett[rad][kol - 1], 'O');
        if (kol < labyrint.antKol - 1)
            rutenett[rad][kol].settNabo(rutenett[rad][kol + 1], 'V');
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
                if (s.length() < minste.length() )
                    minste = s;
            } return minste;
        } return "Ingen utveier";
    }

    @Override // toString metode som printer ut brettet som String v2 - inspirasjon fra regneovelser 14.04.20
    public String toString() {
        StringBuilder utskrift = new StringBuilder(new String(new char[50]).replace("\0", "\n"));
        for (int rad=0; rad < antRader; rad++) {
            utskrift.append("\n");
            for (int kol=0; kol < antKol; kol++) {
                utskrift.append(rutenett[rad][kol].tilTegn()).append(" ");
            }
        }
        return utskrift.toString();
    }

    // for GUI-program - returner selve rutenettet med ruter
    public Rute[][] hentbrett() {
        return rutenett;
    }

    public Rute hentRute(int kol, int rad) {
        return rutenett[rad][kol];
    }




    // test metode - toString() med tall paa topp og siden for oversikt
    public void skrivMedTall() {
        int teller = 0;

        System.out.println();
        // overste kant + tellere
        System.out.print("   | ");
        for (int i=0; i < antKol; i++) {
            if (i>9)
                System.out.print(i);
            else
                System.out.print(i + " ");
        } System.out.print("|");

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
            } System.out.print("|\n");
            teller++;
        }

        // nederste kant
        for (int i=0; i < antKol; i++) {
            System.out.print("--");
        } System.out.print("-----");
        System.out.println("");
    }

    // test metode - skriver ut alle aapninger til rutenettet
    public void finnApninger() {
        for (int i=0; i < rutenett.length; i++) {
            for (int j=0; j < rutenett[i].length; j++) {
                if ( rutenett[i][j] instanceof Aapning ) {
                    System.out.println("Fant aapning!");
                    System.out.println("Rad "+(i+1)+" Kolonne: "+(j+1)+ "  -  " + rutenett[i][j] + "\n");
                }
            }
        }
    }

    // test metode - skriver ut alle naboer til en celle fra parameter
    public void finnNaboer(int kol, int rad) {
        Rute denne = rutenett[rad][kol];
        System.out.println("Denne: [" + denne + "]");
        System.out.println("Nord: [" + denne.nord + "]");
        System.out.println("Sor: [" + denne.sor + "]");
        System.out.println("Ost: [" + denne.ost + "]");
        System.out.println("Vest: [" + denne.vest + "]");
    }

}

