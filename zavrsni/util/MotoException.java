package zavrsni.util;

/**
 *
 * @author Denis
 */
public class MotoException extends Exception {

    private String poruka;

    public MotoException(String poruka) {
        super(poruka);
        this.poruka = poruka;
    }

    public String getPoruka() {
        return poruka;
    }

}
