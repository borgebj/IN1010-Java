class Kunde implements Runnable {

    String navn;
    Postkontor postkontor;

    public Kunde(String navn, Postkontor postkontor) {
        this.postkontor = postkontor;
        this.navn = navn;
    }

    public void run() {

        while (true) {
            Post p = postkontor.hentPost(navn);
            if (p != null) {
                System.out.println(p);
            }
        }
    }
}