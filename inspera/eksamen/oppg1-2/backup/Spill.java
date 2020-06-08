import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Spill {

    private static final Scanner scanner = new Scanner(System.in);
    private static Terreng terreng;
    private static Spiller enkeltSpiller;


    private static void opprettTerreng(String steder, String gjenstander) {
        terreng = new Terreng(steder);
        terreng.fyllKister(gjenstander);
    }


    // for aa opprette en spiller
    private static void opprettSpiller(String navn, int antallTrekk) {
        Terminal brukerTerminal = new Terminal(scanner);

        Sted startSted = terreng.hentStart();
        Spiller spiller = new Spiller(navn, antallTrekk, brukerTerminal, startSted);
        brukerTerminal.settSpiller(spiller);

        enkeltSpiller = spiller;
    }

    // for aa opprette en robott
    private static void opprettRoboter(String navn, int antallTrekk) {
        Robot robot = new Robot();

        Sted startsted = terreng.hentStart();
        Spiller spiller = new Spiller(navn+"0", antallTrekk, robot, startsted);
        robot.settSpiller(spiller);

        enkeltSpiller = spiller;
    }

    // skriver ut resultatet til den ene spilleren
    private static void skrivResultat() {

        // "clear-screen"
        System.out.println(new String(new char[50]).replace('\0', '\n'));

        System.out.println("\nResultatet ....");
        System.out.println("- Spiller " + enkeltSpiller.hentNavn() + " fikk " + enkeltSpiller.hentFormue() + " poeng!");
    }

    public static void main(String[] args) {
        try {
            final String steder = args[0];
            final String gjenstander = args[1];
            final int maksAntallTrekk = Integer.parseInt(args[2]);

            opprettTerreng(steder, gjenstander);

            // for aa lage en vanlig spiller
            //opprettSpiller("Arne", maksAntallTrekk);

            // for aa lage en robot
            opprettRoboter("Robot", maksAntallTrekk);

            Spiller SpillerEn = enkeltSpiller;

            while (SpillerEn.hentTrekk() > 0) {
                SpillerEn.nyttTrekk();
            }

            // sorterer og skriver ut resultatene
            skrivResultat();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\n\nSpill maa ha argumentene: steder.txt gjenstander.txt antallMaksTrekk\n");
            System.exit(0);
        }
    }
}