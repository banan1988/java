package pl.com.b2d.cloning.repository;

/**
 * Created by Łukasz Kucharski on 2016-09-27.
 */
public class ConfigurationReadException extends RuntimeException {
    public ConfigurationReadException(Exception e) {
        super(e);
    }
}
