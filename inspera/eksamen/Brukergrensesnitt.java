
public interface Brukergrensesnitt {
    void giStatus();
    int beOmKommando(String spoersmaal, String[] alternativer);
    void sleep(long t); //egen-metode for aa skape korte stopp i programmet
    void skrivInnhold(Skattkiste current); // egen-metode for aa skrive ut innhold i kisten fra parameter
    void informer(String feilType, String ting, int tall, Gjenstand objekt); // egen metode for aa skrive ut informasjon til spiller
    void settSpiller(Spiller spiller); // metode for aa sette terminalen sin spiller i hver terminal/robot
}