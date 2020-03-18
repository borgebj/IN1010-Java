
public class BlaaResept extends Resept {

    // Del B b) - Endrer "int pasientid" til "Pasient pasient"
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    // returner fargen til klassen
    public String farge() {
        return ("Blaa");
    }

    // returnerer prisen paa legemiddelet
    public double prisAaBetale() {
        double pris = legemiddel.hentPris();
        if (pris < 0) {
            pris = 0;
        } return ( pris * 0.25);
    }
}