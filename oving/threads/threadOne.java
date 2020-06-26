
class threadOne {

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            int antall = 0;
            while (antall<10) {
                try {
                    System.out.println("Hello World");
                    Thread.sleep(500);
                    antall++;
                } catch (InterruptedException e) {}
            }
        }
    }

    public static class MyRunnableTwo implements Runnable {
        @Override
        public void run() {
            System.out.println("bruh");
        }
    }

    public static void main(String[] args) {
        Thread myThread = new Thread(new MyRunnable(), "Traaden min");
        Thread ThreadTwo = new Thread(new MyRunnableTwo(), "Traad to");

        System.out.println( "navn paa traad: " + myThread.getName() );

        ThreadTwo.start();
        myThread.start();
    }
}