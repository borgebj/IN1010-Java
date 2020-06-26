import java.util.concurrent.locks.*;
import java.util.ArrayList;

public class Felles {
    static int penger = 2000;
    static Lock laas = new ReentrantLock();
    static ArrayList<Integer> behold = new ArrayList<Integer>();


    public static void settInn(int verdi) throws InterruptedException {
        laas.lock();
        try {
            behold.add(verdi);
        } finally {
            laas.unlock();
        }
    }

    public static int taUt() throws InterruptedException {
        laas.lock();
        try {
            if (!behold.isEmpty())
                return (behold.get(behold.size()-1));
            else return 0;
        } finally {
            laas.unlock();
        }
    }

    public static class Ta implements Runnable {
        @Override
        public void run() {
            taUt();
        }
    }

    public static class Gi implements Runnable {
        @Override
        public void run() {
            settInn(500);
        }
    }

    public static void main(String[] args) {
        Thread ta = new Thread(new Ta());
        Thread gi = new Thread(new Gi());

        ta.start();
        gi.start();

        System.out.println(penger);
    }
}
