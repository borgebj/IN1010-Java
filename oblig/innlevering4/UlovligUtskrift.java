package innlevering4;

public class UlovligUtskrift extends Exception{
    public UlovligUtskrift(Lege lege, Legemiddel legemiddel, int pasientID){
        super(lege + " har ikke lov til å skrive ut " + legemiddel.hentNavn());
    }
}
