
// bruk av indre klasse for runnable
public abstract class Rute {

    // variabel for kolonne, rad, koordinatene i String og labyrinten den er del av
    protected int rad;
    protected int kolonne;
    protected String mineKoordinater;
    protected Labyrint minLabyrint;

    // nabo-ruter
    protected Rute nord, sor, ost, vest;

    static int threadcount = 0;


    // konstruktoer som setter rad, kolonne og mineKoordinater-stringen
    public Rute(int rad, int kolonne) {
        this.rad = rad;
        this.kolonne  = kolonne;
        this.mineKoordinater = "["+kolonne+"."+rad+"]";
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
    // (kjorer kun i hvite ruter, fordi sort og aapning overrider)
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
        try {
            if (nord != forrige) {
                Thread nord = new Thread(new Traad(this.nord, koordinater));
                nord.start();
                nord.join();
            }
            if (sor != forrige) {
                Thread sor = new Thread(new Traad(this.sor, koordinater));
                sor.start();
                sor.join();
            }
            if (ost != forrige) {
                Thread ost = new Thread(new Traad(this.ost, koordinater));
                ost.start();
                ost.join();
            }
            // siste / vest gaar videre paa samme traad
            if (vest != forrige)
                vest.gaa(this, koordinater);
        } catch (InterruptedException e) { System.out.println("Feil"); }
    }

    // kaller paa gaa() med denne ruten - starter med en tom string
    public void finnUtvei() {
        gaa(this, ">>>");
        System.out.println( "traader: " + threadcount);
        threadcount = 0;
    }


    // abstrakt metode som returner rutens tegnrepresentasjon
    abstract public char tilTegn();

    protected static class Traad implements Runnable {

        Rute rute;
        String path;

        // konstruktoer
        Traad(Rute naboRute, String path) {
            this.rute = naboRute;
            this.path = path;
        }

        @Override // kode som kjoeres av traad
        public void run() {
            threadcount++;
            rute.gaa(rute, path);
        }
    }
}

/** Hva skjer om den gamle tråden først går videre til neste rute og så etterpå starter opp nye tråder? **/
/*
- Det som skjer da er at siden den gamle traaden gaar videre med sitt rekursive kall foer den oppretter nye,
- er at den gamle traaden maa bli ferdig med hele sitt rekursive-kall foer nye traader blir opprettet og startet.
- Dette vil gjoere hele prosessen treigere.
 */

