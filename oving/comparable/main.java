
class main {
    public static void main(String[] args) {
        testEn<Integer> test = new testEn<Integer>();
        System.out.println("\n");


        int a = test.sjekkVerdi(5, 10);
        System.out.println(a);

        System.out.println();

        int b = test.sjekkVerdi(11, 10);
        System.out.println(b);


        System.out.println("\n");
    }
}

// a = x.compareTo(y);

// returner 1 (pos) om x er storre enn y
// returner 0 (neut) om x er lik y
// returner -1 (neg) om x er mindre enn y

// x.compareTo(y)
// a < 0 - x er mindre enn y
// a = 0 - x er lik y
// a > 0 - x er større enn y