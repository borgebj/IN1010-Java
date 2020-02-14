
class EspressoMaskin {

    // instansvariabel som inneholder vannmengden i maskinen - starter paa 1000 ml
    int vannMengde = 1000;
    int antEspresso = 0;
    int antLungo = 0;

    // Lag espresso dersom det er nok vann
    public void lagEspresso() {
        if (vannMengde >= 40) {
            System.out.println("En espresso er laget");
            vannMengde -= 40;
            antEspresso++;
        } else {
            System.out.println("Det er ikke nok vann til en espresso");
        }
    }

    // Lag lungo dersom det er nok vann
    public void lagLungo() {
        if (vannMengde > 110) {
            System.out.println("En lungo er laget");
            vannMengde -= 110;
            antLungo++;
        } else {
            System.out.println("Det er ikke nok vann til en lungo");
        }
    }

        // Fyll på et gitt antall milliliter vann, dersom det er plass
        public void fyllVann( int ml){
            vannMengde += ml;
        }

        // Les av målestrekene på vanntanken og tilgjengelig vann i ml
        public int hentVannmengde() {
            return vannMengde;
        }

        public String hentAntall() {
            return ("Lungo: "+antLungo)+("\nEspresso: "+antEspresso)+("\nVannmengde: "+vannMengde+" ml");
        }
    }