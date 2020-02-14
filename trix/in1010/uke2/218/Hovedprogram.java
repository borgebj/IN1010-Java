import java.io.FileNotFoundException;

class Hovedprogram {
    public static void main(String[] args) throws FileNotFoundException {
        Gruppeoppmoete mote = new Gruppeoppmoete();

        // kaller paa fyllInn
        mote.fyllInn("gruppeliste.txt");

        mote.harRegistrert("Olga");
        mote.harRegistrert("Gunnar");
        mote.harRegistrert("Bilal");
        mote.harRegistrert("Reodor");

        mote.skrivUt();
    }
}