
import java.io.FileNotFoundException;

// fil for testing av legesystem! Midlertidig (?)

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        if (Integer.parseInt(args[0])==1) {
            Legesystem lege = new Legesystem("LitenEksempelFil.txt");
            lege.skrivResepter();
        }

        else if (Integer.parseInt(args[0])==2) {
            Legesystem lege = new Legesystem("StorEksempelFil.txt");
            lege.skrivResepter();
        }
    }
}
