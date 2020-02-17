
public class Hovedprogram {

    public static void main(String[] args) {

        // oppretter 2 lege-objekter og 2 spesialist-objekter
        Lege navn1 = new Lege("Doktor Shwartz");
        Lege navn2 = new Lege("Doktor Mahmud");
        Spesialist navn3 = new Spesialist("Doktor Arne", 58);
        Spesialist navn4 = new Spesialist("Doktor Kaal", 18);


        // oppretter 4 objekter av legemiddel-typen
        Narkotisk middel1 = new Narkotisk("Cannabis", 149.90, 300.0, 4);
        Vanedannende middel2 = new Vanedannende("Zlpidem", 45.90, 100.0, 2);
        Vanlig middel3 = new Vanlig("Paracet", 29.00, 500.0);
        Vanlig middel4 = new Vanlig("Ibux", 149.00, 3.4);


        // oppretter 4 objekter av resept-typen
        Hvit res1 = new Hvit(middel1, navn1, 0, 2);
        Blaa res2 = new Blaa(middel2, navn2, 1, 5);
        Militaerresept res3 = new Militaerresept(middel3, navn3, 2, 10);
        PResept res4 = new PResept(middel4, navn4, 3);

        System.out.println("---------|Lege|-----------");
        // metode-test av ulike objekter

        // Lege
        System.out.println(navn1.toString()+"\n");
        System.out.println(navn2.toString()+"\n");
        System.out.println(navn3.toString()+"\n");
        System.out.println(navn4.toString()+"\n");
        System.out.println("-------|Legemiddel|-------\n");

        // Legemiddel
        System.out.println(middel1.toString()+"\n");
        System.out.println(middel2.toString()+"\n");
        System.out.println(middel3.toString()+"\n");
        System.out.println(middel4.toString()+"\n");
        System.out.println("--------|Resepter|--------\n");

        // Resepter
        System.out.println(res1.toString()+"\n");
        System.out.println(res2.toString()+"\n");
        System.out.println(res3.toString()+"\n");
        System.out.println(res4.toString()+"\n");
        System.out.println("-----|bruk av resepter|-----\n");

        // bruk av resepter
        System.out.println("-Vi bruker hver resept 2 ganger-");
        res1.bruk();
        res1.bruk();
        System.out.println("Resept 1 - antall reit igjen: " + res1.antallReit);

        res2.bruk();
        res2.bruk();
        System.out.println("Resept 2 - antall reit igjen: " + res2.antallReit);

        res3.bruk();
        res3.bruk();
        System.out.println("Resept 3 - antall reit igjen: " + res3.antallReit);

        res4.bruk();
        res4.bruk();
        System.out.println("Resept 4 - antall reit igjen: " + res4.antallReit);
        System.out.println("\n-Vi bruker resept 4 2 ganger til-");
        res4.bruk();
        System.out.println("Resept 4 - antall reit igjen: " + res4.antallReit);
    }
}