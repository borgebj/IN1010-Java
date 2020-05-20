import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Frekvens {

    // instansvariabler
    ArrayList<String> list;
    String flestTekst;
    int flestAntall;


    // konstruktoer
    public Frekvens(ArrayList<String> list) {
        this.list = list;
    }


    // finner ordet som forekommer flest ganger og lagrer det
    public void finnFlest() {
        int antall = 0;
        int teller;
        String flest = "";

        // itererer gjennom alle
        for (String s : list) {
            teller = 0;

            // for hver: iterer gjennom alle igjen og sammenlign
            for (String ss : list) {
                if (s.equals(ss))
                    teller++;
            }

            // hvis antall er hoyere, endre flest
            if (teller > antall) {
                flest = s;
                antall = teller;
            }
        }
        flestTekst = flest;
        flestAntall = antall;
    }

    // returner teksten som forekommer flest ganger
    public String hentFlest() {
        return flestTekst;
    }

    // returner hvor ofte denne teksten forekommer
    public int hentAntall() {
        return flestAntall;
    }
}

// main-class
public class BrukFrekvens {
    public static void main(String[] args) throws FileNotFoundException {

        // oppretter selve filen og scanneren
        String filnavn = args[0];
        File fil = new File(filnavn);
        Scanner scanner = new Scanner(fil);

        // oppretter tom liste som skal sendes inn som parameter senere
        ArrayList<String> liste = new ArrayList<String>();

        // for hver linje, legg til i listen
        while (scanner.hasNextLine()) {
            liste.add( scanner.nextLine() );
        }

        // oppretter frekvens-objekt
        Frekvens frekvens = new Frekvens(liste);

        // finner ordet som kommer oftest
        frekvens.finnFlest();

        String flestString = frekvens.hentFlest();
        int flestAntall = frekvens.hentAntall();

        System.out.println("Stringen '" + flestString + "' forekom oftest, med '" + flestAntall + "' antall ganger.");
    }
}