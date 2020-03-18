import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Legesystem {

    public Legesystem (String fil) throws FileNotFoundException {

        Scanner minFil = new Scanner(new File(fil));

        while (minFil.hasNextLine()) {
            System.out.println(minFil.nextLine());
        }
    }

}