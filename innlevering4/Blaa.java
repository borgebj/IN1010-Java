
public class Blaa extends Resept {

    public Blaa(Legemiddel legemiddel, Lege utskrivendeLege, int pasientid, int reit) {
        super(legemiddel, utskrivendeLege, pasientid, reit);
    }

    // returner fargen til klassen
    public String farge() {
        return ("Blaa");
    }

    // returnerer prisen paa legemiddelet
    public double prisAaBetale() {
        double pris = middel.hentPris();
        if (pris < 0) {
            pris = 0;
        } return ( pris * 0.25);
    }
}