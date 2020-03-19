
class Hanoi {
    static void flytt(int n, char fra, char via, char til) {
        if (n == 1) {

            // det trivielle tilfellet: flytt 1 ring direkte
            System.out.println("Flytt " + fra + " til " + til);
        } else {
            // det rekursive tilfellet:

            // flytt de små ringene til hjelpestolpen
            flytt(n - 1, fra, til, via);

            //flytt den største ringen fra "fra-stolpen" til "til-stolpen"
            flytt(1, fra, via, til);

            // flytt ringene fra hjelpestolpen til "til-stolpen" igjen
            flytt(n-1, via, fra, til);
        }
    }

    public static void main(String[] args) {
        int antall = Integer.parseInt(args[0]);
        flytt(antall, 'A', 'B', 'C');
    }
}