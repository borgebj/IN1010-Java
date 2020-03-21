
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
        return ("      [ " + navn + " ]  \n\n" +
                "Navn: " + navn + "\n" +
                "Pris: " + pris + " kr\n" +
                "Mengde: " + virkestoff + " mg." + "\n" +
                "ID: " + id + "\n" +
                "Styrke: " + styrke + "");
    }
}