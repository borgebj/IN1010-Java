
public class PResept extends HvitResept {

    // Del B b) - Endrer "int pasientid" til "Pasient pasient"
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, 3);
    }

    @Override // returner fargen til klassen
    public String farge() {
        return ("Hvit");
    }

    @Override // returnerer prisen paa legemiddelet, som har en statisk rabatt paa 108kr
    public double prisAaBetale() {
        double pris = legemiddel.hentPris();
        pris = pris-108;
        if (pris < 0) {
            pris = 0;
        } return pris;
    }
}