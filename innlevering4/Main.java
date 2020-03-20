package innlevering4;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Legesystem lege = new Legesystem("LitenEksempelFil.txt");


        lege.skrivPasienter(); System.out.println("\n");
        lege.skrivLegemidler(); System.out.println("\n");
        lege.skrivLeger(); System.out.println("\n\n");

        lege.skrivResepter(); System.out.println("\n");

    }
}