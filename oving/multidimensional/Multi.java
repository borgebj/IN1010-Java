
class Multi {
    public static void main(String[] args) {

        //nr 1
        // deklarerer en multidimenjonal array av typen int
        int[][] ArrayList = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
    }; //        0  1  2

        // henter tallet 3 - liste 0 pa plass nr 2
        System.out.println(ArrayList[0][2]);

        // henter tallet 9 - liste 2 pa plass nr 2
        System.out.println(ArrayList[2][2]);

        System.out.println();


        // printer ut hvert element
        for (int i=0; i < ArrayList.length; i++) {

            for (int j=0; j < ArrayList[i].length; j++) {
                System.out.println(ArrayList[i][j]); } // kan ogsaa skrives print istedenfor println for aa printe ut hver liste paa sin egen linje

            System.out.println();
        }
    }
}