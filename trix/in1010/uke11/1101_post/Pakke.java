
class Pakke extends Post {
    public Pakke(String beskrivelse, String mottaker) {
        super(beskrivelse, mottaker);
    }

    public String toString() {
        return "Mottaker: " + mottaker + " - PAKKE-beskrivelse: " + beskrivelse;
    }
}