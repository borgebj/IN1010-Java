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

        // "clear-screen"
        System.out.println(new String(new char[50]).replace('\0', '\n'));

        System.out.println("\n--------| "+spiller.hentNavn()+" |--------");
        System.out.println("Antall trekk: [" + spiller.antallTrekk + "] - Formue: [" + spiller.formue+" kr]");
        System.out.println("Mine gjenstander (maks 2): " + spiller.ryggsekk);
        System.out.println("\n|> > " + spiller.startsted + "< <|\n");
    }

    // viser alternativer for bruker - reduserer kode i "beOmKommando"
    private void visValg(String spoersmaal, String[] alternativer) {
        System.out.println("\n-------| valg |------------");

        // skriver alle alternativer med tall ved siden av (1: gaa .. )
        for (int i = 0; i < alternativer.length; i++)
            System.out.println(i + ": " + alternativer[i]);

        System.out.print("---------------------------\n" + spoersmaal + " \n > ");
    }

    @Override
    public int beOmKommando(String spoersmaal, String[] alternativer) {

        visValg(spoersmaal, alternativer);

        // soerger for at valget ikke er "unreachable"
        int valg = scanner.nextInt();
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