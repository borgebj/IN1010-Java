import java.util.ArrayList;

public class Kino {
    String kinoNavn;
    ArrayList<Billett> mineBilletter;

    public Kino (String navn, ) {
        this.kinoNavn = navn;
    }

    public int finnLedigeBilletter() {
        int antall = 0;
        for (Billett b : mineBilletter) {
            if (b.erLedig) antall++
        } return antall;
    }

    public Billett[] hentBillettType() {
        Billett[] ledige = new Billett[finnLedigeBilletter()];
        for (int i=0; i < ledige.length; i++) {
            if (mineBilletter.get(i).erLedig) ledige[i].add(mineBilletter.get(i));
        } return ledige;
    }

    public String hentNavn() {
        return kinoNavn;
    }
}