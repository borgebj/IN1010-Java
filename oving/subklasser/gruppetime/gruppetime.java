
class A {}
class B extends A {}
class C extends A {}
class D extends C {}

class TrueOrFalse {
    public static void main(String[]args) {
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();
        A e = new D();
        A f = new B();

        System.out.println("a) " + (b instanceof A)); // True - fordi B er en subklasse av A
        System.out.println("b) " + (c instanceof D)); // False - c er ikke instance of d fordi d er en subklasse av c (altsa omvendt)
        System.out.println("c) " + (d instanceof A)); // True - fordi d bygger til slutt paa superklassen A
        System.out.println("d) " + (e instanceof C)); // True - D tolkes som A og er instanse av C // ?
        System.out.println("e) " + (f instanceof C)); // False - A er ikke instans av C, og heller ikke B -- tolkes som A, opprettes som B, spor om er C // ?
    }
}
