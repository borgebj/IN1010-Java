import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// "Vi bruker koordinater på formen (kolonne, rad)"

public class Labyrint {

    //  rad-kol
    private Rute [][] rutenett;
    int antRader;
    int antKol;


    // tar inn antall rader, antall kolonner, og et array av Ruter
    private Labyrint(int antRader, int antKol, Rute[][] rutenett) {
        this.antRader = antRader;
        this.antKol = antKol;
        this.rutenett = rutenett;
    }


    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner lesFil = new Scanner(fil);

        // henter de 2 foerste tallene og lagrer de som "rader" og "kolonner"
        String [] tall = lesFil.nextLine().trim().split(" ");
        int rader = Integer.parseInt(tall[0]);
        int kolonner = Integer.parseInt(tall[1]);

        Rute[][] brett = new Rute[rader][kolonner];


        // leser gjennom selve labyrinten, tegn for tegn
        while (lesFil.hasNext()) {

            for (int i=0; i < brett.length; i++) {
                String[] line = lesFil.nextLine().split("");

                for (int j=0; j < brett[i].length; j++) {

                    // sjekker kantene (hvis i/j er 0 eller i/j er lengden paa radene og kolonnene
                    if (j == 0 || i == 0 || j == kolonner-1 || i == rader-1) {

                        // sjekker hvilken type rute de er
                        if (line[j].equals(".")) {
                            brett[i][j] = new Aapning();
                        }
                        else if (line[j].equals("#")) {
                            brett[i][j] = new SortRute();
                        }
                    }

                    // hvis ikke er paa kanten:
                    else if (line[j].equals("#")) {
                        brett[i][j] = new SortRute();
                    }
                    else if (line[j].equals(".")) {
                        brett[i][j] = new HvitRute();
                    }
                }
            }
        }
        return new Labyrint(rader, kolonner, brett);
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


    // test metode
    public void finnApninger() {

        int antAapninger = 0;

        System.out.println();
        for (int i=0; i < rutenett.length; i++) {
            for (int j=0; j < rutenett[i].length; j++) {
                if (j==0 || i==0 || j == antKol-1 || i == antRader-1) {
                    if (rutenett[i][j].tilTegn() == '.') {
                        System.out.println("Fant aapning!");
                        antAapninger++;
                        System.out.println("Rad "+(i+1)+" Kolonne: "+(j+1)+ "  -  " + rutenett[i][j].tilTegn() + "\n");
                    }
                }
            }
        }
    }
}