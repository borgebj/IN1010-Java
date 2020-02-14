
class Utskrift {
    // heter utskrift, krever String
    public static void utskrift(String tekst) {
        System.out.println(tekst);
    }

    // heter det samme, krever integer
    public static void utskrift(int heltall) {
        System.out.println(heltall);
    }
    // svar: Ja

    // endres til:
    public static void utskrift(int a, int b) {
        System.out.println("Sum: "+(a+b));
    }
}