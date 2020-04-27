class Hovedprogram{
    public static void main(String[] args) {

        Postkontor postkontor = new Postkontor();

        Postmann postmann = new Postmann(postkontor);
        Thread traad = new Thread(postmann);
        traad.start();

        Kunde kunde1 = new Kunde("Henrik", postkontor);
        traad = new Thread(kunde1);
        traad.start();

        Kunde kunde2 = new Kunde("Margrethe", postkontor);
        traad = new Thread(kunde2);
        traad.start();
    }
}