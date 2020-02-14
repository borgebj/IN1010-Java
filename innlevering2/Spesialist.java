
public class Spesialist extends Lege implements Godkjenningsfritak{

    // instansvariabel ID fra parameteren for kontrollID'en
    int id;

    public Spesialist(String navn, int kontrollID) {
        super(navn);
        id = kontrollID;
    }

    @Override // metode som overskrives fra grensesnittet som returner kontrollID'en
    public int hentKontrollID(){
        return id;
    }

    @Override // metode som overskriver toString()-metoden og returner relevant info
    public String toString() {
        return ("Spesialistnavn: " + legeNavn +
                "\nKontrollID: " + id);
    }
}