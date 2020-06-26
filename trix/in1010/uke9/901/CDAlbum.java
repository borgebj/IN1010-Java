
class CDAlbum implements Comparable<CDAlbum> {

    // globale variabler for CDAlbum
    String Artistnavn;
    String Albumnavn;
    String Utgivelsesaar;

    public CDAlbum(String Artistnavn, String Albumnavn, String Utgivelsesaar) {
        this.Artistnavn = Artistnavn;
        this.Albumnavn = Albumnavn;
        this.Utgivelsesaar = Utgivelsesaar;
    }

    // returner artistnavnet
    public String hentArtistnavn() {
        return Artistnavn;
    }

    // returner albumnavn
    public String hentAlbumnavn() {
        return Albumnavn;
    }

    // returner utgivelsesaar
    public String hentUtgivelseaar() {
        return Utgivelsesaar;
    }

    @Override // overriding av toString-metode som skriver ut relevant info
    public String toString() {
        return ("["+Artistnavn +" - "+ Albumnavn +" ("+ Utgivelsesaar+") ]");
    }

    @Override  // egen compareTo metode som sammenligner Artistnavn
    public int compareTo(CDAlbum album) {
        String Artistnavn = album.hentArtistnavn();
        return (this.Artistnavn.compareTo(Artistnavn));
    }
}