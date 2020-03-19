
class Kommode extends Mobel {
    // private fordi vi ikke skal ha flere underklasser
    private int hoyde;
    private int antSkuffer;

    public Kommode(int vek, int dybd, int bredd, int hoy, int antall) {
        super(vek, dybd, bredd);
        hoyde = hoy;
        antSkuffer = antall;
    }


    public int hentStorrelse() {
        return (dybde * bredde * hoyde);
    }
    
    public void hentAntSkuffer() {
        System.out.println("Antall skuffer: " + antSkuffer);
    }
}
