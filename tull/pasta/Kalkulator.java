package pasta;

public class Kalkulator {

    private int mittSvar;

    public Kalkulator(int tall) {
        mittSvar = tall; }

    public void pluss(int tall) {
        mittSvar += tall; }

    public void gange(int tall) {
        mittSvar *= tall; }

    public void deling(int tall) {
        mittSvar /= tall; }

    public int regn() {
        return mittSvar;
    }

    public String hentSvar() {
        return ("Tallet ditt er naa: " + mittSvar); }
}