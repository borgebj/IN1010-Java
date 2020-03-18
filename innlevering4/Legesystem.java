import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Legesystem {

    // lister som holder styr paa pasienter, legemidler, leger og resepter
    Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
    Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
    SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
    Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();

    // konstruktoer - initierer listene
    public Legesystem(String fil) throws FileNotFoundException {
        System.out.println();

        // scanner + fil objekt
        Scanner lesFil = new Scanner(new File(fil));


        while (lesFil.hasNextLine()) {

            // for hver linje som IKKE starter med #

            // lager pasient-objekter
            if (lesFil.nextLine().startsWith("# Pasienter")) {
                while (lesFil.nextLine().startsWith("#")) {
                    System.out.println(lesFil.nextLine());
                }
            }

            // lager legemiddel-objekter
            if (lesFil.nextLine().startsWith("# Legemidler")) {
                System.out.println("legemiddel");
            }

            // lager lege-objekter
            if (lesFil.nextLine().startsWith("# Leger")) {
                System.out.println("lege");
            }

            // lager resept-objekter
            if (lesFil.nextLine().startsWith("# Resepter")) {
                System.out.println("resept");
            }


        }
        System.out.println();
    }
}


/*  - LesFraFil -
* 1. Opprette pasient-objekter
* 2. Opprette legemidler-objekter
* 3. Opprette lege-objekter
* 4. Opprette Resept-objekter
*
*
  location: class Legesystem

* while (lesFil.hasNextLine()){
*   linjer [
 */