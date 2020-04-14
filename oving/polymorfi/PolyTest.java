
class PolyTest {
    public static void main(String[] args) {

        PolyB b = new PolyB();

        PolyC c = new PolyC();

        // klasse() og erC() tester paa klasse og subklasse
        System.out.println("B-test: " + b.klasse());
        System.out.println("C-test: " + c.klasse());
        System.out.println();
        System.out.println("B-test: " + b.erC());
        System.out.println("C-test: " + c.erC());

    }
}