
public class Hovedprogram {
    public static void main(String[] args) {

        Bank fellesbank = new Bank();

        System.out.println("Starter ...");
        for (int i=0; i < 100; i++) {
            Traad traad = new Traad(i, fellesbank);
            Thread thred = new Thread(traad);
            thred.start();
        }
    }
}