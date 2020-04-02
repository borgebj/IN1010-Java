import java.util.Random;

class Multi3 {
    public static void main(String[] args) {
        System.out.println("-------------------------------------\n");
        Random rand = new Random();

        // Variabler for rad og kolonne (Størrelse)
        int rad = 4;
        int kolonne = 5;

        // Lager og definerer størrelse
        char[][] kart = new char[rad][kolonne];

        // lager et brett med kun "X"
        for (int i=0; i < kart.length; i++) {
            for (int j=0; j < kart[i].length; j++) {
                kart[i][j] = 'X';
            }
        }
        // genererer tilfeldig plass til skatten
        int skattRad = rand.nextInt(4);
        int skattKolonne = rand.nextInt(5);
        System.out.println("Rad: "+ skattRad +"  kolonne: "+ skattKolonne);

        // legger inn skatt
        kart[skattRad][skattKolonne] = 'O';

        for (char[] a : kart) {
            for (char b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println("\n-------------------------------------");
    }
}