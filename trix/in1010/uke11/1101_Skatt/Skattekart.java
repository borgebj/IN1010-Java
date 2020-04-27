import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class Skattekart {

    char [][] skattebrett;
    int hoyde;
    int bredde;
    boolean erFunnet = false;

    // privat konstruktoer
    private Skattekart(char[][] array, int hoyde, int bredde) {
        skattebrett = array;
        this.hoyde = hoyde;
        this.bredde = bredde;
    }

    // leser fra fil, lager og returner skattebrett
    static Skattekart lesInn(File f) throws FileNotFoundException {
        Scanner lesFil = new Scanner(f);

        // leser foerste linjen og oppretter variabler
        String [] tall = lesFil.nextLine().split(" ");
        int bredde = Integer.parseInt(tall[0]);
        int hoyde = Integer.parseInt(tall[1]);

        // oppretter selve brettet
        char [][] brett = new char [bredde][hoyde];

        for (int i=0; i < brett.length;  i++) {
            String [] line = lesFil.next().split("");

            for (int j=0; j < brett[i].length; j++) {
                brett[i][j] = line[j].charAt(0);
            }
        }
        // returner privat konstruktoer
        return new Skattekart(brett, hoyde, bredde);
    }

    // skriver ut skattekart
    void skrivUt() {
        System.out.println("---------------");
        for (char[] a : skattebrett) {
            System.out.print("| ");
            for (char b : a) {
                if (erFunnet) {
                    System.out.print(b + " ");
                } else if (!erFunnet) {
                    if (b == 'O') {
                        System.out.print('X' + " ");
                    } else System.out.print(b + " ");
                }
            }
            System.out.print("| \n");
        }
        System.out.println("---------------");
    }

    // sjekker om skatten er paa indeks fra parameter
    boolean sjekk(int rad, int kol) {
        if (skattebrett[rad][kol] == 'X') {
            erFunnet = true;
            return true;
        }
        else {
            skattebrett[rad][kol] = ' ';
            return false;
        }
    }
}