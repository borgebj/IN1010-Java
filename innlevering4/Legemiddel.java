import java.lang.Math.*;

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

        // forkorter til 2 decimaler vha Math.round()
        double nyPris = Math.round(pris * 100.00) / 100.00;
        double nyVirkestoff = Math.round(virkestoff * 100.00) / 100.00;
        return (id + ": " + "[VANLIG] - " + navn + " ("+nyPris+" kr) " + " ("+nyVirkestoff+" mg)");
    }
}
