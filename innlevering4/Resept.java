package innlevering4;

abstract class Resept {

    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int reit;

    // id-teller - okes for hvert objekt
    protected static int idCount = 0;

    // id for et objekt
    protected int id = 0;

    // Del B b) - Endrer "int pasId" til "Pasient pasient"
    protected Pasient pasient;

    // Del B b) - Endrer "int pasientid" til "Pasient pasient"
    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.reit = reit;
        this.pasient = pasient;
        id = idCount++;
    }

    public int hentId() {
        return id;
    }

    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    // fungerer ikke lenger
    /* public int hentPasientId() {
        return pasient;
    } */

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0) {
            reit--;
            return true;
        } else {
            return false;
        }
    }

    // overrides i subklassene
    abstract public String farge();

    // overrides i subklassene
    abstract public double prisAaBetale();

    @Override // overskriver original toString() metode med kode som returnerer variablene med tilsvarende tekst
    public String toString() {
        return (legemiddel + "\n" + utskrivendeLege +
                "\nPasient: " + pasient +
                "\nResept-ID: " + id +
                "\nAntall reit: " + reit);
    }
}