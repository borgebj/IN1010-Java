
class metodeTest {
    public static void main(String[] args) {

        metode test = new metode();

        String navn = "Espen Askeladd";

        // sjekker om Espen Askeladd er med
        System.out.println(test.finnHund(navn));
        // false

        // legger til Jonas Helgerud alder 25
        test.add("Jonas Helgerud", 25);

        // sjekker om Espen Askeladd er med
        System.out.println(test.finnHund(navn));
        // false

        // legger til Espen Askeladd alder 19
        test.add("Espen Askeladd", 19);

        // sjekker om Espen Askeladd er med
        System.out.println(test.finnHund(navn));
        // true
    }
}