
abstract class Rute {

    // variabel for kolonne, rad og labyrinten den er del av
    int kolonne;
    int rad;
    Labyrint minLabyrint;

    // nabo-ruter
    Rute naboNord;
    Rute naboSyd;
    Rute naboVest;
    Rute naboOst;

    // abstrakt metode som returner rutens tegnrepresentasjon
    abstract public char tilTegn();

}