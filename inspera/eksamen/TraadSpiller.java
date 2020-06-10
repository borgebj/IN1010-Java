
public class TraadSpiller extends Robot {

    @Override
    public void giStatus() {
        //ignore
    }

    @Override
    protected void visValg(String[] alternativer) {
        //ignore
    }

    @Override
    public void sleep(long t) {
        // ignore
    }

    @Override
    protected void visRobotValg(int robotValg) {
        // ignore
    }

    @Override
    public void skrivInnhold(Skattkiste current) {
        // ignore
    }

    @Override
    public void informer(String feilType, String objekt, int formue, Gjenstand tatt) {
        // ignore
    }

    // ved aa override og ignorere koden i superklassens metoder, kvitter vi oss med
    // baade mye tekst, og Thread.sleep, slik at traadene faar utfoert saa fort som mulig
}