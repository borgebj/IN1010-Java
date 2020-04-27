import java.util.concurrent.locks.*;


public class Monitor {

    int tal = -1;
    Lock laas = new ReentrantLock();
    Condition ikkeNeste = laas.newCondition();

    public void taImot(int t) {
        laas.lock();
        try {
            while (t-1 != tal) {
                ikkeNeste.await();
            }
            tal = t;
            System.out.println(t);
            ikkeNeste.signalAll();
        }
        catch (InterruptedException e) {}
        finally {
            laas.unlock();
        }
    }
}