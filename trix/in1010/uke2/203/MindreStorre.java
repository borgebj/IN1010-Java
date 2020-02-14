import java.util.Scanner;

class MindreStorre {
    public static void main(String[] args) {
        // scanner/input objekt med navn "scanner"
        Scanner scanner = new Scanner(System.in);

        // tar input fra bruker lagret i variabel "tall"
        System.out.print("Skriv inn tall1: ");
        int tall = scanner.nextInt();

        // if og else som sjekker om tallet er 10/20, under 10, mellom 10 og 20 eller over 20
        if (tall<10){
            System.out.println("Tallet er mindre enn 10");
        } else if (tall>10 && tall<20) {
            System.out.println("Tallet er mellom 10 og 20");
        } else if (tall>20) {
            System.out.println("Tallet er over 20");
        } // egen test
        else if (tall==10||tall==20) {
            System.out.println("Tallet er "+tall); }
    }
}