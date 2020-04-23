import java.util.Random;
import java.util.Scanner;

/* Brettet:
      +---+---+---+
      | 1 | 2 | 3 |
      +---+---+---+
      | 4 | 5 | 6 |
      +---+---+---+
      | 7 | 8 | 9 |
      +---+---+---+

   Spillerne:
      X - maskinen
      O - brukeren
 */

class Brett {
    Rute[] ruter = new Rute[1+9];

    Brett() {
        for (int i = 1;  i <= 9;  i++)
            ruter[i] = new Rute(' ');
    }

    void spill() {
        while (true) {
            spillX();
            if (harVunnet('X')) utropVinner('X');
            if (erUavgjort()) utropUavgjort();

            spillO();
            if (harVunnet('O')) utropVinner('O');
            if (erUavgjort()) utropUavgjort();
        }
    }

    Random tilfeldig = new Random();
    void spillX() {
        int p;
        do {
            p = tilfeldig.nextInt(9)+1;
        } while (ruter[p].merke != ' ');
        ruter[p].settMerke('X');
        System.out.println("X spiller " + p);
    }

    Scanner tastatur = new Scanner(System.in);
    void spillO() {
        int p;
        do {
            System.out.print("Hva spiller O? ");
            p = tastatur.nextInt();
        } while (ruter[p].merke != ' ');
        ruter[p].settMerke('O');
        System.out.println("O spiller " + p);
    }

    boolean likRad(int r1, int r2, int r3, char c) {
        if (ruter[r1].merke != c) return false;
        if (ruter[r2].merke != c) return false;
        if (ruter[r3].merke != c) return false;
        return true;
    }

    boolean harVunnet(char c) {
        return
                likRad(1, 2, 3, c) ||  // Vannrett
                        likRad(4, 5, 6, c) ||
                        likRad(7, 8, 9, c) ||
                        likRad(1, 4, 7, c) ||  // Loddrett
                        likRad(2, 5, 8, c) ||
                        likRad(3, 6, 9, c) ||
                        likRad(1, 5, 9, c) ||  // Diagonal
                        likRad(3, 5, 7, c);
    }

    boolean erUavgjort() {
        for (int i = 1;  i <= 9;  i++)
            if (ruter[i].merke == ' ') return false;
        return true;
    }

    void utropVinner(char s) {
        System.out.println("Vinneren er " + s + "!");
        System.exit(0);
    }

    void utropUavgjort() {
        System.out.println("Det ble uavgjort!");
        System.exit(0);
    }
}

class Rute {
    char merke;

    Rute(char c) {
        merke = c;
    }

    void settMerke(char c) { merke = c; }

    @Override
    public String toString() { return ""+merke; }
}

class TTT1 {
    public static void main(String[] args) {
        Brett b = new Brett();
        b.spill();
    }
}