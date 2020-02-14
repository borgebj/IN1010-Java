
class A {}
class B {}
class C {}

class Hovedprogram {
    public static void main(String[] args) {

        A [] list = new A [5];

        for (A x : list) {
            System.out.println(x);
        }

        A a = new A();
        list[3] = a;

        for (A y : list) {
            System.out.println(y);
        }

    }
}