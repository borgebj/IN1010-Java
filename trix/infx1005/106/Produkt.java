import java.util.Scanner;

class Produkt {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Skriv tall 1: ");
        int x = scanner.nextInt();

        System.out.print("Skriv inn tall 2: ");
        int y = scanner.nextInt();

        int produkt = x * y;

        System.out.println("Produktet av x og y er: " + produkt);
    }}