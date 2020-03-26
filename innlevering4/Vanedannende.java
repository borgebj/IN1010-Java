
public class Vanedannende extends Legemiddel {

    private int styrke;


    public Vanedannende(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentVanedannendeStyrke() {
        return styrke;
    }

    @Override // A4 - toString-metode som returner relevant info med 1 metode
    public String toString() {

        // forkorter til 2 decimaler vha Math.round()
        double nyPris = Math.round(pris * 100.00) / 100.00;
        double nyVirkestoff = Math.round(virkestoff * 100.00) / 100.00;
        return (id + ": " + "[VANEDANNENDE] - " + navn + " ("+nyPris+" kr) " + " ("+nyVirkestoff+" mg)" +  " ("+styrke+" / 10) ");
    }
}