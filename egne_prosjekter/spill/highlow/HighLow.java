import java.util.Scanner;
import java.util.Random;

class HighLow {

    public static void main(String[] args) {

        // Random-obejkt (gir et tilfeldig tall)
        Random rand = new Random();

        // scanner-objekt (gir muligheten til input fra bruker)
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nSkriv inn et tall mellom 0 og 100: ");
        int input = scanner.nextInt();

        // tallet brukeren skal finne blir satt til et tilfeldig tall mellom 0 og 100
        int tall = rand.nextInt(100);

        // mens input fra bruker ikke er tallet
        while (input != tall) {
            if (input > 100 || input < 0) {
                System.out.print("  " + "Feil. Skriv et tall mellom 0 og 100: ");
                input = scanner.nextInt();
            } else if (input > tall) {
                System.out.print("  " + input + " er for hoyt! Prov igjen: ");
                input = scanner.nextInt();
            } else if (input < tall) {
                System.out.print("  " + input + " er for lavt! Prov igjen: ");
                input = scanner.nextInt();
            }
        }

        // printer ut etter tallet er funnet
        System.out.println("       -  " + tall + " er riktig!  -");
    }
}