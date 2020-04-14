
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

    // sjekker parametere og legger til nabo i forhold til tegnet
    public void settNabo(Rute rute, char tegn) {
        if (tegn=='N') this.nord = rute;
        if (tegn=='S') this.sor = rute;
        if (tegn=='O') this.ost = rute;
        if (tegn=='V') this.vest = rute;
    }


    // setter referanse til rutens labyrint
    public void settReferanse(Labyrint ref) {
        this.minLabyrint = ref;
    }

    // rekursjon metode som kaller paa sine naboer helt til den finner aapningen i labyrinten
    public void gaa(Rute denne, String koordinater)  {

        // basistilfelle for sykliske - stopper om om vi har vaert innom ruten foer
        if (koordinater.contains(mineKoordinater)) { return; }

        // for hver rute legges koordinatene til i stringen
        koordinater += mineKoordinater;

        // basistilfelle: om ruten er aapning - legger til utvei og stopper
        if (erAapning()) {
            minLabyrint.utveier.leggTil(koordinater);
            return;
        }
        // basistilfelle: om ruten er svart - stopp
        else if (tilTegn()=='#') { return; }
        else {
            // ellers: legger til pil i melding og kall paa hjelpemetode
            koordinater += "-->";
            sjekkOgGaa(denne, koordinater);
        }
    }

    // hjelpemetode - sjekker hver nabo om de ikke er den ruten de kom fra, og kaller gaa()
    private void sjekkOgGaa(Rute denne, String koordinater) {
        if (nord != denne)
            nord.gaa(this, koordinater);
        if (sor != denne)
            sor.gaa(this, koordinater);
        if (ost != denne)
            ost.gaa(this, koordinater);
        if (vest != denne)
            vest.gaa(this, koordinater);
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