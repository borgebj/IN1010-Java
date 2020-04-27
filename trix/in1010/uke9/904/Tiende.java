
class Tiende implements Runnable {
    @Override
    public void run() {

        for (int i=5; i < 1000; i++) {
            if (i % 10 == 0) {
                System.out.println(i);
            }
        }
    }
}