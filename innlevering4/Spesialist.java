
public class Spesialist extends Lege implements Godkjenningsfritak{

    // instansvariabel ID fra parameteren for kontrollID'en
    int kontrollID;

    public Spesialist(String navn, int kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;
    }

    @Override // metode som overskrives fra grensesnittet som returner kontrollID'en
    public int hentKontrollID(){
        return kontrollID;
    }

    @Override // metode som overskriver toString()-metoden og returner relevant info
    public String toString() {
        return ("Spesialist: " + navn +
                "\nKontrollID: " + kontrollID);
    }

    @Override // metode for aa  skrive ut (lage > legge til liste > returne) resept av typen "MilitaerResept"
    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) {

        // lager, legger til og returner
        MilitaerResept militaer = new MilitaerResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(militaer);
        return militaer;
    }

    @Override // metode for aa  skrive ut (lage > legge til liste > returne) resept av typen "PResept"
    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient)  {

        // lager, legger til og returner
        PResept PResept = new PResept(legemiddel, this, pasient);
        utskrevedeResepter.leggTil(PResept);
        return PResept;
    }

    @Override // metode for aa  skrive ut (lage > legge til liste > returne) resept av typen "BlaaResept"
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) {

        // lager, legger til og returner
        BlaaResept Blaa = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(Blaa);
        return Blaa;
    }

}