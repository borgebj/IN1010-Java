// for aa bruke array:
// (trenger ikke bruke import java.util.Arrays!)

class Array1 {
    public static void main(String[] args) {

        //maate nr 1 - spesifisere lengde - tom array
        int[] array1 = new int[5];

        //"kan ikke" printe array
        System.out.println(array1);

        // men kan legge inn i indekser
        array1[0] = 1;
        array1[1] = 2;
        array1[2] = 3;
        array1[3] = 4;
        array1[4] = 5;

        // trenger for-lokke for aa printe ut
        for (int i=0; i<array1.length; i++) {
            System.out.println(array1[i]);}

        //maate nr 2 - forhandsbestemme elementer - full array
        int[] array2 = {1, 2, 3, 4, 5};

        System.out.println("Vanlig for-loop");

        // printer ut hvert element i array2
        for (int a=0; a<array2.length; a++) {
            System.out.println(a);
        }

        System.out.println("Enhanced for-loop");
        for (int tall : array2) {
            System.out.println( tall );
        }
    }
}