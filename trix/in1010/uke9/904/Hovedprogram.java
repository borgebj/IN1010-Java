
class Hovedprogram {
    public static void main(String[] args) {

        Monitor minMonitor = new Monitor();

        for (int i=0; i < 10; i++) {
            Nthe n = new Nthe(i, 0, 1000, minMonitor);
            Thread traad = new Thread(n);
            traad.start();
        }
    }
}