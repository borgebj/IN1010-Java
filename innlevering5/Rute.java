
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
    public void gaa(Rute forrige, String koordinater)  {

        // basistilfelle for sykliske - stopper om om vi har vaert innom ruten foer
        if (koordinater.contains(mineKoordinater)) return;

        // for hver rute vi gaar gjennom: legg til koordinat og strek til neste
        koordinater += mineKoordinater + "-->";

        // kaller metode som sjekker naboer og kaller "gaa()"
        sjekkOgGaa(forrige, koordinater);
    }

    // hjelpemetode - sjekker hver nabo om de ikke er den ruten de kom fra, og kaller gaa()
    private void sjekkOgGaa(Rute forrige, String koordinater) {
        if (nord != forrige)
            nord.gaa(this, koordinater);
        if (sor != forrige)
            sor.gaa(this, koordinater);
        if (ost != forrige)
            ost.gaa(this, koordinater);
        if (vest != forrige)
            vest.gaa(this, koordinater);
    }

    // kaller paa gaa() med denne ruten - starter med en tom string
    public void finnUtvei() {
        gaa(this, "");
    }

    // abstrakt metode som returner rutens tegnrepresentasjon
    abstract public char tilTegn();
}