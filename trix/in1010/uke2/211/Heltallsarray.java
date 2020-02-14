
class Heltallsarray {
    public static void main(String[] args) {

        int a = 0;
        int b = 1;
        int c = 2;
        int d = 3;
        int e = 4;

        System.out.println("A: "+a);
        System.out.println("B: "+b);
        System.out.println("C: "+c);
        System.out.println("D: "+d);
        System.out.println("E: "+e);

        // opretter ny array med navn array og lengde 5 med type int
        int[] array = new int[5];

        // legger til tall 1-5 i lister
        for (int i=0; i<array.length; i++) {
            array[i] = i; }

        // maate 1 aa printe alle verdier fra array
        for (int i=0; i<array.length; i++) {
            System.out.println(array[i]); }

        System.out.println();

        // maate 2
        for (int var : array) {
            System.out.println(var);
        }

    }

}