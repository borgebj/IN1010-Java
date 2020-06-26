import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


class Multi4 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("-------------------------------------\n");

        File fil = new File(args[0]);
        char[][] kart = lesFil(fil);

        skrivUt(kart);

        System.out.println("\n\n");

        finnKanter((kart));

        System.out.println("\n\n");

        finnApninger(kart);

        System.out.println("\n\n");

        finnHjoerner(kart);


        System.out.println("\n-------------------------------------");
    }

    static char[][] lesFil(File fil) throws FileNotFoundException {
        Scanner innFil = new Scanner(fil);

        int rad = Integer.parseInt(innFil.next());
        int kolonne = Integer.parseInt(innFil.next());

        // Lager og definerer størrelse
        char[][] kart = new char[rad][kolonne];

        while (innFil.hasNext()) {

            // lager et brett med kun "X"
            for (int i = 0; i < kart.length; i++) {
                String[] line = innFil.next().split("");

                for (int j = 0; j < kart[i].length; j++) {
                    kart[i][j] = line[j].charAt(0);
                }
            }
        }
        return kart;
    }

    static void skrivUt(char[][] kart) {

        for (char[] a : kart) {
            for (char b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }

    static void finnKanter(char[][] kart) {
        // test for aa finne kant
        for (int i=0; i < kart.length; i++) {
            for (int j=0; j < kart[i].length; j++) {

                // NOTE TO SELF: Denne er EKSTREM nyttig! LÆR
                if (i == 0 || j == 0 || i == kart.length - 1 || j == kart[i].length - 1)
                {
                    System.out.printf("On [%d, %d] is on the border.", i, j);
                    System.out.println("  -  " + kart[i][j]);
                }
            }
            System.out.println();
        }
    }


    static void finnApninger(char[][] kart) {

        int antAapninger = 0;

        for (int i=0; i < kart.length; i++) {
            for (int j=0; j < kart[i].length; j++) {
                if (j==0 || i==0 || i == kart.length-1 || j == kart[i].length-1) {
                    if (kart[i][j] == '.') {
                        System.out.println("Fant aapning!");
                        antAapninger++;
                        System.out.println("Rad "+(i+1)+" Kolonne: "+(j+1)+ "  -  " + kart[i][j] + "\n");
                    }
                }
            }
        }
    }

    static void finnHjoerner(char[][] kart) {
        for (int i=0; i < kart.length; i++) {
            for (int j=0; j < kart[i].length; j++) {

                if (i==0 && j==0) {
                    System.out.print("Overst venstre: ");
                    System.out.println(kart[i][j]);
                }

                if (i==0 && j==kart[i].length-1) {
                    System.out.print("Overst hoyre: ");
                    System.out.println(kart[i][j]);
                }

                if (j==0 && i==kart.length-1) {
                    System.out.print("Nederst venstre: ");
                    System.out.println(kart[i][j]);
                }

                if (j==kart[i].length-1 && i==kart.length-1) {
                    System.out.print("Nederst hoyre: ");
                    System.out.println(kart[i][j]);
                }
            }
        }
    }
}