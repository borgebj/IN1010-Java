
public class Sofa extends Mobel {
    // private fordi vi ikke skal ha flere underklasser
    private int antPers;

    public Sofa(int vek, int dybd, int bredd, int antall) {
        super(vek, dybd, bredd);
        antPers = antall;
    }

    public void skrivPlasser() {
        System.out.println("Antall plasser: " + antPers);
    }
}
