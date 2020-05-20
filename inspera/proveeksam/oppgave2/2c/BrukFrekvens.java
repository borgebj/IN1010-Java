import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

// lenkeliste for String
class Enveisliste implements Iterable<String> {

    Node start;

    // indre klasse som inneholder dataen
    private static class Node {

        // innholdet til noden og neste node
        String innhold;
        int antall;
        Node neste;

        // konstruktoer
        Node(String innhold) { this.innhold = innhold; this.antall = 1; }
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

    // returner stoerrelsen til listen
    public int size() {
        int counter = 0;
        for (String s : this) {
            counter++;
        } return counter;
    }

    // henter stringen fra en gitt posisjon
    public String get(int pos) {
        if (pos < 0 || pos > size()) throw new IndexOutOfBoundsException("Ugyldig index: " + pos);
        Node denne = start;
        for (int i=0; i < pos; i++) {
            denne = denne.neste;
        } return denne.innhold;
    }

    public String remove (int pos) {
        if (pos < 0 || pos > size()) throw new IndexOutOfBoundsException("Ugyldig index: " + pos);

        if (pos == 0) {
            String innhold = start.innhold;
            start = start.neste;
            return innhold;
        }
        else {
            Node denne = start;
            for (int i=0; i < pos-1; i++) {
                denne = denne.neste;
            }
            Node fjernNode = denne.neste;
            denne.neste = fjernNode.neste;
            return fjernNode.innhold;
        }
    }

    // "komprimerer" listen og fjerner elementer som forekommer flere enn 1 ganger
    public void removeDuplicates() {
        Node forste = start;

        while (forste != null && forste.neste != null) {
            Node andre = forste;

            while (andre.neste != null) {

                if (forste.innhold.equals(andre.neste.innhold)) {
                    forste.antall++;
                    andre.neste = andre.neste.neste;
                }
                else {
                    andre = andre.neste;
                }
            }
            forste = forste.neste;
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


    // 2a - finner ordet som forekommer flest ganger og lagrer det
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

    // 2c - komprimerer listen ved aa fjerne ord som forekommer flere ganger
    public void komprimer() {
        list.removeDuplicates();
        System.out.println("kompromiss gjort");
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

        frekvens.komprimer();
    }
}