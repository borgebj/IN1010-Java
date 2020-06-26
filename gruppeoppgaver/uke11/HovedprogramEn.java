class HovedprogramEn {
    public static void main(String[] args) {

        // Hvordan opprette en Tråd:
        // 1. lag en klasse som implementerer interfacet Runnable
        // 2. implementer metoden run().
        // 3. lag et objekt av klassen din.
        // 4. lag et objekt av klassen Thread, parameter = objekt fra punkt 3.
        // 5. kall på start()-metoden til Thread-objektet.

        Teller teller = new Teller();
        Thread t = new Thread(teller);
        t.start();

        for (int i = 0; i < 10; i++){
            System.out.println(i);
        }
    }
}

class Teller implements Runnable {

    public void run(){

        for (int i = 0; i < 10; i++){
            System.out.println(i);
            try {
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
}