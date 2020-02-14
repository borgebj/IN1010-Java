import javax.sound.midi.Soundbank;

public class testResepter {

    public static void main(String[] args) {

        // variabler for test-bruk

        int pasientId1 = 1;
        int pasientId2 = 2;
        int pasientId3 = 3;
        int pasientId4 = 4;
        int reit1 = 4;
        int reit2 = 2;
        int reit3 = 1;

        // lege-objekter for test
        Lege navn1 = new Lege("Arne");
        Lege navn2 = new Lege("Bob");
        Lege navn3 = new Lege("Kari");
        Lege navn4 = new Lege("Frank");

        // legemiddel-objekter fra testLegemiddel
        Vanlig v1 = new Vanlig("Paracet", 29.00, 500.0);
        Vanedannende v2 = new Vanedannende("Zlpidem", 45.90, 100.0, 2);
        Narkotisk v3 = new Narkotisk("Cannabis", 149.90, 300.0, 4);
        Vanlig v4 = new Vanlig("Ibux", 149.00, 3.4);



        // test av subklassen "Hvit"
        Hvit res1 = new Hvit(v1, navn1, pasientId1, reit1);

        System.out.println("---TEST AV HVIT RESEPT---");
        System.out.println("ID: " + res1.hentId());
        System.out.println("Legemiddel: " + res1.hentLegemiddel());
        System.out.println("Lege: " + res1.hentLege());
        System.out.println("PasientID: " + res1.hentPasientId());
        System.out.println("Reit: " + res1.hentReit());
        System.out.println("FoerPris: " + res1.prisAaBetale() + " kr");
        System.out.println("Farge: " + res1.farge());

        // bruker resepten 2 ganger
        res1.bruk();
        res1.bruk();
        System.out.println("Reit igjen etter bruk: " + res1.hentReit());


        System.out.println("--------------------------\n");


        // test av subklassen "Blaa"
        Blaa res2 = new Blaa(v2, navn2, pasientId2, reit2);

        System.out.println("---TEST AV BLAA RESEPT---");
        System.out.println("ID: " + res2.hentId());
        System.out.println("Legemiddel: " + res2.hentLegemiddel());
        System.out.println("Lege: " + res2.hentLege());
        System.out.println("PasientID: " + res2.hentPasientId());
        System.out.println("Reit: " + res2.hentReit());
        System.out.println("FoerPris: " + res2.prisAaBetale() + " kr");
        System.out.println("Farge: " + res2.farge());

        // bruker resepten 2 ganger
        res2.bruk();
        res2.bruk();
        System.out.println("Reit igjen etter bruk: " + res2.hentReit());


        System.out.println("--------------------------\n");


        // test av subklassen "Militaerresept"
        Militaerresept res3 = new Militaerresept(v3, navn3, pasientId3, reit3);

        System.out.println("---TEST AV MILITAERRESEPT---");
        System.out.println("ID: " + res3.hentId());
        System.out.println("Legemiddel: " + res3.hentLegemiddel());
        System.out.println("Lege: " + res3.hentLege());
        System.out.println("PasientID: " + res3.hentPasientId());
        System.out.println("Reit: " + res3.hentReit());
        System.out.println("FoerPris: " + res3.prisAaBetale() + " kr");
        System.out.println("Farge: " + res3.farge());

        // bruker resepten 2 ganger
        res3.bruk();
        res3.bruk();
        System.out.println("Reit igjen etter bruk: " + res3.hentReit());


        System.out.println("--------------------------\n");


        // test av subklassen "PResept"
        PResept res4 = new PResept(v4, navn4, pasientId4);

        System.out.println("---TEST AV P-Resept---");
        System.out.println("ID: " + res4.hentId());
        System.out.println("Legemiddel: " + res4.hentLegemiddel());
        System.out.println("Lege: " + res4.hentLege());
        System.out.println("PasientID: " + res4.hentPasientId());
        System.out.println("Reit: " + res4.hentReit());
        System.out.println("FoerPris: " + res4.prisAaBetale() + " kr");
        System.out.println("Farge: " + res4.farge());

        // bruker resepten 4 ganger (fordi PResept bare har 3)
        res4.bruk();
        res4.bruk();
        res4.bruk();
        res4.bruk();
        System.out.println("Reit igjen etter bruk: " + res4.hentReit());
        // selv etter 4 bruk av resepten skrives det ut at det er 0 igjen, pga maksgrensen paa 3

        System.out.println("--------------------------\n");

        // bruk av toString() paa resept 4
        System.out.println("Test av toString()-metode \n");
        System.out.println(res3.toString());

        System.out.println("--------------------------");

    }
}