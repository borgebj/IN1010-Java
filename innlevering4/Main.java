import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Legesystem lege = new Legesystem("litenEksempelFil.txt");


        lege.skrivResepter();

    }
}