
class Hovedprogram {
    public static void main(String[] args) {

        Brev brevet = new Brev("Per Askeladd", "Espen Askeladd");
        brevet.skrivLinje("Hvordan har du det?");
        brevet.skrivLinje("Jeg har det bare bra!");
        brevet.lesBrev();
    }
}