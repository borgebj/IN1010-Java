import java.io.File;
import java.io.FileNotFoundException;

class testProgram {
    public static void main(String[] args) throws FileNotFoundException {

        Labyrint labyrint = new Labyrint();
        File fil = new File("2.in");

        labyrint.lesFraFil(fil);
    }
}