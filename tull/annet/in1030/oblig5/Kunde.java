import java.util.ArrayList;

public class Kunde
{
    ArrayList<Billett> mineBilletter;
    int kundeNr;
    static int kundeCounter;
    String navn;
    int alder;
    String email;
    int tlf;

    public Kunde(String navn, int alder, String email, int tlf) {
        this.navn = navn;
        this.alder = alder;
        this.email = email;
        this.tlf = tlf;
        this.kundeNr = kundeCounter++;
    }

    public void leggTilBillet(Billet b) {
        mineBilletter.add(b.registrer());
    }

}