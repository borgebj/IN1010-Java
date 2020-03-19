
// oving paa "Ternary operaor"

class ternary {

    // ternary / if med variabel
    static void ternaryA() {
        System.out.println("-------------------------------------------------");

        // antall linjer: 7
        System.out.println("Med if:");
        int tidA = 18;
        String msgA = "";

        if (tidA < 12) {
            msgA = "God morgen (A)";
        } else {
            msgA = "God dag (A)";
        }
        System.out.println(msgA);

        System.out.println("-------------------------------------------------");

        // antall linjer: 3
        System.out.println("Med ternary:");
        int tidB = 18;
        final String msgB = (tidB < 12) ? "God morgen (A)" : "God dag (A)";
        System.out.println(msgB);

        System.out.println("-------------------------------------------------");
    }

    // ternary / if med System.out.println
    static void ternaryB() {
        System.out.println("-------------------------------------------------");

        // antall linjer: 5
        System.out.println("Med if:");
        int tidA = 18;

        if (tidA < 12) {
            System.out.println("God morgen (B)");
        } else {
            System.out.println("God dag (B)");
        }

        System.out.println("-------------------------------------------------");

        // antall linjer: 2
        System.out.println("Med ternary:");
        int tidB = 18;
        System.out.println( (tidB < 12) ? "God morning (B)" : "God dag (B)");

        System.out.println("-------------------------------------------------");
    }

    // kaller paa enten A eller B basert paa compile-input
    public static void main(String[] args) {

        int tall = Integer.parseInt(args[0]);

        // kaller metode avhengig av hva tall er
        switch(tall) {
            case(1): ternaryA(); break;
            case(2): ternaryB(); break;
            default:
                System.out.println("- " + tall + " er ikke gyldig.");
                System.out.println("> Skriv enten 1 eller 2");
        }
    }
}