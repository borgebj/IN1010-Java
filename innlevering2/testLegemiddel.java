
class testLegemiddel {

    public static void main(String[] args) {

        // Narkotisk-objekt og test av metoder
        Narkotisk n1 = new Narkotisk("Cannabis", 149.90, 300.0, 4);
        System.out.println("\nNavn: " + n1.hentNavn() + ".");
        System.out.println("Pris: " + n1.hentPris() + " kr.");
        System.out.println("Mengde: " + n1.hentVirkestoff() + "mg.");
        System.out.println("Styrke: " + n1.hentNarkotiskStyrke() + "/5.");
        System.out.println("ID: " + n1.hentId() + ".\n");

        // Vanedannende-objekt og test av metoder
        Vanedannende vd1 = new Vanedannende("Zlpidem", 45.90, 100.0, 2);
        System.out.println("Navn: " + vd1.hentNavn() + ".");
        System.out.println("Pris: " + vd1.hentPris() + " kr.");
        System.out.println("Mengde: " + vd1.hentVirkestoff() + "mg.");
        System.out.println("Styrke: " + vd1.hentVanedannendeStyrke() + "/5.");
        System.out.println("ID: " + vd1.hentId() + ".\n");

        // Vanlig-objekt og test av metoder
        Vanlig v1 = new Vanlig("Paracet", 29.00, 500.0);
        System.out.println("Navn: " + v1.hentNavn() + ".");
        System.out.println("Pris: " + v1.hentPris() + " kr.");
        System.out.println("Mengde: " + v1.hentVirkestoff() + "mg.");
        System.out.println("ID: " + v1.hentId() + ".\n");

        System.out.println("\n- ENDRING AV PRIS -");

        // endring av pris
        System.out.println("Navn: " + v1.hentNavn() + ".");
        System.out.println("Gammel pris: " + v1.hentPris() + " kr.");
        v1.settNyPris(24.90);
        System.out.println("Ny pris: " + v1.hentPris() + " kr.\n");

        System.out.println("\n- toString() TEST -");

        // test av toString-metoden
        System.out.println(n1.toString()+"\n");
        System.out.println(vd1.toString()+"\n");
        System.out.println(v1.toString()+"\n");
    }
}