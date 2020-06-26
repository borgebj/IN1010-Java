import java.util.Random;

class Multi3 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------\n");
        Random rand = new Random();

        // Variabler for rad og kolonne (Størrelse)
        int rad = 3;
        int kolonne = 3;

        // Lager og definerer størrelse
        char[][] kart = new char[rad][kolonne];

        // lager et brett med kun "X"
        for (int i=0; i < kart.length; i++) {
            for (int j=0; j < kart[i].length; j++) {
                kart[i][j] = 'X';
            }
        }
        // genererer tilfeldig plass til skatten
        int skattRad = rand.nextInt(rad);
        int skattKolonne = rand.nextInt(kolonne);
        System.out.println("Totalt - Rader: " + rad + " Kolonner: " + kolonne);
        System.out.println("Skatt - Rad: "+ skattRad +"  kolonne: "+ skattKolonne + "\n");

        // legger inn skatt
        kart[skattRad][skattKolonne] = 'O';

        // printer brettet
        for (char[] a : kart) {
            for (char b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println("\n-------------------------------------");
    }
}