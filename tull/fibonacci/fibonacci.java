import java.util.Scanner;
import java.util.InputMismatchException;

class fibonacci {

    public static void main(String[] args) throws InputMismatchException {

        // Hvis a starter som 1, vil det tislvare at C er variabelen aa printe ut
        int a = 1; // naa
        int b = 0; // neste
        int c = 0; // sum

        Scanner scanner = new Scanner(System.in);
        System.out.print("Fibbo-lengde: ");

        // bruker samtidig en try-catch for aa prove input og sjekke om det er av typen Integer
        try {
            int input = scanner.nextInt();

            for (int i = 0; i < input; i++) {
                System.out.println(i + 1 + " : " + c);
                c = a + b;
                a = b;
                b = c;
            }
        } catch (InputMismatchException e) { // ellers vil feilemdling komme
            System.out.println("\nInput er ikke integer");
        }
    }
}


//eksempel for forklaring og forstaaelse

// a:1 b:0 c:0 -> 0
// c = a + b = 1 + 0 = 1
// a = b = 0
// b = c = 1

//a:0 b:1 c:1 -> 1
// c = a + b = 0 + 1 = 1
// a = b = 1
// b = c = 1

//a:1 b:1 c:1 -> 1
// c = a + b = 1 + 1 = 2
// a = b = 1
// b = c = 2

//a:1 b:2 c:2 -> 2
// c = a + b = 1 + 2 = 3
// a = b = 2
// b = c = 3

//a:2 b:3 c:3 -> 3
// osv ...