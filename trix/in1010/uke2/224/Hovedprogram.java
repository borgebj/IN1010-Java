
class Hovedprogram {
    public static void main(String[] args) {
        EspressoMaskin maskin = new EspressoMaskin();

        // lager 10 espresso
        for (int a=0; a<10; a++) {
            System.out.print("Lager Espresso: ");
            maskin.lagEspresso();
            System.out.println();
        } // lager 4 lungo
        for (int b=0; b<4; b++) {
            System.out.print("Lager lungo: ");
            maskin.lagLungo();
            System.out.println();
        }
        // henter vannmengde og printer ut
        System.out.println("Vannmengde: "+maskin.hentVannmengde()+" ml");

        // lager 1 lungo og 1 epsresso
        System.out.print("Lager lungo: ");
        maskin.lagEspresso();
        System.out.println();

        System.out.print("Lager espresso: ");
        maskin.lagLungo();
        System.out.println();

        // henter ny vannmengde
        System.out.println("Vannmengde: "+maskin.hentVannmengde()+" ml");

        // lager en espresso (er ikke mer vann, skriver ut feilmelding)
        System.out.print("Lager espresso: ");
        maskin.lagEspresso();
        System.out.println();

        // henter antall espresso, lungo og vannmengde med egen metode
        System.out.println(maskin.hentAntall());
        System.out.println();

        // forsoker aa lage en espresso (40ml) med 10 ml igjen
        maskin.lagEspresso();

        // forsoker aa lage en lungo (110ml) med 10 ml igjen
        maskin.lagLungo();

        // fyller tanken med 220ml og henter mengde
        maskin.fyllVann(220);
        System.out.println();
        System.out.println("Ny vannmengde: "+maskin.hentVannmengde()+" ml");

        // lager 2 av hver og henter vannmengde
        maskin.lagEspresso();
        maskin.lagEspresso();
        maskin.lagLungo();
        maskin.lagLungo();
        System.out.println("Vann igjen: "+maskin.hentVannmengde()+" ml\n");
    }
}