import java.util.Random;


public class Spiller implements Comparable<Spiller> {

    // spiller-navn, trekk og poeng (starter paa 0)
    String navn;
    int antallTrekk;
    int rundeNr = 0;
    int formue;

    // referanse til startsted for spiller - endres underveis
    Sted startsted;

    Random rand = new Random();

    // tom ryggsegg og referanse til brukerterminalen
    Skattkiste ryggsekk = new Skattkiste(2);
    Brukergrensesnitt terminal;


    public Spiller(String navn, int antallTrekk, Brukergrensesnitt terminal, Sted startsted) {
        this.navn = navn;
        this.antallTrekk = antallTrekk;
        this.terminal = terminal;
        this.startsted = startsted;
        this.formue = 0;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentTrekk() {
        return antallTrekk;
    }

    public int hentFormue() {
        return formue;
    }

    public Skattkiste hentRyggsekk() {
        return ryggsekk;
    }

    public Sted hentSted() {
        return startsted;
    }

    public Brukergrensesnitt hentGrensesnitt() {
        return terminal;
    }


    // metode for valg nr 0
    protected void seInnhold(Skattkiste current) {
        terminal.sleep(1000);

        terminal.skrivInnhold(current);

        terminal.sleep(1000);
    }

    // gaar videre (overrides i VeivalgSpiller)
    protected void gaaVidere() {

        // informerer at runden er ferdig
        terminal.informer("annet", "trekkFerdig", 0, null);
        terminal.sleep(1500);

        // gaar videre og trekker fra total antall trekk
        startsted = startsted.gaaVidere();
    }

    private Gjenstand taFraKiste(Skattkiste kiste, boolean harTatt) {
        if (!kiste.erTom()) {
            if (!ryggsekk.erFull()) {
                if (!harTatt) {

                    Gjenstand tatt = kiste.taUt();
                    ryggsekk.leggNed(tatt);
                    terminal.informer("annet", "tattOpp", 0, tatt);
                    return tatt;

                } else {
                    terminal.informer("annet", "taUt", 0,null);
                }
            } else {
                terminal.informer("full", "ryggsekk", 0, null);
            }
        } else {
            terminal.informer("tom", "skattkiste", 0, null);
        }
        return null;
    }

    private void selgFraRyggsekk(Skattkiste kiste, Gjenstand tatt) {
        if (!ryggsekk.erTom()) {
            if (!kiste.erFull()) {
                System.out.println();

                // om hverken ryggsekk er tom eller kiste er full
                int selgValg = terminal.beOmKommando("Hva onsker du aa selge?", ryggsekk.hentGjenstander());
                Gjenstand valgtGjenstand = ryggsekk.hent(selgValg);

                // sjekker om gjenstand er tatt fra samme sted
                if (valgtGjenstand == tatt) {
                    terminal.informer("annet", "sammeGjenstand", 0, null);
                } else {
                    ryggsekk.fjern(valgtGjenstand);
                    formue += kiste.leggNed(valgtGjenstand);

                    terminal.sleep(500);
                    terminal.informer("annet", "formue", formue, null);
                }
            } else {
                terminal.informer("full", "skattkiste", 0, null);
            }
        } else {
            terminal.informer("tom", "ryggsekk", 0, null);
        }
    }

    // "random-encounter" - tyv som stjeler - skjer sjeldent
    private void randomEncounter() {
        if (rand.nextInt(200) > 180 && rundeNr > 2) {
            System.out.println(new String(new char[50]).replace('\0', '\n'));

            // kan entene stjele gjenstander, eller 1/4 av formuen din
            if (rand.nextInt(8) < 6 && !ryggsekk.erTom()) {
                terminal.informer("annet", "gjenstandTyv", 0, null);
                ryggsekk.innhold.clear();
            } else if (formue != 0){
                terminal.informer("annet", "pengeTyv", 0, null);
                formue -= formue/4;
            } else {
                terminal.informer("annet", "heldig", 0, null);
            }
            terminal.sleep(2500);
        }
    }

    public void nyttTrekk() {
        Skattkiste currentKiste = startsted.hentKiste();
        boolean harTatt = false;
        Gjenstand tatt = null;

        randomEncounter();

        // lokke som fortsetter til bruker er ferdig
        int valg = 10;
        while (valg != 3) {

            terminal.giStatus();

            String[] alternativer = {"Se innhold i skattkiste", "Legge igjen gjenstand (Selge)", "Ta en gjenstand fra skattekiste", "gaa til neste sted"};
            valg = terminal.beOmKommando("Hva onsker du aa gjoere?", alternativer);

            switch (valg) {

                // se innhold i kisten paa stedet
                case (0):
                    terminal.sleep(1000);
                    seInnhold(currentKiste);
                    break;

                // legge fra seg (selge)
                case (1):
                    terminal.sleep(1000);
                    if (tatt != null) harTatt = true;
                    selgFraRyggsekk(currentKiste, tatt);
                    break;

                // ta ut om kisten ikke er tom og ryggsekken ikke er full. Kan ogsaa ta maks en gang per sted
                case (2):
                    terminal.sleep(1000);
                    tatt = taFraKiste(currentKiste, harTatt);
                    break;
            }
            terminal.sleep(2000);
        }

        // gaar videre ti slutt og trekker fra antallTrekk
        gaaVidere();
        rundeNr++;
        antallTrekk--;
    }

    @Override // brukt for aa sammenligne spillere for resultat-menyen
    public int compareTo(Spiller o) {
        return Integer.compare(this.formue, o.hentFormue());
    }
}