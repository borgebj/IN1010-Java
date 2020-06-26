
class TrixTestProgram {
    public static void main(String[] args) {

        TrixSkyskraper bygningEn = new TrixSkyskraper();


    }
}

// a)
// programmet skriver ut begge kontruktorene, fordi vi oppretter et objekt av subklassen til TrixBygning, som dermed har 2 konstruktorer (super, og sub)

// b)
// legger vi til super() i subklassen kommer fremdeles samme to linjer, fordi super() kaller paa super sin konstruktor, som dermed har teksten fra forste konstruktor.

// c)
// for aa bruke parametere fra konstruktoren til superklassen maa man da ha med disse parametere i kall paa super(), i dette tilfellet maa vi da ha super(bruttoAreal);