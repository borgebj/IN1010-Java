
public class Vanedannende extends Legemiddel {

    private int styrke;

    public Vanedannende(String midNavn, double midPris, double midVirk, int midStyrke) {
        super(midNavn, midPris, midVirk);
        styrke = midStyrke;
    }

    public int hentVanedannendeStyrke() {
        return styrke;
    }
}