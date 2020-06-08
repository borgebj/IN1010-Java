import java.util.Scanner;

public class Terminal implements Brukergrensesnitt {

    // instansvariabel - Scanner fra konstruktoer
    Scanner scanner;
    Spiller spiller;


    public Terminal(Scanner scanner) {
        this.scanner = scanner;
    }

    // setter spilleren som bruker terminalen fra paraemeter
    public void settSpiller(Spiller spiller) {
        this.spiller = spiller;
    }


    @Override
    public void giStatus() {

        //"clear-screen"
        System.out.println(new String(new char[50]).replace('\0', '\n'));

        System.out.println("\n--------| " + spiller.hentNavn() + " |--------");
        System.out.println("Antall trekk: [" + spiller.hentTrekk() + "] - Formue: [" + spiller.hentFormue() + " kr]");
        System.out.println("Mine gjenstander (maks 2): " + spiller.hentRyggsekk());
        System.out.println("\n|> > " + spiller.hentSted() + "< <|");
    }

    // viser alternativer for bruker - reduserer kode i "beOmKommando"
    private void visValg(String spoersmaal, String[] alternativer) {
        System.out.println("\n-------| valg |------------");

        // skriver alle alternativer med tall ved siden av (1: gaa .. )
        for (int i = 0; i < alternativer.length; i++)
            System.out.println(i+1 + ": " + alternativer[i]);

        System.out.print("---------------------------\n" + spoersmaal + " \n > ");
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

        visValg(spoersmaal, alternativer);

        // soerger for at valget ikke er "unreachable"
        int valg = scanner.nextInt() - 1;
        while (valg >= alternativer.length) {
            sleep(1000);
            System.out.print("\n"+valg+" er ikke et valg - proev igjen\n");
            visValg(spoersmaal, alternativer);
            valg = scanner.nextInt();
        }
        return valg;
    }

    @Override
    public void sleep(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException ignored) {}
    }
}