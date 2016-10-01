package pl.com.b2d.cloning.repository;

/**
 * Created by Łukasz Kucharski on 2016-09-27.
 */
public class ConfigurationWriteException extends RuntimeException {
    public ConfigurationWriteException(Exception e) {
        super(e);
    }
}
