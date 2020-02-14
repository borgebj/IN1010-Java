import java.util.Scanner;

class Differanse {
    public static void main(String[] args) {

        // Scanner/input objekt med navn "scanner" av typen Scanner
        Scanner scanner = new Scanner(System.in);

        // spor om tall og tar inn input med navn "tall1" av typen Integer
        System.out.print("Skriv inn tall 1: ");
        int tall1 = scanner.nextInt();

        // spor om tall og tar inn input med navn "tall" av typen Integer
        System.out.print("Skriv inn tall 2: ");
        int tall2 = scanner.nextInt();

        //tom variabel som kan bli brukt utenfor if-setningen (navn "sum"), typen Integer)
        int sum;

        // if som finner om tall1 eller tall2 er storst, og finner ut sum avhengig av resultat
        if (tall1>tall2) {sum = tall1-tall2;}
        else {sum = tall2-tall1;}

        //printer ut differansen mellom de to tallene
        System.out.println("Differansen mellom "+tall1+" og "+tall2+" er "+sum);
    }
}

