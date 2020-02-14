import java.util.ArrayList;

class Rack {

    // instansvariabel - listen med noder
    private ArrayList<Node> rackArray;

    // oppretter ny rack
    public Rack() {
        rackArray = new ArrayList<Node>();
    }

    // plasserer node fra parameter inn i ArrayListen
    public void settInnNode(Node node) {
        rackArray.add(node);
    }

    // oker teller med 1 for hver node i racken
    public int getAntNoder() {
        int tot = 0;

        for (Node x : rackArray) {
            tot++;
            } return tot;
    }

    // gaar gjennom hver node og oker teller med antall prosessorer til hver minne, som returneres.
    public int antProsessorer() {
        int totalPros = 0;

        for (Node x : rackArray) {
            totalPros += x.antProsessorer();
        } return totalPros;
    }

    // gaar gjennom hver node og oker teller hver gang en node har minne over parameter-grensen - returner denne telleren
    public int noderMedNokMinne(int paaKrevdMinne) {
        int antNoder = 0;

        for (Node x : rackArray) {
            if (x.nokMinne(paaKrevdMinne)) {
                antNoder++; }
        } return antNoder;
    }
}