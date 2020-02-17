
public class Narkotisk extends Legemiddel {

    private int styrke;

    public Narkotisk(String midNavn, double midPris, double midVirk, int midStyrke) {
        super(midNavn, midPris, midVirk);
        styrke = midStyrke;
    }

    public int hentNarkotiskStyrke() {
        return styrke;
    }
}
