
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
            return (id + ": [Militaer|narkotisk] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+") ");
        }
        else if (legemiddel instanceof Vanedannende) {
            return (id + ": [Militaer|vanedannende] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+" ) ");
        }
        return (id + ": [Militaer|vanlig] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+") ");
    }
}