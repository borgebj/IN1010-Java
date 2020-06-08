import java.util.Random;

public class Robot implements Brukergrensesnitt {

    Spiller spiller;


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
            System.out.println(i + ": " + alternativer[i]);
        System.out.println("-------------------------");
    }

    protected void visRobotValg(int robotValg) {
        System.out.println("\nRobot velger alternativ " + robotValg);

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
}