import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

// lenkeliste for String
class Enveisliste implements Iterable<String> {

    Node start;

    // indre klasse som inneholder dataen
    private class Node {

        // innholdet til noden og neste node
        String innhold;
        Node neste;

        // konstruktoer
        Node(String innhold) { this.innhold = innhold; }
    }

    // indre klasse "LenkelisteIterator" brukt for aa iterere gjennom Lenkeliste
    private class listeIterator implements Iterator<String> {

        Node current = start;

        // sjekker om lenkelisten har en til node
        public boolean hasNext() {

            // returner om neste finnes eller ikke
            return current != null;
        }

        // sjekker og returner om det finnes en neste node (returner data fra noden)
        public String next(){

            // om neste node finnes, return data
            if (hasNext()){
                String data = current.innhold;
                current = current.neste;
                return data;
            }
            // om neste er tom
            else {
                return null;
            }
        }

    }

    // Metode som returner nytt LenkelisteIterator-objekt
    public Iterator<String> iterator() {
        return new listeIterator();
    }

    // legger til nytt element
    public void add(String s) {
        if (start==null) { start = new Node(s); }
        else {
            Node denne = start;
            while (denne.neste != null) {
                denne = denne.neste;
            }
            denne.neste = new Node(s);
        }
    }
}

class Frekvens {

    // instansvariabler
    Enveisliste list;
    String flestTekst;
    int flestAntall;


    // konstruktoer
    public Frekvens(Enveisliste list) {
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
class BrukFrekvens {
    public static void main(String[] args) throws FileNotFoundException {

        // oppretter selve filen og scanneren
        String filnavn = args[0];
        File fil = new File(filnavn);
        Scanner scanner = new Scanner(fil);

        // oppretter tom "enveisliste" som skal sendes inn som parameter senere
        Enveisliste liste = new Enveisliste();

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