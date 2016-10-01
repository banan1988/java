package pl.com.b2d.cloning;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String s) {
        super(s);
    }
}
