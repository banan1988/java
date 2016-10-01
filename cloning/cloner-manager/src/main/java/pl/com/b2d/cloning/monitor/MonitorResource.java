package pl.com.b2d.cloning.monitor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import pl.com.b2d.cloning.configuration.Monitor;

import java.net.URL;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MonitorResource extends ResourceSupport {

    private final String name;
    private final URL url;
    private final String format;

    protected MonitorResource(final Monitor monitor) {
        this(monitor.getName(), monitor.getUrl(), monitor.getFormat());
    }

    @JsonCreator
    public MonitorResource(
            @JsonProperty final String name,
            @JsonProperty final URL url,
            @JsonProperty final String format) {
        this.name = name;
        this.url = url;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public URL getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }
}
