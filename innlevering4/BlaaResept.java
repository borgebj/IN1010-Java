
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
        } return (pris * 0.25);
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
            return (id + ": [Blaa|narkotisk] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+") ");
        }
        else if (legemiddel instanceof Vanedannende) {
            return (id + ": [Blaa|vanedannende] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+" ) ");
        }
        return (id + ": [Blaa|vanlig] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+") ");
    }

}