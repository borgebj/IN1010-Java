import java.util.Scanner;

class Differanse {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Skriv tall 1: ");
        int x = scanner.nextInt();

        System.out.print("Skriv tall 2: ");
        int y = scanner.nextInt();

        int differanse = 0;

        if (x > y) { differanse = x - y; }
        if (y > x) { differanse = y - x; }

        System.out.println("Differansen mellom X og Y er: " + differanse);
    }}