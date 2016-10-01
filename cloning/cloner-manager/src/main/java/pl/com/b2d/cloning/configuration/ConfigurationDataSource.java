package pl.com.b2d.cloning.configuration;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
public interface ConfigurationDataSource {

    Configuration read();

    void save(final Configuration configuration);

}
