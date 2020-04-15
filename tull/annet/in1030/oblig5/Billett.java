
public abstract class Billett {

    abstract int pris;
    Kino minKino;
    int billettId;
    boolean erLedig = true;

    public Billett(Kino kino, int billettId, int alder) {
        this.minKino = kino;
        this.billettId = billettId;
    }

    public void printBillett() {
        System.out.println("Kino: " + minKino.hentNavn());
        System.out.println("BillettID: " + billettId);
    }

    public Billett registrer() {
        erLedig = false;
        return this;
    }
}