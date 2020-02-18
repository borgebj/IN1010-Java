import kalkulator.Kalkulator;
import java.util.Scanner;

public class Svar {
    public static void main(String[] args){
        // scanner/input-objekt
        Scanner scanner = new Scanner(System.in);

        System.out.print("Skriv inn ditt tall: ");
        int inp = scanner.nextInt();

        // kalkulator-objekt
        Kalkulator objekt = new Kalkulator(inp);
        System.out.println("Ditt tall: " + objekt.hentSvar());

        String pluss = scanner.nextLine();
        System.out.print("Tall aa plusse: ");
        objekt.gange(5);
        System.out.println("Ditt tall: " + objekt.hentSvar());

        String gange = scanner.nextLine();
        System.out.print("Tall aa gange: ");
        objekt.gange(4);
        System.out.println("Ditt tall: " + objekt.hentSvar());

        String dele = scanner.nextLine();
        System.out.print("Tall aa dele: ");
        objekt.gange(10);
        System.out.println("Ditt tall: " + objekt.hentSvar());
    }
}