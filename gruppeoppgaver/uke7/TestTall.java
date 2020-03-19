
class TestTall {
    public static void main(String[]args){

        Tall t1 = new Tall(1);
        Tall t2 = new Tall(2);
        Tall t3 = new Tall(3);
        Tall t4 = new Tall(4);

        if (t1.compareTo(t2) > 0) {
            System.out.println("Storre");
        } else { System.out.println("Mindre"); }
    }
}