import java.util.Scanner;

class Produkt {
    public static void main(String[] args) {

        // Scanner/input objekt med navn "scanner" av typen Scanner
        Scanner scanner = new Scanner(System.in);

        // spor om tall og tar inn input med navn "tall1" av typen Integer
        System.out.print("Skriv inn tall 1: ");
        int tall1 = scanner.nextInt();

        // kan ogsa skrive f.eks.
        // int tall1;
        // tall1 = Integer.parseInt(scanner.nextLine());

        // spor om tall og tar inn input med navn "tall" av typen Integer
        System.out.print("Skriv inn tall 2: ");
        int tall2 = scanner.nextInt();

        //tom variabel som kan bli brukt utenfor if-setningen (navn "sum"), typen Integer)
        int sum;

        // if som finner om tall1 eller tall2 er storst, og finner ut sum avhengig av resultat
        if (tall1 > tall2) {
            sum = tall1 * tall2;
        } else {
            sum = tall2 * tall1;
        }

        //printer ut differansen mellom de to tallene
        System.out.println("Produktet av " + tall1 + " og " + tall2 + " er " + sum);

        //if som sjekker om sum er mindre enn null (negativt) ellers er den positiv (over 0)
        if (sum < 0) {
            System.out.println("Tallet er negativt");
        } else {
            System.out.println("Tallet er positivt");
        }
    }
}


// Samme kode som 201, bare * istedenfor +