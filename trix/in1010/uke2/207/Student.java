
class Student {

    // instansvariabler
    private String studentNavn;
    private int totScore;
    private int antQuiz;

    // konstruktor med 3 parameter, 2 int og 1 String - gir verdier til instansvariabler
    public Student(String navn, int score, int quiz) {
        studentNavn = navn;
        totScore = score;
        antQuiz = quiz;
    }

    public String hentNavn() {
        return studentNavn;
    }

    public void leggTilQuizScore(int score) {
        totScore += score;
        antQuiz++;
    }

    public int hentTotalScore() {
        return totScore;
    }

    public float hentGjennomsnittligScore() {
        float gjennomsnitt = totScore / antQuiz;
        return gjennomsnitt;
    }
}