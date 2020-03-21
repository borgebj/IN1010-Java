
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// fil for testing av legesystem! Midlertidig (?)

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nHva onsker du aa skrive ut? (Pasienter/Leger/Legemidler/Resepter/a): ");
        String kommando = scanner.nextLine().toLowerCase().trim(); System.out.println();
        TimeUnit.MILLISECONDS.sleep(750);


        if (Integer.parseInt(args[0])==1) {
            Legesystem lege = new Legesystem("LitenEksempelFil.txt");

            while (!kommando.equals("a")) {
                switch (kommando) {
                    case "pasienter":
                        lege.skrivPasienter();
                        break;
                    case "leger":
                        lege.skrivLeger();
                        break;
                    case "legemidler":
                        lege.skrivLegemidler();
                        break;
                    case "resepter":
                        lege.skrivResepter();
                        break;
                    default:
                        System.out.println("\nUgyldig kommando - prov igjen\n");
                }
                System.out.print("Hva onsker du aa skrive ut? (Pasienter/Leger/Legemidler/Resepter/a): ");
                kommando = scanner.nextLine().toLowerCase().trim(); System.out.println();
                TimeUnit.MILLISECONDS.sleep(750);
            }
        }

        else if (Integer.parseInt(args[0])==2) {
            Legesystem lege = new Legesystem("StorEksempelFil.txt");

            while (!kommando.equals("a")) {
                switch (kommando) {
                    case "pasienter":
                        lege.skrivPasienter();
                        break;
                    case "leger":
                        lege.skrivLeger();
                        break;
                    case "legemidler":
                        lege.skrivLegemidler();
                        break;
                    case "resepter":
                        lege.skrivResepter();
                        break;
                    default:
                        System.out.println("\nUgyldig kommando - prov igjen\n");
                }
                System.out.print("Hva onsker du aa skrive ut? (Pasienter/Leger/Legemidler/Resepter/a): ");
                kommando = scanner.nextLine().toLowerCase().trim(); System.out.println();
                TimeUnit.MILLISECONDS.sleep(750);
            }
        }


        System.out.println("Avslutter ...\n");
        TimeUnit.MILLISECONDS.sleep(1200); System.out.println();

    }
}
