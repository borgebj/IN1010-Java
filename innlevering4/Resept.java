
abstract class Resept {

    // globale verdier - alt et resept skal inneholde
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int reit;
    protected Pasient pasient;

    // unik ID for resepten
    protected static int idCount = 0;
    protected int id = 0;


    // Konstruktoer - lager globale variabler med verdier fra parameter
    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.reit = reit;
        this.pasient = pasient;
        id = idCount++;
    }


    // returner id fra resepten
    public int hentId() {
        return id;
    }

    // returner legemiddel-objekt
    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    // returne lege-objekt
    public Lege hentLege() {
        return utskrivendeLege;
    }

    // returnerer pasient-objekt
    public Pasient hentPasient() {
        return pasient;
    }

    // returner antall reit i resepten
    public int hentReit() {
        return reit;
    }

    // "bruk" metode som fjerner antall reit for hver bruk
    public boolean bruk() {
        if (reit > 0) {
            reit--;
            return true;
        } else {
            return false;
        }
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
            return (id + ": [narkotisk] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+") ");
        }
        else if (legemiddel instanceof Vanedannende) {
            return (id + ": [vanedannende] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+" ) ");
        }
        else if (legemiddel instanceof Vanlig) {
            return (id + ": [vanlig] - " + Loutput + " ("+reit+" reit) " + " ("+Poutput+") ");
        }
        return (id + ": " + Loutput + " ("+reit+" reit) " + " ("+Poutput+") ");
    }

    // overrides i subklassene
    abstract public String farge();

    // overrides i subklassene
    abstract public double prisAaBetale();
}