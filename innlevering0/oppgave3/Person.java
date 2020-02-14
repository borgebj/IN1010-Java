
public class Hund {

    private Bil3 bil;
    private String navn;

    // main-metode tar inn objekt av Bil3-typen og navn av String-typen
    public Hund(String HundNavn, Bil3 nr) {
        bil = nr;
        navn = HundNavn;
    }

    public void skrivBilNummer() {
        System.out.println("Bilnummeret til "+navn+" er: "+bil.hentNummer());
    }
}