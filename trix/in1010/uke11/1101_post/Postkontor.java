import java.util.concurrent.locks.*;

class Postkontor {

    Post[] post = new Post[10];

    Lock laas = new ReentrantLock();
    Condition ikkeFull = laas.newCondition();
    Condition ikkeTom = laas.newCondition();

    public int stoerrelse() {
        int stoerrelse = 0;
        for (int i=0; i < post.length; i++) {
            if (post[i]!=null) {
                stoerrelse++;
            }
        }
        return stoerrelse;
    }

    // sett inn
    public void leverPost(Post p) {
        laas.lock();
        try {
            if (stoerrelse()==10) {
                ikkeFull.await(); // er den full sier vi at den maa vente fordi den er full
            }
            for (int i=0; i < post.length; i++) {
                if (post[i] == null) {
                    post[i] = p;
                    ikkeTom.signal(); // legger vi noe inn signaliserer vi at den ikke lenger er tom, fordi vi la til noe (signaliserer kun om vi har satt innn)
                    return;
                }
            }
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        finally {
            laas.unlock();
        }
    }

    // ta ut
    public Post hentPost(String mottaker) {
        laas.lock();
        try {
            if (stoerrelse()==0) {
                ikkeTom.await(); // hvis den er tom sier vi den maa vente fordi den er tom
            }
            for (int i=0; i < post.length; i++) {
                if (post[i]!=null) {
                    if (post[i].mottaker.equals(mottaker)) {
                        Post p = post[i];
                        post[i] = null;
                        ikkeFull.signal();
                        return p;
                    }
                }
            }
            ikkeTom.await();
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        finally {
            laas.unlock();
        }
        return null;
    }
}