import java.util.concurrent.locks.*;

class Nthe implements Runnable {

    int n;
    int start;
    int maks;
    Monitor minMonitor;
    Lock laas = new ReentrantLock();

    public Nthe(int n, int start, int maks, Monitor minMonitor) {
        this.n = n;
        this.start = start;
        this.maks = maks;
        this.minMonitor = minMonitor;
    }

    @Override
    public void run() {
        laas.lock();
        try {
            for (int i = start; i < maks; i++) {
                if (i % n == 0) {
                    minMonitor.taImot(i);
                }
            }
            System.out.println("-------");
        }
        finally {
            laas.unlock();
        }
    }
}