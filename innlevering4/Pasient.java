
public class Pasient extends Stabel<Resept> {

    // navn og foedselsnummer
    String navn;
    String fNr;

    // unik ID for pasient
    static int idCount = 0;
    int ID = 0;

    // stabel med resepter for pasienten
    Stabel<Resept> resepter = new Stabel<Resept>();


    // konstruktoer - gir globale variabler verdier fra parameter
    public Pasient(String navn, String fNr) {
        this.navn = navn;
        this.fNr = fNr;
        ID = idCount++;
    }

    // returner navn til pasient
    public String hentNavn() {
        return navn;
    }

    // returner foedselsnummer til pasient
    public String hentfNr() {
        return fNr;
    }

    // returner ID'en til pasienten
    public int hentId() {
        return ID;
    }

    // henter ut listen med resepter
    public Lenkeliste hentResepter() {
        return resepter;
    }

    // legger til ny resept
    public void addResept(Resept resept) {
        resepter.leggTil(resept);
    }

    @Override // egen to-String metode
    public String toString() {
        return (ID +": "+ navn + " (fnr "+fNr+")");
    }
}