import java.util.Scanner;

class KoProgram {
    public static void main(String[] args) {

        System.out.println("----------------------------------------------");

        KoSystem system = new KoSystem();
        Scanner scanner = new Scanner(System.in);

        int svar = 0;
        while (svar != 4) {
            System.out.println();
            System.out.println("**MENY FOR BILLETTSYSTEM**");
            System.out.println("1 - Trekk ny kolapp.");
            System.out.println("2 - Betjen kunde.");
            System.out.println("3 - Print antall kunder i kø");
            System.out.println("4 - Avslutt.");

            System.out.print("\n> ");
            svar = Integer.parseInt(scanner.nextLine());


            if (svar == 1) {
                system.trekkKoLapp();
            } else if (svar == 2) {
                system.betjenKunde();
            } else if (svar == 3) {
                system.printKunderIKo();
            } else if (svar == 4) {
                System.out.println("Programmet avsluttes.");
            } else {
                System.out.println("Feil: tast enten 1, 2, 3 eller 4");
            }
        }
        System.out.println("----------------------------------------------");

    }
}