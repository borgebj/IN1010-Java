
class Fakultet {

    public static void main(String[] args) {
        int tall = Integer.parseInt(args[0]);

        for (int i=0; i <= tall; i++) {
            System.out.println(i + "! = " + fakultet(i));
        }
    }

    static int fakultet(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * fakultet(n-1);
    }
}