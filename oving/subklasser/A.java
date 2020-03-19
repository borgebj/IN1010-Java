

public class A {

    String navn;

    public A() {
        navn = "A";
    }

    public void test(C x) {
        if (x instanceof B) {
            System.out.println("InstanceOf B");
        }
    }

    public String hentNavn() {
        return navn;
    }

}

