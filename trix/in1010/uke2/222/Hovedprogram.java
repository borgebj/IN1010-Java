import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Hovedprogram {
    public static void main(String[] args)
            throws FileNotFoundException {

        // arraylist for hunder
        ArrayList<Hund> hunder = new ArrayList<Hund>();

        // arraylist for personer
        ArrayList<Person> personer = new ArrayList<Person>();

        // bruker File-objekt for aa lage variabel som inneholder fil
        File minFil = new File("navn.txt");

        // bruker fil-variablen sammen med scanner-objekt
        Scanner scanner = new Scanner(minFil);

        // mens filen har en token
        while (scanner.hasNext()) {
            String[] biter = scanner.nextLine().split(" ");

            if (biter[0].equals("P")) {
                String navn = biter[1];
                personer.add(new Person(navn));
            } else if (biter[0].equals("H")) {
                String nan = biter[1];
                hunder.add(new Hund(nan));
            }
        }

        System.out.println("Personer:");
        for(Person var : personer) {
            System.out.println(var.hentNavn());
        }
        System.out.println();
        System.out.println("Hunder:");
        for (Hund ver : hunder) {
            System.out.println(ver.hentNavn());
        }
    }
}