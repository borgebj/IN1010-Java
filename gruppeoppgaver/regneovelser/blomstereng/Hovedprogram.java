import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Hovedprogram
{

    private static Scanner scanner;
    private static int rader;
    private static int kolonner;
    private static File fil;
    private static JordFlekk[][] rutenett;
    private static Eng eng;

    public static void main(String[] args)
    {
        // rekkefolgen paa hva som skjer
        settScannerOgFil(args[0]);
        settRaderOgKolonner();
        settRutenett();
        fyllRutenettMedJordflekkerOgSteiner();
        settNaboReferanser();
        opprettEng();
        settEngReferanseIJordFlekker();
        begynnPollinering();
    }

    private static void settScannerOgFil(String filnavn)
    {
        fil = new File(filnavn);
        try {
            scanner = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void settRaderOgKolonner()
    {
        String[] raderOgKolonner = scanner.nextLine().split(" ");
        rader = Integer.parseInt(raderOgKolonner[0]);
        kolonner = Integer.parseInt(raderOgKolonner[1]);
    }

    private static void settRutenett()
    {
        rutenett = new JordFlekk[rader][kolonner];
    }

    private static void fyllRutenettMedJordflekkerOgSteiner()
    {
        for (int rad=0; rad < rader; rad++) {
            String raden = scanner.nextLine();
            for (int kol=0; kol < kolonner; kol++) {
                rutenett[rad][kol] = avgjoerObjekt(raden.charAt(kol));
            }
        }
    }

    private static void settNaboReferanser()
    {
        for (int rad=0; rad < rader; rad++) {
            for (int kol=0; kol < kolonner; kol++) {
                settNabo(rad, kol);
            }
        }
    }

    private static void opprettEng()
    {
        eng = new Eng(rutenett, rader, kolonner);
    }

    private static void settEngReferanseIJordFlekker()
    {
        for (int rad=0; rad < rader; rad++) {
            for (int kol=0; kol < kolonner; kol++) {
                rutenett[rad][kol].settEngReferanse(eng);
            }
        }
    }

    private static void begynnPollinering()
    {
        String[] radOgKolonne = hentBrukerInput();
        int rad = Integer.parseInt(radOgKolonne[0]);
        int kolonne = Integer.parseInt(radOgKolonne[1]);
        eng.polliner(rad, kolonne);
    }

    // hjelpemetode for "begynnPollinering"
    private static String[] hentBrukerInput()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Velg rad og kolonne: Eks 5 5 \n > ");
        return scanner.nextLine().split(" ");
    }

    // hjelpemetode for "fyllRutenettMedJordflekkerOgSteiner"
    private static JordFlekk avgjoerObjekt(char tegn) {
        if (tegn == '.')
            return new JordFlekk();
        return new Stein();
    }

    // hjelpemetode for "settNaboReferanser"
    private static void settNabo(int rad, int kol)
    {
        if (rad > 0)
            rutenett[rad][kol].settNord(rutenett[rad-1][kol]);
        if (rad < rader - 1)
            rutenett[rad][kol].settSyd(rutenett[rad+1][kol]);
        if (kol > 0)
            rutenett[rad][kol].settVest(rutenett[rad][kol-1]);
        if (kol < kolonner - 1)
            rutenett[rad][kol].settOst(rutenett[rad][kol+1]);
    }
}