import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Terreng {

    // Liste hvor hvert sted er linket med et annet og liste med alle total gjenstander i terrenget
    ArrayList<Sted> steder = new ArrayList<Sted>();
    ArrayList<Gjenstand> gjenstander = new ArrayList<Gjenstand>();

    // random for aa velge tilfeldige gjenstander og scanner fra konstruktor
    Random rand = new Random();
    Scanner scanner;


    // konstruktor: gaar gjennom fil, lager Steder og legger til i ArrayList
    public Terreng(String filnavn) {
        File fil = new File(filnavn);
        try {
            Scanner scanner = new Scanner(fil);

            while (scanner.hasNextLine()) {

                // oppgave 4: oppretter VeivalgSted istendenfor Sted
                String beskrivelse = scanner.nextLine();
                Sted sted = new VeivalgSted(beskrivelse);
                steder.add(sted);
            }

            // setter tilfeldige naboer
            settNaboer();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // settNaboer gir et tilfeldig som nabo til hvert Sted i Super
    protected void settNaboer() {
        for (Sted s : steder) {

            Sted nabo = steder.get(rand.nextInt(steder.size()));

            // finn et sted som ikke er det samme
            while (nabo.hentBeskrivelse().equals(s.hentBeskrivelse())) {
                nabo = steder.get(rand.nextInt(steder.size()));
            }
            // sett naboer
            s.settNesteSted(nabo);
        }
    }

    private void opprettSkattkister() {

        for (Sted s : steder) {

            // hvert sted faar en kiste
            int antallGjenstander = rand.nextInt(4);
            Skattkiste kiste = new Skattkiste(antallGjenstander - 1 + 1);

            if (antallGjenstander > 0) {

                for (int i = 1; i < antallGjenstander; i++) {
                    Gjenstand gjenstand = gjenstander.get(rand.nextInt(gjenstander.size()));
                    kiste.leggNed(gjenstand);
                }
            }
            s.settKiste(kiste);
        }
    }

    public void fyllKister(String filnavn) {
        File fil = new File(filnavn);
        try {
            scanner = new Scanner(fil);

            while (scanner.hasNextLine()) {
                String[] linje = scanner.nextLine().split(" ");
                String navn = linje[0];
                int verdi = Integer.parseInt(linje[1]);

                // oppretter og legger til gjenstand
                Gjenstand gjenstand = new Gjenstand(navn, verdi);
                gjenstander.add(gjenstand);
            }

            // lager skatteskiter til hvert sted og tildeler de
            opprettSkattkister();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // velger en tilfeldig start
    public Sted hentStart() {
        return steder.get(rand.nextInt(steder.size()));
    }
}