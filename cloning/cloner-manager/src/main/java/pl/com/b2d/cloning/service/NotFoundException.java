package pl.com.b2d.cloning.service;

/**
 * Created by Łukasz Kucharski on 2016-09-27.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String s) {
        super(s);
    }
}
