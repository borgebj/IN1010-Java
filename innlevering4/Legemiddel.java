
abstract class Legemiddel {

    // instansvariabler til legemiddel
    protected String navn;
    protected double pris;
    protected double virkestoff;

    // id-teller - okes for hvert objekt
    protected static int idCount = 0;

    // id for et objekt
    protected int id = 0;

    // konstruktor som gir instansvariabler verider fra parameter
    public Legemiddel(String navn, double pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this. virkestoff = virkestoff;
        id = idCount;
        idCount++;
    }

    // returnerer ID'en
    public int hentId() {
        return id;
    }

    // returnerer navnet
    public String hentNavn() {
        return navn;
    }

    // returnerer prisen
    public double hentPris() {
        return pris;
    }

    // returnerer mengden virkestoff
    public double hentVirkestoff() {
        return virkestoff;
    }

    // gir instansvariablen pris ny verdi fra parameter
    public void settNyPris(double nyPris) {
        pris = nyPris;
    }

    @Override // A4 - toString-metode som returner relevant info med 1 metode
    public String toString() {
        return ("      [ " + navn + " ]  \n\n" +
                "Navn: " + navn + "\n" +
                "Pris: " + pris + " kr\n" +
                "Mengde: " + virkestoff + " mg." + "\n" +
                "ID: " + id);
    }
}
