
class Hanoi {
    public static void main(String[] args) {
        int tall = Integer.parseInt(args[0]);
        hanoi(tall, "A", "B", "C");
    }

    static void hanoi(int antall, String fra, String via, String til) {

        if (antall == 0) {
            return;
        }

        hanoi(antall-1, fra, til, via);

        System.out.println("Flytter disk " + fra + " til " + til);

        hanoi(antall-1, via, fra, til);
    }
}