
public class PResept extends Hvit {

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientid) {
        super(legemiddel, utskrivendeLege, pasientid, 3);
    }

    @Override // returner fargen til klassen
    public String farge() {
        return ("Hvit");
    }

    @Override // returnerer prisen paa legemiddelet, som har en statisk rabatt paa 108kr
    public double prisAaBetale() {
        double pris = middel.hentPris();
        if (pris < 0) {
            pris = 0;
        } return (pris - 108);
    }
}