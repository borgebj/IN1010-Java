
public class StandardBillet extends Billett {
    int pris;

    public Billett(Kino kino, int billettId, int alder) {
        super(kino, billettId, alder);
        if (alder < 16 && alder > 6) this.pris = 200;
        else if (alder > 15) this.pris = 300;
    }
}