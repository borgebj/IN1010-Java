
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
<<<<<<< Updated upstream
}
=======

    @Override // overskriver original toString() metode med kode som returnerer variablene med tilsvarende tekst
    public String toString() {

        // stor forbokstav paa Lege
        String Pnavn = pasient.hentNavn();
        String Poutput = Pnavn.substring(0, 1).toUpperCase() + Pnavn.substring(1);

        // stor forbokstav paa Pasient
        String Lnavn = legemiddel.hentNavn();
        String Loutput = Lnavn.substring(0, 1).toUpperCase() + Lnavn.substring(1);

        // sjekker middel-typen
        if (legemiddel instanceof Narkotisk) {
            return (id + ": [Hvit|narkotisk] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+") ");
        }
        else if (legemiddel instanceof Vanedannende) {
            return (id + ": [Hvit|vanedannende] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+" ) ");
        }
        else if (legemiddel instanceof Vanlig) {
            return (id + ": [Hvit|vanlig] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+") ");
        }
        return (id + ": " + Loutput + " ("+reit+" reit) " + " ("+Poutput+") ");
    }
}
>>>>>>> Stashed changes
