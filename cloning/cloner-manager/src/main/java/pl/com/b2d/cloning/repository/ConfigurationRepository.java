package pl.com.b2d.cloning.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.springframework.stereotype.Repository;
import pl.com.b2d.cloning.xxx.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Łukasz Kucharski on 2016-09-27.
 */
@Repository
public class ConfigurationRepository {

    private final String path;
    private final ObjectMapper mapper;

    public ConfigurationRepository() {
        this(Resources.getResource("configuration.json").getFile());
    }

    public ConfigurationRepository(final String path) {
        this(path, new ObjectMapper());
    }

    public ConfigurationRepository(final String path, final ObjectMapper mapper) {
        this.path = path;
        this.mapper = mapper;
    }

    public Configuration read() {
        try {
            return mapper.readValue(new File(path), Configuration.class);
        } catch (final IOException e) {
            throw new ConfigurationReadException(e);
        } catch (final Exception e) {
            throw new ConfigurationReadException(e);
        }
    }

    public void save(final Configuration configuration) {
        try {
            mapper.writeValue(new File(path), configuration);
        } catch (final IOException e) {
            throw new ConfigurationWriteException(e);
        } catch (final Exception e) {
            throw new ConfigurationWriteException(e);
        }
    }

}
