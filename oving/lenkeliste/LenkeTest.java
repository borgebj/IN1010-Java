
class LenkeTest {
    public static void main(String[] args) {

        SortertLenkeliste<Integer> bruh = new SortertLenkeliste<Integer>();

        bruh.leggTil(1);
        bruh.leggTil(2);
        bruh.leggTil(5);
        bruh.leggTil(3);
        bruh.leggTil(9);

        bruh.hentAll();

        SortertLenkeliste<String> list = new SortertLenkeliste<String>();

        list.leggTil("bilA");
        list.leggTil("bilC");
        list.leggTil("bilB");

        list.hentAll();
    }
}