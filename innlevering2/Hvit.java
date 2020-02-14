
public class Hvit extends Resept {

    public Hvit(Legemiddel legemiddel, Lege utskrivendeLege, int pasientid, int reit) {
        super(legemiddel, utskrivendeLege, pasientid, reit);
    }

    public String farge() {
        return ("Hvit");
    }

    public double prisAaBetale() {
        double pris = middel.hentPris();
        if (pris < 0) {
            pris = 0;
        } return pris;
    }
}
