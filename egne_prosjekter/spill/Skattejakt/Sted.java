
public class Sted {

    // beskrivelse av sted og skattekisten som er der
    String beskrivelse;
    Skattkiste minKiste;
    Sted nesteSted; //Oppgave 2

    public Sted(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }


    // setter referanse til neste sted
    public void settNesteSted(Sted nesteSted) {
        this.nesteSted = nesteSted;
    }

    // setter kisten til stedet
    public void settKiste(Skattkiste kiste) {
        this.minKiste = kiste;
    }

    public Skattkiste hentKiste() {
        return minKiste;
    }

    public String hentBeskrivelse() {
        return beskrivelse;
    }

    // gaar videre - returner neste sted
    public Sted gaaVidere() {
        return nesteSted;
    }


    @Override
    public String toString() {
        return beskrivelse + "";
    }

}