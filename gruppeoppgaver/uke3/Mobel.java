
public class Mobel {
    // protected fordi de skal bli brukt av subklasser
    protected int vekt;
    protected int dybde;
    protected int bredde;

    public Mobel(int vek, int dybd, int bredd) {
        vekt = vek;
        dybde = dybd;
        bredde = bredd;
    }

    public int hentVekt() {
        return vekt;
    }

    public int hentStorrelse() {
        return (dybde * bredde);
    }
}