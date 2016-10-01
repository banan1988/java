package pl.com.b2d.cloning.configuration;

/**
 * Created by Łukasz Kucharski on 2016-09-27.
 */
public class ConfigurationSaveException extends RuntimeException {
    public ConfigurationSaveException(Exception e) {
        super(e);
    }
}
