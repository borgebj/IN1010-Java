import java.util.HashMap;
import java.util.Scanner;

class Handletur {
    public static void main(String[] args) {

        HashMap<String, Integer> varer = new HashMap<String, Integer>();
        varer.put("Broed", 20);
        varer.put("Melk", 15);
        varer.put("Ost", 40);
        varer.put("Yoghurt", 12);

        System.out.println("Prisene er som folger:");
        for (String var : varer.keySet()) {
            System.out.println(var + ": "+ varer.get(var));
        }

        // objekt av typen Scanner med navn scanner (får bruker input)
        Scanner scanner = new Scanner(System.in);

        // tom variabel av type integer som inneholder totalprisen
        int sum = 0;

        // spor bruker hvor mye av hver vare de vil ha, med kall av Scanner-metoden for å få brukerinp i ulike variabler
        System.out.println("\nHvor mange brod vil du ha?");
        int brod = scanner.nextInt();
        // legger til antall * prisen i sum-variablen
        sum += (brod * varer.get("Broed"));

        System.out.println("HVor mange melk vil du ha?");
        int melk = scanner.nextInt();
        sum += (melk * varer.get("Melk"));

        System.out.println("Hvor mange ost vil du ha?");
        int ost = scanner.nextInt();
        sum += (ost * varer.get("Ost"));

        System.out.println("Hvor mange yoghurt vil du ha?");
        int yoghurt = scanner.nextInt();
        sum += (yoghurt * varer.get("Yoghurt"));

        // skriver ut totalprisen til brukeren
        System.out.println("Du skal betale: "+sum+" kr.");
    }
}