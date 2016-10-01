package pl.com.b2d.cloning.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by Łukasz Kucharski on 2016-09-27.
 */
@Component
public class FileConfigurationDataSource implements ConfigurationDataSource {

    private final String path;
    private final ObjectMapper mapper;

    public FileConfigurationDataSource() {
        this(Resources.getResource("configuration.json").getFile());
    }

    public FileConfigurationDataSource(final String path) {
        this(path, new ObjectMapper());
    }

    public FileConfigurationDataSource(final String path, final ObjectMapper mapper) {
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
            throw new ConfigurationSaveException(e);
        } catch (final Exception e) {
            throw new ConfigurationSaveException(e);
        }
    }

}
