package annet;

import java.util.Scanner;


class Curser {

    public static void main(String[] args) {

        int maksTimer = 4;
        int currentTimer = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nVil du starte webm? ");

        String inp = scanner.nextLine();

        while (inp.equals("ja")) {

            try {
                Thread.sleep(800);
                System.out.println();
                System.out.println("Current time: "+currentTimer);
                System.out.println("Maks time:    "+maksTimer);
                currentTimer++;
                maksTimer++;
            }
            catch (InterruptedException e) {}
        }
        System.out.println();
    }
}