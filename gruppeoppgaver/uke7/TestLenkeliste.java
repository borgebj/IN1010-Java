
class TestLenkeliste {
    public static void main(String[]args) {
        Lenkeliste<String> liste = new Lenkeliste<>();

        liste.settInnPaaStarten("Hei");
        liste.settInnPaaStarten("Hello");
        liste.settInnPaaStarten("hey");

        for (String data : liste) {
            System.out.println(data);
        }

    }
}