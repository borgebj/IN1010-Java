
class testEn<T extends Comparable<T>> {

    public Integer sjekkVerdi(T a, T b){

        Integer verdi = a.compareTo(b);

        if (verdi < 0) {
            System.out.println(a + " er mindre enn " + b);
        }

        else if (verdi > 0) {
            System.out.println(a + " er storre enn " + b);
        }

        else if (verdi == 0) {
            System.out.println(a + " er lik " + b);
        }
        return verdi;
    }
}
// x.compareTo(y);

// returner 1 (pos) om x er storre enn y
// returner 0 (neut) om x er lik y
// returner -1 (neg) om x er mindre enn y

