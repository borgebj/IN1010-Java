import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Hovedprogram {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        File fil = new File(args[0]);
        Skattekart kart = Skattekart.lesInn(fil);

        System.out.println();
        kart.skrivUt();
        System.out.println();

        try {
            System.out.print("Finn skatten! Gjett koordinater paa formatet (rad kol) (a for aa avslutte): \n > ");
            String input = scanner.nextLine().trim().toLowerCase();

            while (!input.equals("a")) {

                String[] tall = input.split(" ");
                int rad = Integer.parseInt(tall[0]);
                int kol = Integer.parseInt(tall[1]);

                boolean funnet = kart.sjekk(rad-1, kol-1);
                if (funnet) {
                    System.out.println("\nSkatt er funnet!");
                    input = "a";
                    kart.skrivUt();
                    System.out.println();
                }
                else  {
                    System.out.println();
                    kart.skrivUt();

                    System.out.print("\nIngen skatt der! Gjett igjen: \n > ");
                    input = scanner.nextLine().trim().toLowerCase();
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nrad og kol er ugyldige\n");
        }
    }
}