class Postmann implements Runnable {

    Postkontor postkontor;

    public Postmann(Postkontor postkontor) {
        this.postkontor = postkontor;
    }
    @Override
    public void run() {

        for (int i=0; i < 100; i++) {
            if (i % 2 == 0) {
                Post post = new Pakke("Doruller", "borge");
                postkontor.leverPost(post);
            }
            else {
                Post post = new Brev("Hei, jeg trenger hjelp", "borge");
                postkontor.leverPost(post);
            }
        }
    }
}