
public class HvitResept extends Resept {

    // Del B b) - Endrer "int pasientid" til "Pasient pasient"
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    // returner fargen til klassen
    public String farge() {
        return ("Hvit");
    }

    // returnerer prisen paa legemiddelet
    public double prisAaBetale() {
        double pris = legemiddel.hentPris();
        if (pris < 0) {
            pris = 0;
        } return pris;
    }

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
        return (id + ": [Hvit|vanlig] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+") ");
    }
}