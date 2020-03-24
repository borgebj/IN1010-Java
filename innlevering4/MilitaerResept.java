
public class MilitaerResept extends HvitResept {

    // Del B b) - Endrer "int pasientid" til "Pasient pasient"
    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override // returner fargen til klassen
    public String farge() {
        return ("Hvit");
    }

    @Override // returnerer prisen paa legemiddelet, som er 100% rabatt, altsaa 0.
    public double prisAaBetale() {
        return legemiddel.hentPris() * 0;
    }

}

