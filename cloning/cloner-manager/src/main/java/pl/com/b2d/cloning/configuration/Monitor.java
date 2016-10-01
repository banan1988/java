package pl.com.b2d.cloning.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;

import java.net.URL;

/**
 * Created by Łukasz Kucharski on 2016-09-24.
 */
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Monitor {

    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String FORMAT = "format";

    private final String name;
    private final URL url;
    private final String format;

    @JsonCreator
    public Monitor(
            @JsonProperty(value = NAME, required = true) final String name,
            @JsonProperty(value = URL, required = true) final URL url,
            @JsonProperty(value = FORMAT, required = true) final String format) {
        this.name = name;
        this.url = url;
        this.format = format;
    }

    @JsonProperty(NAME)
    public String getName() {
        return name;
    }

    @JsonProperty(URL)
    public URL getUrl() {
        return url;
    }

    @JsonProperty(FORMAT)
    public String getFormat() {
        return format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitor monitor = (Monitor) o;
        return Objects.equal(name, monitor.name) &&
                Objects.equal(url, monitor.url);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, url);
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "name='" + name + '\'' +
                ", url=" + url +
                '}';
    }
}
