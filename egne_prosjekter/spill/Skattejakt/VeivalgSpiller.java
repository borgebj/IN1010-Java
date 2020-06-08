
public class VeivalgSpiller extends Spiller {

    public VeivalgSpiller(String navn, int antallTrekk, Brukergrensesnitt terminal, Sted startsted) {
        super(navn, antallTrekk, terminal, startsted);
    }

    @Override
    protected void gaaVidere() {

        // forsikrer om at det er av typen VeivalgSted og henter veiene dens
        String[] veier = ((VeivalgSted) startsted).hentVeiNavn();

        int valg = terminal.beOmKommando("Hvor onsker du aa gaa?", veier);

        // velger stringen (key i Sted-hashmap) vha bruker-input
        String retning = "";
        switch (valg) {
            case (0):
                retning = "Hoeyre";
                break;

            case (1):
                retning = "Venstre";
                break;

            case (2):
                retning = "Rett fram";
                break;
        }

        // finner neste sted og informerer bruker
        Sted gaaTil = ((VeivalgSted) startsted).hentSted(retning);
        System.out.println("\nDu har valgt " + retning + ". Gaar til " + retning);

        // gaar videre til sted fra parameter
        startsted = gaaTil;

        terminal.sleep(1500);
        // informerer at runden er ferdig
        System.out.println("\nFerdig med trekk\n");
        terminal.sleep(1500);
    }
}