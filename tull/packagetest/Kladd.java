import en.*;
import java.util.*;

public class Kladd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Tall2: ");
        int x = scanner.nextInt();

        Kalkulator kladd = new Kalkulator(x);

        System.out.print("Tall2: ");
        int y = scanner.nextInt();

        int sum = kladd.regn(y);
        System.out.println("Produkt: "+sum);
    }
}

// er ikke sikker hvor man starter når man skriver import
// java.util er kort liksom