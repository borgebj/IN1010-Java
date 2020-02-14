
public class Militaerresept extends Hvit {

    public Militaerresept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientid, int reit) {
        super(legemiddel, utskrivendeLege, pasientid, reit);
    }

    @Override // returner fargen til klassen
    public String farge() {
        return ("Hvit");
    }

    @Override // returnerer prisen paa legemiddelet, som er 100% rabatt, altsaa 0.
    public double prisAaBetale() {
        return middel.hentPris() * 0;
    }
}