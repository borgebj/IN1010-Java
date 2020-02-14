
class Multi2 {
    public static void main(String[] args) {

        // deklarerer en array  med lengde paa begge dimensjoner
        int [][] ArrayList = new int[3][3];

        // angir verdier i hver indeks
       ArrayList[0][0] = 1;
       ArrayList[0][1] = 2;
       ArrayList[0][2] = 3;

        ArrayList[1][0] = 4;
        ArrayList[1][1] = 5;
        ArrayList[1][2] = 6;

        ArrayList[2][0] = 7;
        ArrayList[2][1] = 8;
        ArrayList[2][2] = 9;

        for (int x=0; x < ArrayList.length; x++){

            for (int y=0; y < ArrayList[x].length; y++) {
                System.out.println(ArrayList[x][y]);

            } System.out.println();
        }
    }
}