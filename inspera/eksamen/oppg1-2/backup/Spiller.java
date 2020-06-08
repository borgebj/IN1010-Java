
public class Spiller implements Comparable<Spiller> {

    // spiller-navn, trekk og poeng (starter paa 0)
    String navn;
    int antallTrekk;
    int formue;

    // referanse til startsted for spiller
    Sted startsted;

    // tom ryggsegg og referanse til brukerterminalen
    Skattkiste ryggsekk =  new Skattkiste(2);
    Brukergrensesnitt terminal;


    public Spiller(String navn, int antallTrekk, Brukergrensesnitt terminal, Sted startsted) {
        this.navn = navn;
        this.antallTrekk = antallTrekk;
        formue = 0;
        this.terminal = terminal;
        this.startsted = startsted;
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

    public void nyttTrekk() {
        Skattkiste currentKiste = startsted.hentKiste();
        boolean harTatt = false;
        Gjenstand tatt = null;

        // lokke som fortsetter til bruker er ferdig
        int valg = 0;
        while (valg != 3) {

            terminal.giStatus();

            String[] alternativer = {"Se innhold i skattkiste", "Legge igjen gjenstand (Selge)", "Ta en gjenstand fra skattekiste", "gaa til neste sted"};
            valg = terminal.beOmKommando("Hva onsker du aa gjoere?", alternativer);

            switch (valg) {

                // se innhold i kisten paa stedet
                case (0):
                    terminal.sleep(1000);
                    String[] gjenstander = currentKiste.hentGjenstander();

                    System.out.println("\n-------| Innhold |---------");
                    for (int i=0; i < gjenstander.length; i++)
                        System.out.println(i +": "+ gjenstander[i]);
                    System.out.println("---------------------------\n");
                    terminal.sleep(1000);

                    break;

                // legge fra seg (selge)
                case (1):
                    terminal.sleep(1000);
                    if (!ryggsekk.erTom()) {
                        if (!currentKiste.erFull()) {
                            System.out.println();

                            // om hverken ryggsekk er tom eller kiste er full
                            int selgValg = terminal.beOmKommando("Hva onsker du aa selge?", ryggsekk.hentGjenstander());
                            Gjenstand valgtGjenstand = ryggsekk.hent(selgValg);

                            // sjekker om gjenstand er tatt fra samme sted
                            if (valgtGjenstand == tatt) {
                                System.out.println("\nDu kan ikke selge samme gjenstand du tok fra samme sted");
                            }
                            else {
                                ryggsekk.fjern(valgtGjenstand);
                                formue += currentKiste.leggNed(valgtGjenstand);

                                terminal.sleep(500);
                                System.out.println("\nDin formue er oppdatert: " + formue + "kr");
                            }
                        } else {
                            System.out.println("\nSkattkisten er full");
                        }
                    } else {
                        System.out.println("\nRyggsekken din er tom");
                    }
                    break;

                // ta ut om kisten ikke er tom og ryggsekken ikke er full. Kan ogsaa ta maks en gang per sted
                case (2):
                    terminal.sleep(1000);
                    if (!currentKiste.erTom()) {
                        if (!ryggsekk.erFull()) {
                            if (!harTatt) {

                                tatt = currentKiste.taUt();
                                ryggsekk.leggNed(tatt);
                                System.out.println("\n" + tatt + " er tatt opp");
                                harTatt = true;

                            } else {
                                System.out.println("\nDu kan bare ta ut en gjenstand");
                            }
                        } else {
                            System.out.println("\nRyggsekken din er full");
                        }
                    } else {
                        System.out.println("\nSkattkisten er tom");
                    }
                    break;
            }
            terminal.sleep(2500);
        }

        // informerer at runden er ferdig
        System.out.println("\nFerdig med trekk");
        terminal.sleep(1500);

        // gaar videre og trekker fra total antall trekk
        startsted = startsted.gaaVidere();
        antallTrekk--;
    }

    @Override
    public int compareTo(Spiller o) {
        return Integer.compare(this.formue, o.hentFormue());
    }
}