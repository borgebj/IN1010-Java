import java.io.FileNotFoundException;

class Hovedprogram {

    // main metode med throws FileNotFoundException pga bruk av "File"
    public static void main(String[] args) throws FileNotFoundException {

        // Original kode foer ny konstruktor i dataklynge
        /*
        // oppretter 1 Dataklynge-objekt og 2 Node-objekter med ulike verider
        Dataklynge abel = new Dataklynge(12);
        Node node1 = new Node(64, 1);
        Node node2 = new Node(1024, 2);
         */

        // Original kode for innlegging av noder
        /*
        // for-lokke som legger inn 650 av node1 i abel
        for (int i=0; i < 650; i++) {
            abel.settInnNode(node1);
        }

        // for-lokke som legger inn 16 av node2 i abel
        for (int j=0; j < 16; j++) {
            abel.settInnNode(node2);
        }
         */

        // ny kode etter ny konstruktor i dataklynge
        Dataklynge abel = new Dataklynge("dataklynge.txt");

        // utskrift til terminalen
        System.out.println("\n");

        System.out.println("Noder med minst 32 GB : " + abel.noderMedNokMinne(32));
        System.out.println("Noder med minst 64 GB : " + abel.noderMedNokMinne(64));
        System.out.println("Noder med minst 128 GB: " + abel.noderMedNokMinne(128));
        System.out.println();
        System.out.println("Antall prosessorer: " + abel.antProsessorer());
        System.out.println("Antall rack: " + abel.antRacks());

        System.out.println("\n");

    }
}