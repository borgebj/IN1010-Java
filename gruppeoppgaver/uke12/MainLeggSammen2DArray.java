import java.util.Random;

// ikke vits å prøve å fikse engang

class MainLeggSammen2DArray {
    private static int riktig = 0;
    private static int feil = 0;

    public static void main(String[] args) {
        testLeggSammen2Darray();
        testLeggSammenTomArray();
        System.out.println("___________________________________________");
        System.out.println(riktig + " riktig -- " + feil + " feil");
    }

    // sjekk-metode
    private static void sjekk(long expected, long calculcated, String message) {
        if (calculcated == expected) {
            System.out.println("Riktig! - " + calculcated);
            riktig++;
        }
        else {
            System.out.println("Feil paa " + message + " - Fikk " + calculcated + " Forventet " + expected);
            feil++;
        }
    }

    private static void testLeggSammen2DArray() {
        int [][] numbers1 = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}}; // 120
        int [][] numbers2 = {{31, 56, 1, 67, 12, 56, 98}, {31,56, 1, 67, 12, 56, 98}, {31, 56}};
        int [][] numbers3 = new int [50][50];
        Random random = new Random();
        for (int i=0; i < numbers3.length; i++) { // rader
            for (int j=0; numbers3[i].length; j++ ) { // kolonner
                number[i][j] = random.nextInt(100);
            }
        }
        int sumNumbers1 = 0;
        for (int [] rad : numers1) {
            for (int i : rad) {
                sumNumbers1 += i;
            }
        }
        int sumNumbers2 = 0;
        for (int [] rad : numers1) {
            for (int i : rad) {
                sumNumbers2 += i;
            }
        }
        int sumNumbers3 = 0;
        for (int [] rad : numers1) {
            for (int i : rad) {
                sumNumbers3 += i;
            }
        }

        sjekk(sumNumbers1, testLeggSammen2DArray(numbers1), "Test av Numbers1 1 - 15");
        sjekk(sumNumbers2, testLeggSammen2DArray(numbers2), "Test av Numbers2");
        sjekk(sumNumbers3, testLeggSammen2DArray(numbers3), "Test av random");

    }


    private static void testLeggSammenTomArray() {
        int [][] numbersTom = new int [0][0];
        sjekk(0, testLeggSammen2DArray(numbersTom), "Test av tom liste");
    }

    private static long leggSammen2DArray(int[][] numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        return leggSammen2DArray(numbers, numbers.length -1, numbers[numbers.length -1].length - 1);
    }

    // overloada metode av forrige ^
    private static long leggSammen2DArray(int[][] numbers, int row, int col) {

        // om vi er på begynelsen
        if (row == 0 && col == 0) {
            return numbers[row][col];
        }
        // om forste rad er tom
        else if (numbers[row].length == 0) {
            return 0;
        }
        // om vi er paa siste kolonne
        else if (col == 0) {
            return numbers[row][col] + leggSammen2DArray(numbers, row-1, numbers[row - 1].length -1);
        }
        else {
            return numbers[row][col] + leggSammen2DArray(numbers, row, col -1);
        }
    }

}