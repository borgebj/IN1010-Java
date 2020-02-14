import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Linjenummer {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("- - - - - - - - - - - - - - - -");

        // objekt av type fil med navn minFil og tekstfil.txt som parameter
        File minFil = new File("tekstfil.txt");

        // scanner-objekt av typen fil med navn scanner og minFil ^(ovenfor) som parameter
        Scanner scanner = new Scanner(minFil);

        int i = 0;

        while (scanner.hasNext()) {
            i++;
            System.out.println("# "+i+": "+scanner.nextLine());
        }

        System.out.println("- - - - - - - - - - - - - - - -");


    }
}