
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.InterruptedException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class testProgram {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        String filnavn = null;

        if (args.length > 0) {
            filnavn = args[0];
        } else {
            System.out.println("FEIL! Riktig bruk av programmet: "
                    +"java Oblig5 <labyrintfil>");
            return;
        }
        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }


        System.out.println("\n");

        ///////////////////////////////////////////////////////////
        System.out.println("Tester toString()");

        // tester toString-metoden
        System.out.println( l );

        System.out.println("\n");
        TimeUnit.SECONDS.sleep(2);


        ///////////////////////////////////////////////////////////
        System.out.println("Tester finnAapninger()");

        // finner aapninger til brettet
        l.finnApninger();

        System.out.println("\n");
        TimeUnit.SECONDS.sleep(2);


        ///////////////////////////////////////////////////////////
        System.out.println("Tester skrivMedTall()");

        l.skrivMedTall();

        System.out.println("\n");
        TimeUnit.SECONDS.sleep(2);


        ///////////////////////////////////////////////////////////
        System.out.println("Tester finnNaboer()");

        System.out.print("Skriv inn koordinater <kolonne> <rad> \n > ");
        String[] linje = scanner.nextLine().split(" ");

        int kol = Integer.parseInt(linje[0]);
        int rad = Integer.parseInt(linje[1]);

        l.finnNaboer(kol, rad);
        System.out.println("\n");
        TimeUnit.SECONDS.sleep(2);


        ///////////////////////////////////////////////////////////
        System.out.println("Tester finnUtveiFra()");

        Liste<String> utveier = l.finnUtveiFra(kol, rad);
        if (utveier.stoerrelse() != 0) {
            for (String s : utveier) {
                System.out.println(s);
            }
        } else {
            System.out.println("Ingen utveier.");
        }

        System.out.println("\n");
        TimeUnit.SECONDS.sleep(2);


        ///////////////////////////////////////////////////////////
        System.out.println("Tester toString()");

        // tester toString-metoden
        System.out.println( l );

        System.out.println("\n");
        TimeUnit.SECONDS.sleep(2);


        ///////////////////////////////////////////////////////////
        System.out.println("Tester finnKortestUtvei()");

        System.out.println( l.finnKortestUtvei() );

        System.out.println("\n");
        TimeUnit.SECONDS.sleep(2);
    }
}