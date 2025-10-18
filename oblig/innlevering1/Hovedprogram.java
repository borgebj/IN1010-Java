package innlevering1;

import java.io.FileNotFoundException;

class Hovedprogram {

    // main metode med throws FileNotFoundException pga bruk av "File"
    public static void main(String[] args) throws FileNotFoundException {

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