
public class Traad implements Runnable {

    int belop;
    Bank bank;

    public Traad(int belop, Bank bank) {
        this.belop = belop;
        this.bank = bank;
    }

    @Override
    public void run() {
        for (int i=0; i < 100; i++) {
            try {
                bank.ta(100);
                bank.gi(100);
                System.out.println(bank.saldo());
                Thread.sleep(200);
            } catch (InterruptedException e) {}
        }
    }
}