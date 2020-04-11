
public abstract class Rute {

    // variabel for kolonne, rad og labyrinten den er del av
    protected int rad;
    protected int kolonne;
    protected Labyrint minLabyrint;

    // nabo-ruter
    protected Rute nord, sor, ost, vest;


    public Rute(int rad, int kolonne) {
        this.rad = rad;
        this.kolonne  = kolonne;
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


        /*
        melding += denne.melding + "["+kolonne+","+rad+"]";
         */

        // basistilfelle: om ruten er aapning
        if (erAapning()) {

            System.out.println("Aapning: " + rad+","+kolonne);

            return;
        }
        // basistilfelle: om ruten er svart
        else if (tilTegn()=='#') {
            return;
        }
        // om ruten er godkjent til aa gaa videre:
        else {

            koordinater += "-->";

            /*
            // legg til pil for aa indikere at den gaar til en ny rute
            melding += "-->";
             */


            // sjekk hver nabo om de ikke er den ruten de kom fra
            if (nord != denne) {
                nord.gaa(this, mineKoordinater);
            }
            if (sor != denne) {
                sor.gaa(this, mineKoordinater);
            }
            if (ost != denne) {
                ost.gaa(this, mineKoordinater);
            }
            if (vest != denne) {
                vest.gaa(this, mineKoordinater);
            }
        }
    }

    // kaller paa gaa() med denne ruten
    public void finnUtvei() {
        gaa(this, vei);
    }

    // abstrakt metode som returner rutens tegnrepresentasjon
    abstract public char tilTegn();

    // abstrakt metode som returner om ruten er aapning eller ikke
    abstract public boolean erAapning();

}