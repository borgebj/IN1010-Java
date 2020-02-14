import java.util.Arrays;

class Array {
    public static void main(String[] args){

        //  -  lage array#1  -  /  lengde = 5, fordi 5 tall er definert
        int[] array = new int[5];
        array[0] = 1;
        array[1] = 2;

        //  -  printe array  -  /  Arrays.toString(####);
        System.out.print("Array nr1: ");
        System.out.println(Arrays.toString(array));

        //  -  lage array#2  -  / lengde = 6, fordi 6 antall tall
        int[] array2 = { 6, 5, 3, 2, 1, 4 };
        System.out.print("Array nr2: ");
        System.out.println(Arrays.toString(array2));

        //   -  Finne lengde  -  /  ####.length;
        System.out.print("Array nr2 lengde: ");
        System.out.println(array2.length);

        //  -  Sortere array  -  /  Arrays.sort(####);
        Arrays.sort(array2);
        System.out.print("Array nr2 sortert: ");
        System.out.println(Arrays.toString(array2));

        System.out.println("--------------------------------------------------------");
    }
}