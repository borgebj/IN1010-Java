
public class Pasient extends Stabel<Resept> {

    String navn;
    String fNr;

    // unik ID for pasient
    static int idCount = 0;
    int ID = 0;


    // stabel med resepter for pasienten
    Stabel<Resept> resepter = new Stabel<Resept>();

    public Pasient(String navn, String fNr) {
        this.navn = navn;
        this.fNr = fNr;
        ID = idCount++;
    }

    // returner ID'en til pasienten
    public int hentId() {
        return ID;
    }

    @Override // egen to-String metode
    public String toString() {
        return ("Pasientnavn: " + navn +
                "\nFoedselsnummer: " + fNr);
    }
}


