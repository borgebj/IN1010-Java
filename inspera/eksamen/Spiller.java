import java.util.concurrent.locks.*;

public class Spiller implements Comparable<Spiller> {

    // spiller-navn, trekk og poeng (starter paa 0)
    String navn;
    int antallTrekk;
    int formue;

    // laas for bruk av TraadSpiller
    Lock laas = new ReentrantLock();

    // referanse til startsted for spiller - endres underveis
    Sted startsted;

    // tom ryggsegg og referanse til brukerterminalen
    Skattkiste ryggsekk = new Skattkiste(2);
    Brukergrensesnitt terminal;


    public Spiller(String navn, int antallTrekk, Brukergrensesnitt terminal, Sted startsted) {
        this.navn = navn;
        this.antallTrekk = antallTrekk;
        this.terminal = terminal;
        this.startsted = startsted;
        formue = 0;
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
        laas.lock();
        terminal.sleep(1000);

        terminal.skrivInnhold(current);

        terminal.sleep(1000);
        laas.unlock();
    }

    // gaar videre (overrides i VeivalgSpiller)
    protected void gaaVidere() {

        // informerer at runden er ferdig
        System.out.println("\nFerdig med trekk");
        terminal.sleep(1500);

        // gaar videre og trekker fra total antall trekk
        startsted = startsted.gaaVidere();
    }

    private Gjenstand taFraKiste(Skattkiste kiste, boolean harTatt) {
        laas.lock();
        if (!kiste.erTom()) {
            if (!ryggsekk.erFull()) {
                if (!harTatt) {

                    Gjenstand tatt = kiste.taUt();
                    ryggsekk.leggNed(tatt);
                    System.out.println("\n" + tatt + " er tatt opp");
                    laas.unlock();
                    return tatt;

                } else {
                    System.out.println("\nDu kan bare ta ut en gjenstand");
                }
            } else {
                System.out.println("\nRyggsekken din er full");
            }
        } else {
            System.out.println("\nSkattkisten er tom");
        }
        laas.unlock();
        return null;
    }

    private void selgFraRyggsekk(Skattkiste kiste, Gjenstand tatt) {
        laas.lock();
        if (!ryggsekk.erTom()) {
            if (!kiste.erFull()) {
                System.out.println();

                // om hverken ryggsekk er tom eller kiste er full
                int selgValg = terminal.beOmKommando("Hva onsker du aa selge?", ryggsekk.hentGjenstander());
                Gjenstand valgtGjenstand = ryggsekk.hent(selgValg);

                // sjekker om gjenstand er tatt fra samme sted
                if (valgtGjenstand == tatt) {
                    System.out.println("\nDu kan ikke selge samme gjenstand du tok fra samme sted");
                } else {
                    ryggsekk.fjern(valgtGjenstand);
                    formue += kiste.leggNed(valgtGjenstand);

                    terminal.sleep(500);
                    System.out.println("\nDin formue er oppdatert: " + formue + "kr");
                }
            } else {
                System.out.println("\nSkattkisten er full");
            }
        } else {
            System.out.println("\nRyggsekken din er tom");
        }
        laas.unlock();
    }

    public void nyttTrekk() {
        Skattkiste currentKiste = startsted.hentKiste();
        boolean harTatt = false;
        Gjenstand tatt = null;

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
        antallTrekk--;
    }

    @Override // brukt for aa sammenligne spillere for resultat-menyen
    public int compareTo(Spiller o) {
        return Integer.compare(this.formue, o.hentFormue());
    }
}