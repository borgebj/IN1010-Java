import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class TemperaturLeser {
    public static void main(String[] args)
            throws FileNotFoundException{

        // array av typen String med navn array og lengde 12
        String[] array = new String[12];

        // objet av typen File med navn minFil og parameter Temperatur.txt (fil)
        File minFil = new File("Temperatur.txt");

        // objekt av typen Scanner med navn scanner og minFil objekt som parameter
        Scanner scanner = new Scanner(minFil);


        int teller = 0;
        while (scanner.hasNextLine()) {
            array[teller] = scanner.nextLine();
            teller++;
            }

        for (String var : array) {
            System.out.println(var);
        }
        }
    }
