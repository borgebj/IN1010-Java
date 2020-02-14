import java.util.Arrays;

class ArrayTo {
    public static void main(String[] args){
        int[] intArray = new int[5];
        System.out.println(Arrays.toString(intArray));
        System.out.println(intArray.length);
        System.out.println("Legger til tallet 5 på første indeks");
        intArray[0] = 5;
        System.out.println(Arrays.toString(intArray));
        System.out.println(intArray.length);
    }
}