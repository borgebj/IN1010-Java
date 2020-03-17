//TODO: Del D: Klassen lege 

public class Lege implements Comparable<Lege> {

    // instansvariabel legeNavn fra parameter for navnet til legen
    String legeNavn;

    public Lege(String navn){
        legeNavn = navn;
    }

    // metode som returner navnet paa legen
    public String hentNavn() {
        return legeNavn;
    }

    @Override // metode som overskriver toString()-metoden og returner relevant info
    public String toString() {
        return ("Legenavn: " + legeNavn);
    }
    
    /* NYTT ! */ 
    
    @Override // overskriving av "compareTo" for "Lege"
    public compareTo(Lege lege) {
        // IDK wtf skal være her oppgaven er dårlig
    }
}
