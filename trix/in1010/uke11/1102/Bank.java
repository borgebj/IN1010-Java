import java.util.concurrent.locks.*;

public class Bank {

    int pengebelop = 0;
    private Lock laas;

    public Bank() {
        laas = new ReentrantLock();

    }


    // sett inn
    public void gi(int belop) throws InterruptedException {
        laas.lock();
        try {
            pengebelop += belop;
        }
        finally {
            laas.unlock();
        }
    }

    // ta ut
    public void ta(int belop) throws InterruptedException {
        laas.lock();
        try {
            pengebelop -= belop;
        }
        finally {
            laas.unlock();
        }
    }

    public int saldo() {
        return pengebelop;
    }
}