import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Gruppeoppmoete {
    private String[] navn = new String[14];
    private boolean[] oppmoete = new boolean[14];
    private int antStud = 0;
    // Fyll inn med metodene nevnt over.

    public void fyllInn(String filnavn) throws FileNotFoundException {
        Scanner lesFil = new Scanner(new File(filnavn));

        while (lesFil.hasNextLine() && antStud < navn.length) {
            navn[antStud] = lesFil.nextLine();
            antStud++;
        }
    }

    public void harRegistrert(String sjekknavn) {
        for (int i = 0; i < antStud; i++) {
            if (sjekknavn.equals(navn[i])) {
                oppmoete[i] = true;
            }
        }
    }

    public void skrivUt () {
            for (int i = 0; i < antStud; i++) {

                if (oppmoete[i]) {
                    System.out.println(navn[i] + " har mott");
                } else {
                    System.out.println(navn[i] + " har ikke mott");
                }
            }
        }
}