import obligFire.*;

class Pasient extends Stabel<Resept> {

    String navn;
    String fNr;
    static int idCount = 0;
    int ID = 0;
    // stabel

    public Pasient(String navn, String fNr /* mer? */ ) {
        this.navn = navn;
        this.fNr = fNr;
    }
}