
class Rekursjon {
    public static void main(String[] args) {
        rekursjon(5);
    }

    static void rekursjon(int tall) {

        // om tall er 0, stoppes rekursjonen
        if (tall == 0)
            return;

        // teller opp
        System.out.println(tall);

        rekursjon(tall-1);

        // teller ned
        System.out.println(tall);
    }
}