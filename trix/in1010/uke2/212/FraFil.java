import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class FraFil {
    public static void main(String[] args)
            throws FileNotFoundException {

        // objekt av typen File med navn minFil og parameter tekst.txt (fil)
        File minFil = new File("tekst.txt");

        // objekt av typen Scanner med navn scanner og parameter filen ovenfor
        Scanner scanner = new Scanner(minFil);

        // mens scanner ^ .hasNextLine() <- gir True/False om filen har ny linje
        while (scanner.hasNextLine()) {
            // variabel ord av typen String blir gjort om til filens neste token/ord
            String ord = scanner.next();
            // printer ut ordet
            System.out.println(ord);
        }
    }
}