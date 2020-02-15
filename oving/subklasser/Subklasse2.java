
class A {

    void skrivUt() {
        System.out.println("Dette er klassen A");
    }
}

class B extends A {

    @Override
    void skrivUt() {
        System.out.println("Dette er klassen B");
    }
}


class Hovedprogram {

    public static void main(String[] args) {

        // 2 objekter av A og B med samme identifikator og innhold
        A a1 = new A();
        B b1 = new B();

        // identifikator av typen-A, men inneholder objekt av typen-B
        A a2 = new B();

        a1.skrivUt();
        b1.skrivUt();

        // dvs at a2 bruker metoder til B-objektert
        a2.skrivUt();
    }
}