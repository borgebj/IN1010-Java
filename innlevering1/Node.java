

public class Node {

    // instansvariabel for minne-storrelse i GB
    private int minStorrelse;

    // instansvariabel for antall prosessorer i noden
    private int antPros;

    // konstruktor som angir instansvariablene verdier fra parameter
    public Node(int minne, int ant) {

        // om antall prosessorer er for mye/lite, gis bedskjed og variabler blir ikke endret
        if (antPros > 2 || antPros < 0 ) {
            System.out.println("Du kan bare ha 1-2 prosessorer");
        } else {
            antPros = ant;
            minStorrelse = minne;
        }
    }

    // metode som returnerer antall prosessorer > instansvariablen "antPros"
    public int antProsessorer() {
        return antPros;
    }

    // metode som sjekker om Noden har nok minne ved aa sammenligne minStorrelse variabel med verdi fra parameter
    public boolean nokMinne(int paakrevdMinne) {

        if (minStorrelse >= paakrevdMinne) {
            return true;
        } return false;
    }
    
}