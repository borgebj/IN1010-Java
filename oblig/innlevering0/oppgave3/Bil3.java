package innlevering0.oppgave3;

public class Bil3 {

    private String bilNummer;

    public Bil3(String nr){
        bilNummer = nr;
    }

    public void skriv(){
        System.out.println("Bilnummer: " + bilNummer);
    }

    public String hentNummer() {
        return bilNummer;
    }
}