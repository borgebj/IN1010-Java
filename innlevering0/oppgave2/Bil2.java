

public class Bil2 {

    // privat instansvariabel bilNummer av typen String
    private String bilNummer;

    // main-metode som tar inn string parameter "nr"
    public Bil2(String nr){
        bilNummer = nr;
    }

    // skriv-metode som skriver ut bilnummeret
    public void skriv(){
        System.out.println("Bilnummer: " + bilNummer);
    }
}