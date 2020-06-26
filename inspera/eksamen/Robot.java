import java.util.Random;

public class Robot implements Brukergrensesnitt {

    Spiller spiller;


    @Override // setter spilleren som bruker terminalen fra paraemeter
    public void settSpiller(Spiller spiller) {
        this.spiller = spiller;
    }


    @Override
    public void giStatus() {

        // "clear-screen"
        System.out.println(new String(new char[50]).replace('\0', '\n'));

        System.out.println("\n-------| " + spiller.hentNavn() + " |-------");
        System.out.println("Antall trekk: [" + spiller.hentTrekk() + "] - Formue: [" + spiller.hentFormue() + " kr]");
        System.out.println("Mine gjenstander (maks 2): " + spiller.hentRyggsekk());
        System.out.println("\n|> > " + spiller.hentSted() + "< <|\n");
    }

    // viser alternativer for bruker - reduserer kode i "beOmKommando"
    protected void visValg(String[] alternativer) {
        System.out.println("------Roboten kan:-------");
        for (int i = 0; i < alternativer.length; i++)
            System.out.println(i+1 + ": " + alternativer[i]);
        System.out.println("-------------------------");
    }

    protected void visRobotValg(int robotValg) {
        System.out.println("\nRobot velger alternativ " + (robotValg+1));

    }

    public void skrivInnhold(Skattkiste current) {
        String[] gjenstander = current.hentGjenstander();

        // skriver ut et slags terminal-interface for brukeren
        System.out.println("\n-------| Innhold |---------");
        for (int i = 0; i < gjenstander.length; i++)
            System.out.println(i+1 + ": " + gjenstander[i]);
        System.out.println("---------------------------\n");
    }

    @Override
    public int beOmKommando(String spoersmaal, String[] alternativer) {
        Random rand = new Random();

        visValg(alternativer);

        // returner et tilfeldig tall fra 0 til antall alternativer
        int robotValg = rand.nextInt(alternativer.length);

        // roboten favoriserer aa selge og ta. | gjoer at robotene faar formue raskere
        if (alternativer.length > 3) {
            int diceThrow = rand.nextInt(10);
            if (diceThrow > 7) robotValg = 1;
            else if (diceThrow > 6) robotValg = 2;
        }
        visRobotValg(robotValg);

        return robotValg;
    }

    @Override
    public void sleep(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException ignored) {}
    }

    @Override
    public void informer(String feilType, String ting, int tall, Gjenstand objekt) {
        switch (feilType) {
            case "tom":
            case "full":
                if (ting.equals("ryggsekk")) {
                    System.out.println("\nRyggsekken din er "+feilType);
                } else if (ting.equals("skattkiste")) {
                    System.out.println("\nSkattkisten er " +feilType);
                }
                break;

            case "annet":
                if (ting.equals("formue")) {
                    System.out.println("\nDin formue er oppdatert: " + tall + "kr");
                } else if (ting.equals("tattOpp")) {
                    System.out.println("\n" + objekt + " er tatt opp");
                } else if (ting.equals("sammeGjenstand")) {
                    System.out.println("\nDu kan ikke selge samme gjenstand du tok fra samme sted");
                } else if (ting.equals("taUt")) {
                    System.out.println("\nDu kan bare ta ut en gjenstand");
                } else if (ting.equals("trekkFerdig")) {
                    System.out.println("\nFerdig med trekk");
                } else if (ting.equals("Hoeyre") || ting.equals("Venstre") || ting.equals("Rett fram")) {
                    System.out.println("\nDu har valgt " + ting + ". Gaar til " + ting);
                } else if (ting.equals("gjenstandTyv")) {
                    System.out.println("\nDet kom en tyv og tok alle gjenstandene dine!");
                } else if (ting.equals("pengeTyv")) {
                    System.out.println("\nDet kom en tyv og tok 1/4 av formuen din!");
                } else if (ting.equals("heldig")) {
                    System.out.println("\nEn tyv forsoekte aa stjele fra deg, men du hadde ingenting han ville ha.");
                }
                break;
        }
    }
}