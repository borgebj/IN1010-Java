package packagetest;

import pasta.Kalkulator;

import java.util.*;

public class Kladd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Tall2: ");
        int x = scanner.nextInt();

        Kalkulator kladd = new Kalkulator(x);

        System.out.print("Tall2: ");
        int y = scanner.nextInt();

        kladd.gange(y);

        int sum = kladd.regn();
        System.out.println("Produkt: "+sum);
    }
}