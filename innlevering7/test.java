public class test {
    public static void main(String[] args) {
        Lenkeliste<Integer> test = new Lenkeliste<Integer>();

        test.leggTil(1);
        test.leggTil(2);
        test.leggTil(3);

        test.fjern(2);
        test.fjern(2);

        System.out.println(test);
    }
}
