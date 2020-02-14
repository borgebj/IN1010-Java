import baatpakke.Baat;
import baatpakke.Baathus;

class Test {
    public static void main(String[] args){
        System.out.println("---------------------------------------\n");

        // 1 hoved baathus-objekt
        Baathus baathus1 = new Baathus(3);

        // 2 ekstra baathus-objekter for aa teste
        Baathus baathus2 = new Baathus(3);
        Baathus baathus3 = new Baathus(6);

        // 4 ulike baat-objekter
        Baat b1 = new Baat("Triton");
        Baat b2 = new Baat("Chaparral");
        Baat b3 = new Baat("Mercury");
        Baat b4 = new Baat("Mercury");

        // fyller baathus1 med 2 baater og saa skriver ut
        baathus1.settInn(b1);
        baathus1.settInn(b2);
        baathus1.settInn(b3);
        baathus1.settInn(b4);
        baathus1.skrivBaater();

        System.out.println("\n---------------------------------------\n");

        // fyller baathus2 med 3 baater og saa skriver ut
        baathus2.settInn(obj1);
        baathus2.settInn(obj2);
        baathus2.settInn(obj3);
        baathus2.skrivBaater();

        System.out.println("\n---------------------------------------\n");

        // fyller baathus3 med 4 baater og saa skriver ut
        baathus3.settInn(obj1);
        baathus3.settInn(obj2);
        baathus3.settInn(obj3);
        baathus3.settInn(obj4);
        baathus3.skrivBaater();

        System.out.println("\n---------------------------------------");

    }
}