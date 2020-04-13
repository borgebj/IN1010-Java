
public abstract class Rute {

    // variabel for kolonne, rad, koordinatene i String og labyrinten den er del av
    protected int rad;
    protected int kolonne;
    protected String mineKoordinater;
    protected Labyrint minLabyrint;

    // nabo-ruter
    protected Rute nord, sor, ost, vest;


    // konstruktoer som setter rad, kolonne og mineKoordinater-stringen
    public Rute(int rad, int kolonne) {
        this.rad = rad;
        this.kolonne  = kolonne;
        this.mineKoordinater = "["+kolonne+","+rad+"]";
    }

    // setter naboer
    public void settNaboer(Rute nord, Rute sor, Rute ost, Rute vest) {
        this.nord = nord;
        this.sor = sor;
        this.ost = ost;
        this.vest = vest;
    }

    // setter referanse til rutens labyrint
    public void settReferanse(Labyrint ref) {
        this.minLabyrint = ref;
    }

    // rekursjon metode som kaller paa sine naboer helt til den finner aapningen i labyrinten
    public void gaa(Rute denne, String koordinater)  {

        // nytt basistilfelle for sykliske - stopper om stringen vi tar med oss inneholder denne rutens koordinater
        if (koordinater.contains(mineKoordinater)) {
            return;
        }

        // for hver rute legges koordinatene til i stringen
        koordinater += mineKoordinater;

        // basistilfelle: om ruten er aapning
        if (erAapning()) {

            // legger til stringen i utveier-listen og stopper
            minLabyrint.utveier.leggTil(koordinater);
            return;
        }
        // basistilfelle: om ruten er svart
        else if (tilTegn()=='#') {
            return;
        }
        // om ruten er godkjent til aa gaa videre:
        else {

            // om ruten gaar videre legges pil til
            koordinater += "-->";

            // sjekk hver nabo om de ikke er den ruten de kom fra
            if (nord != denne) {
                nord.gaa(this, koordinater);
            }
            if (sor != denne) {
                sor.gaa(this, koordinater);
            }
            if (ost != denne) {
                ost.gaa(this, koordinater);
            }
            if (vest != denne) {
                vest.gaa(this, koordinater);
            }
        }
    }

    // kaller paa gaa() med denne ruten - starter med en tom string
    public void finnUtvei() {
        gaa(this, "");
    }

    // abstrakt metode som returner rutens tegnrepresentasjon
    abstract public char tilTegn();

    // abstrakt metode som returner om ruten er aapning eller ikke
    abstract public boolean erAapning();

}