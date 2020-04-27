
class Post {

    String beskrivelse;
    String mottaker;

    public Post(String beskrivelse, String mottaker) {
        this.beskrivelse = beskrivelse;
        this.mottaker = mottaker;
    }

    public String toString() {
        return "Mottaker: " + mottaker + " - Beskrivelse: " + beskrivelse;
    }
}