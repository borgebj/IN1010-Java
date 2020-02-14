import java.util.*;

class Input {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hva er 25 + 25: ");
        int inp = scanner.nextInt();

        if (inp == 50) { System.out.println("Det er riktig!"); }
        else { System.out.println("Det er feil"); }
}}