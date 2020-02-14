
class Hovedprogram {
    public static void main(String[] args) {

        // 3 objekter av Student-typen
        Student joakim = new Student("Joakim", 234, 79);
        Student kristin = new Student("Kristin", 34, 1);
        Student dag = new Student("Dag", 46325, 358);

        // for-lokke som legger til custom quiz-score for hver student 3 ganger
        for (int i=0; i<3; i++) {
            joakim.leggTilQuizScore((i+1)*1);
            kristin.leggTilQuizScore((i+i)*2);
            dag.leggTilQuizScore(i+i);
        }

        // variabler som inneholder navn til de 3 studentene
        String jNavn = joakim.hentNavn();
        String kNavn = kristin.hentNavn();
        String dNavn = dag.hentNavn();

        // skriver ut navn, totalscore og gjennomsnitt for hver Hund
        System.out.println();
        System.out.println(" - "+jNavn+" - ");
        System.out.println("Total: "+joakim.hentTotalScore());
        System.out.println("Gjennomsnitt: "+joakim.hentGjennomsnittligScore());
        System.out.println();
        System.out.println(" - "+kNavn+" - ");
        System.out.println("Total: "+kristin.hentTotalScore());
        System.out.println("Gjennomsnitt: "+kristin.hentGjennomsnittligScore());
        System.out.println();
        System.out.println(" - "+dNavn+" - ");
        System.out.println("Total: "+dag.hentTotalScore());
        System.out.println("Gjennomsnitt: "+dag.hentGjennomsnittligScore());
        System.out.println();
    }
}