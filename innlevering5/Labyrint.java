import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// "Vi bruker koordinater på formen (kolonne, rad)"

class Labyrint {

    //  rad-kol
    private Rute [][] rutenett;
    int antRader;
    int antKol;


    // tar inn antall rader, antall kolonner, og et array av Ruter
    private Labyrint(int antRader, int antKol, Rute [][] rutenett) {
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

        char[][] brett = new char[rader][kolonner];

        System.out.println("Rader: " + rader);
        System.out.println("Kolonner: " + kolonner);

        // leser gjennom selve labyrinten, tegn for tegn
        while (lesFil.hasNext()) {

        }

        Rute [][] testBrett = new Rute[kolonner][rader];
        return new Labyrint(rader, kolonner, testBrett);
    }


    // returner en string-representasjon av labyrinten
    public String toString() {
        String labyrint = "";

        // gaa gjennom rutenettet, legg til character og legg til "\n"

        return labyrint;
    }
}