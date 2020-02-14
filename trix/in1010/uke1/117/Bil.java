
class Bil {

    private String eier;
    private String merke;

    public Bil(String innEier, String innMerke) {
        eier = innEier;
        merke = innMerke;
    }

    public void skrivUt() {
        System.out.println("Eier: " + eier);
        System.out.println("Merke: " + merke);
    }
}