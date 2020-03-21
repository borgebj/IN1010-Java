
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        if (Integer.parseInt(args[0]) == 1) {
            Legesystem lege = new Legesystem("LitenEksempelFil.txt");
            lege.skrivResepter();
        }
        else {
            Legesystem lege = new Legesystem("StorEksempelFil.txt");
            lege.skrivResepter();
        }
    }
}