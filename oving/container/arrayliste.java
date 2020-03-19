
class arrayliste {

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6};
        int iBruk = data.length;

        int pos = 3;

        for (int i = pos+1; i < iBruk; i++) {
            data[i-1] = data[i];
        }
        iBruk--;
        data[pos] = null;



        for (int x : data) {
            System.out.println(x); }
    }
}