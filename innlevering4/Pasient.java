
class Pasient extends Stabel<Resept> {

    String navn;
    String fNr;

    // unik ID for pasient
    static int idCount = 0;
    int ID = 0;


    // stabel med resepter for pasienten
    Stabel resepter = new Stabel<Resept>();

    public Pasient(String navn, String fNr /* mer? */ ) {
        this.navn = navn;
        this.fNr = fNr;
    }
}


// B2: Endre klassene som tar inn en int pasientid til å ta inn en Pasient pasient.
// AKA : alle resepter X_X