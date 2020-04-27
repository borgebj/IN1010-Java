import java.util.concurrent.locks.*;
import java.util.Random;


class HovedprogramTo {
    public static void main(String[] args) {

        SykehusMonitor monitor = new SykehusMonitor();

        for (int i = 0; i < 5; i++){
            Helsearbeider h = new Helsearbeider(monitor);
            Thread t = new Thread(h);
            t.start();
        }

        CoronaVirus c = new CoronaVirus(monitor);
        Thread t = new Thread(c);
        t.start();
    }
}

class CoronaVirus implements Runnable {

    SykehusMonitor monitor;

    public CoronaVirus(SykehusMonitor monitor){
        this.monitor = monitor;
    }

    public void run(){
        while (true){

            Random r = new Random();
            int tid = r.nextInt(3000) + 3000;
            try {
                Thread.sleep(tid);
            }
            catch (InterruptedException e){
                System.out.println(e);
            }

            monitor.gjorSyk();

            CoronaVirus ny = new CoronaVirus(monitor);
            Thread traad = new Thread(ny);
            traad.start();
        }
    }
}



class Helsearbeider implements Runnable {

    SykehusMonitor monitor;

    public Helsearbeider(SykehusMonitor monitor){
        this.monitor = monitor;
    }

    public void run(){

        while(true){
            Random r = new Random();
            int tid = r.nextInt(2000) + 2000;
            try {
                Thread.sleep(tid);
            }
            catch (InterruptedException e){
                System.out.println(e);
            }

            monitor.gjorFrisk();
        }
    }
}



class SykehusMonitor {

    int antallSmittede = 0;
    Lock laas = new ReentrantLock();
    Condition ikkeTom = laas.newCondition();


    public void gjorSyk(){
        laas.lock();
        try {
            antallSmittede+=1;
            System.out.println("CORONA: En syk person! (tot: "+antallSmittede+")");
            ikkeTom.signal();
        }
        finally {
            laas.unlock();
        }
    }

    public void gjorFrisk(){
        laas.lock();
        try {
            if (antallSmittede <= 0){
                System.out.println("HELSE: Ingen syke.. venter!");
                ikkeTom.await();
            }
            antallSmittede-=1;
            System.out.println("HELSE: En frisk person! (tot: "+antallSmittede+")");
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
        finally {
            laas.unlock();
        }
    }
}