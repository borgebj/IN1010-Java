import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Legesystem lege = new Legesystem("storEksempelFil.txt");


        lege.skrivResepter();

    }
}