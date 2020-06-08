
public class Gjenstand {

    String navn;
    int verdi;

    public Gjenstand(String navn, int verdi) {
        this.navn = navn;
        this.verdi = verdi;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentVerdi() {
        return verdi;
    }

    @Override
    public String toString() {
        return navn + "[" + verdi + "kr]";
    }
}