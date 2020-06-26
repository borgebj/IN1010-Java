import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


class Hovedprogram {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        // deklarerer en ArrayList og initierer med en metode som returner en ferdig ArrayList
        ArrayList<CDAlbum> sortertListe = lesOgSorter(args[0]);

        // printer ut hver CDAlbum i listen
        System.out.println("\nCDAlbum i sortert rekkefølge: \n");
        for (CDAlbum album : sortertListe) {
            System.out.println(album);
        }
        System.out.println();

        listeTilFil(sortertListe, args[1]);
    }

    // sorterer en fil og returner en liste
    public static ArrayList<CDAlbum> lesOgSorter(String filnavn) throws FileNotFoundException {

        // Fil og Scanner-objekt
        File fil = new File(filnavn);
        Scanner lesFil = new Scanner(fil);

        // liste foer sortering
        ArrayList<CDAlbum> liste = new ArrayList<CDAlbum>();

        // for hver linje:
        while (lesFil.hasNextLine()) {
            String[] line = lesFil.nextLine().trim().split(",");

            // lager CDAlbum-objekter med foerste, andre og tredje ord i hver linje som parameter
            CDAlbum album = new CDAlbum(line[0], line[1], line[2]);

            boolean sattInn = false;
            int teller = 0;

            // gaar gjennom hvert album i listen og oker teller med 1 for hvert objekt if-sjekket slaar til
            for (int i = 0; i < liste.size(); i++) {
                if (liste.get(i).compareTo(album) < 0) {
                    teller++;
                }
            }
            // legger til album i teller-index (legger til uansett om liste er tom);
            liste.add(teller, album);

        }
        return liste;
    }

    // skriver sortert liste til fil
   public static void listeTilFil(ArrayList<CDAlbum> liste, String filnavn) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filnavn);

        // gaar gjennom og tildeler verdier til alle CDAlbum
        for (CDAlbum album : liste) {
            String navn = album.hentArtistnavn();
            String albumNavn = album.hentAlbumnavn();
            String aar = album.hentUtgivelseaar();

            // skriver til fil
            writer.write(navn +","+ albumNavn +","+ aar +"\n");
        }
        // lukker/lagrer filen
        writer.close();
   }
}