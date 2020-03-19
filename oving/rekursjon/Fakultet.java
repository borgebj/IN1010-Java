

// "fakultet med lokke"
class Fakultet1 {
    static long fak(int k) {

        long res = 1;

        for (int i = 1; i <= k; i++) {
            res = res * 1;
        } return res;
    }

    // main-metode for utsrkfit
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        for (int i = 1; i <= n; i++) {
            System.out.println( i + "! = " + fak(i));
        }
    }
}

// fakultet med rekursjon
class Fakultet2 {
    static long fak(int k) {

        if ( k == 1 ) {
            return 1;
        }
        else {
            return k * fak( k - 1 );
        }
    }

    // main-metode for utsrkfit
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);

        for (int i = 1; i <= n; i++) {
            System.out.println( i + "! = " + fak(i));
        }
    }
}

