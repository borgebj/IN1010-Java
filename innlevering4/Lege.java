
public class Lege extends Lenkeliste<Resept> implements Comparable<Lege>  {

    // instansvariabel legeNavn fra parameter for navnet til legen
    String navn;

    // lenkeliste som holder alle resepter legen har skrevet ut
    Lenkeliste<Resept> utskrevedeResepter = new Lenkeliste<Resept>();


    public Lege(String navn){
        this.navn = navn;
    }

    // metode som returner navnet paa legen
    public String hentNavn() {
        return navn;
    }

    @Override // metode som overskriver toString()-metoden og returner relevant info
    public String toString() {
        return ("Lege: " + navn);
    }

    @Override // Sammenligner navn fra klassen med navnet til objektet i parameter
    public int compareTo(Lege lege) {
        return navn.compareTo(lege.navn);
    }

    // metode som returner lenkelisten med resepter til legen
    public Lenkeliste hentResepter() {
        return utskrevedeResepter;
    }

    // metode for aa  skrive ut (lage > legge til liste > returne) resept av typen "HvitResept"
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {

        //sjekker forst om legemiddel er narkotisk (lege har ikke lov) - kaster unntak
        if (legemiddel instanceof Narkotisk) { throw new UlovligUtskrift(this, legemiddel, pasient.ID); }

        // lager resept med info fra parameter + "this" - denne klassen
        HvitResept hvit = new HvitResept(legemiddel, this, pasient, reit);

        // legger til resept i lenkelisten til legen og pasienten og returner
        utskrevedeResepter.leggTil(hvit);
        pasient.addResept(hvit);
        return hvit;
    }

    // metode for aa  skrive ut (lage > legge til liste > returne) resept av typen "MilitaerResept"
    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk && this instanceof Lege) { throw new UlovligUtskrift(this, legemiddel, pasient.ID); }

        // lager, legger til og returner
        MilitaerResept militaer = new MilitaerResept(legemiddel, this, pasient, reit);

        utskrevedeResepter.leggTil(militaer);
        pasient.addResept(militaer);
        return militaer;
    }

    // metode for aa  skrive ut (lage > legge til liste > returne) resept av typen "PResept"
    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) { throw new UlovligUtskrift(this, legemiddel, pasient.ID); }

        // lager, legger til og returner
        PResept PResept = new PResept(legemiddel, this, pasient);

        utskrevedeResepter.leggTil(PResept);
        pasient.addResept(PResept);
        return PResept;
    }

    // metode for aa  skrive ut (lage > legge til liste > returne) resept av typen "BlaaResept"
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) { throw new UlovligUtskrift(this, legemiddel, pasient.ID); }

        // lager, legger til og returner
        BlaaResept Blaa = new BlaaResept(legemiddel, this, pasient, reit);

        utskrevedeResepter.leggTil(Blaa);
        pasient.addResept(Blaa);
        return Blaa;
    }
}