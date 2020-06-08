
public interface Brukergrensesnitt {
    void giStatus();
    int beOmKommando(String spoersmaal, String[] alternativer);
    void sleep(long t); //egen-metode for aa skape korte stopp i programmet
}