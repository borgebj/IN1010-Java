
abstract class Resept {

    protected Legemiddel middel;
    protected Lege lege;
    protected int pasId;
    protected int antallReit;

    // id-teller - okes for hvert objekt
    protected static int idCount = 0;

    // id for et objekt
    protected int id = 0;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientid, int reit){
        middel = legemiddel;
        lege = utskrivendeLege;
        antallReit = reit;
        pasId = pasientid;
        id = idCount;
        idCount++;
    }

    public int hentId() {
        return id;
    }

    public Legemiddel hentLegemiddel(){
        return middel;
    }

    public Lege hentLege() {
        return lege;
    }

    public int hentPasientId() {
        return pasId;
    }

    public int hentReit() {
        return antallReit;
    }

    public boolean bruk() {
        if (antallReit > 0) {
            antallReit--;
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
        return (middel + "\n" + lege +
                "\nPasient-ID: " + pasId +
                "\nResept-ID: " + id +
                "\nAntall reit: " + antallReit);
    }
}