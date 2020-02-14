import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


// jeg har valgt aa bruke ArrayList for bade Rack og Dataklynge fordi den er mye mer dynamisk enn vanlig arrray
// og i dette tilfellet hvor vi ikke trenger maks antall racks, er ArrayList best aa bruke.

public class Dataklynge {

    // instansvariabeler - liste og integer
    private ArrayList<Rack> klynge;
    private int maksAntNoder;

    // Original konstruktor
    /*
    // konstuktor - angir verdi fra parameter, lager ny klynge, ny rack og setter rack i klyngen
    public Dataklynge(int noderPerRack) {
        klynge = new ArrayList<Rack>();
        maksAntNoder = noderPerRack;

        // starter klyngen med 1 tom rack
        Rack rack = new Rack();
        klynge.add(rack);
    }
     */

    // Konstrktor som tar inn filnavn som parameter, og oppretter nye racks og noder med info fra filen
    public Dataklynge(String filnavn) throws FileNotFoundException {

        klynge = new ArrayList<Rack>();
        File innFil = new File(filnavn);
        Scanner lesFil = new Scanner(innFil);

        // forste linje i filen blir gjort om ting Integer og dermed satt inn i instansvariabel
        maksAntNoder = Integer.parseInt(lesFil.nextLine());

        // mens filen har flere linjer
        while (lesFil.hasNextLine()) {

            // linjen blir gjort om til en liste med hvert ord som element
            String[] biter = lesFil.nextLine().split(" ");

            e ord er antall prosessorer
            int antall = Integer.parseInt(biter[0]);
            int minne = Integer.parseInt(biter[1]);
            int antPros = Integer.parseInt(biter[2]);

            // node-objekt blir skapt for aa sette inn i klyngen
            Node node = new Node(minne, antPros);

            for (int i=0; i < antall; i++) {
                settInnNode(node);
            }
        }
    }

    // setter inn node i ledig rack, ellers lager ny rack og setter inn node - deretter setter ny rack i klynge
    public void settInnNode(Node node) {

        for (Rack x : klynge) {
            if (x.getAntNoder() < maksAntNoder) {
                x.settInnNode(node);

                // om noden er satt inn, stopp lokken med return
                return;
            }
        }

        Rack rack2 = new Rack();
        rack2.settInnNode(node);
        klynge.add(rack2);
    }

    // henter total antall prosessorer fra hver node i hver rack og returnerer det
    public int antProsessorer() {
        int tot = 0;

        for (Rack x : klynge) {
            tot += x.antProsessorer();
        } return tot;
    }

    // gaar gjennom hver rack og sjekker hver node hvor mange som har minne over grensen fra parameteren
    public int noderMedNokMinne(int paaKrevdMinne) {
        int noderMedNok = 0;

        for (Rack x : klynge) {
            noderMedNok += x.noderMedNokMinne(paaKrevdMinne);
        } return noderMedNok;
    }

    // teller antall racks i klyngen og returnerer det
    public int antRacks() {
        int antRacks = 0;

        for (Rack x : klynge) {
            antRacks++;
        } return antRacks;
    }

}
