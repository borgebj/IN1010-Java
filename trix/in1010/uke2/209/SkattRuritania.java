import java.util.Scanner;

class SkattRuritania {
    public static void main(String[] args) {

        double skatt = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Skriv inn inntekt: ");
        double inntekt = scanner.nextFloat();

        if (inntekt < 10000) {
            skatt = 10000/10;
        } else if (inntekt >= 10000) {
            skatt = (10000*0.1) + (inntekt-10000)*0.3;
        }

        System.out.println("Du skal betale "+skatt+" i skatter.");
    }
}