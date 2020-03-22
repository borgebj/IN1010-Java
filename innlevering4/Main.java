
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.PrintWriter;

// fil for testing av legesystem! Midlertidig (?)

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nHva onsker du aa skrive ut? (Pasienter/Leger/Legemidler/Resepter/a): ");
        String kommando = scanner.nextLine().toLowerCase().trim(); System.out.println();
        TimeUnit.MILLISECONDS.sleep(750);


        if (Integer.parseInt(args[0])==1) {
            System.out.println("\n\n - Liten eksempelfil - \n\n");

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
            System.out.println("\n\n - Stor eksempelfil - \n\n");

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

        else if (Integer.parseInt(args[0])==3) {
            System.out.println("\n\n - Test-eksempler - \n\n");

            PrintWriter writer = new PrintWriter("error.txt");
            int[] i = new int[1];
            try {
                i[10] = 2;
            } catch (Exception e) {
                writer.format("%5s - %-8s", "allah", "linje");
                writer.write("\n");
                writer.format("%5s - %-8s", "wallah", "linje");
                writer.write("\n");
                writer.format("%5s - %-8s", e.toString(), "linje");

                writer.close();
            }

        }
        System.out.println("Avslutter ...\n");
        TimeUnit.MILLISECONDS.sleep(1200); System.out.println();
    }
}
