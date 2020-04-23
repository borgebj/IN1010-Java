import java.util.Scanner;


class ThreadClock {

    static int tid = 0;
    static boolean toggle = false;


    public static void main(String[] args) {
        System.out.println("> [Stoppeklokke] < \n");
        System.out.print("Trykk <s> for a starte og <return> for a stoppe \n> ");
        Scanner tastatur = new Scanner(System.in);
        String inp = tastatur.nextLine();

        // oppretter og starter tråden
        Thread minTrad = new Thread(new clock());
        minTrad.start();

        // sjekker input
        while (inp.equals("s")) {
            toggle = true;
            inp = tastatur.nextLine();
        }
        toggle = false;
        System.out.println("Current tid: " + tid);
        System.out.println("avsluttet");
    }


    static class clock implements Runnable {
        @Override
        public void run() {

            while (toggle) {
                try {
                    System.out.println("-> "+ ++tid);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
        }
    }
}