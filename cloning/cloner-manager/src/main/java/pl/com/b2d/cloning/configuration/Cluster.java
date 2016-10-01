package pl.com.b2d.cloning.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;

import java.util.Set;

/**
 * Created by Łukasz Kucharski on 2016-09-26.
 */
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cluster {

    public static final String NAME = "name";
    public static final String ALWAYS_RUNNING = "alwaysRunning";
    public static final String UPDATER_ENABLED = "updaterEnabled";
    public static final String HOSTS = "hosts";

    private final String name;
    private final boolean alwaysRunning;
    private final boolean updaterEnabled;
    private final Set<Host> hosts;

    @JsonCreator
    public Cluster(
            @JsonProperty(value = NAME, required = true) final String name,
            @JsonProperty(value = ALWAYS_RUNNING, required = true) final boolean alwaysRunning,
            @JsonProperty(value = UPDATER_ENABLED, required = true) final boolean updaterEnabled,
            @JsonProperty(value = HOSTS, required = true) final Set<Host> hosts) {
        this.name = name;
        this.alwaysRunning = alwaysRunning;
        this.updaterEnabled = updaterEnabled;
        this.hosts = hosts;
    }

    @JsonProperty(NAME)
    public String getName() {
        return name;
    }

    @JsonProperty(ALWAYS_RUNNING)
    public boolean isAlwaysRunning() {
        return alwaysRunning;
    }

    @JsonProperty(UPDATER_ENABLED)
    public boolean isUpdaterEnabled() {
        return updaterEnabled;
    }

    @JsonProperty(HOSTS)
    public Set<Host> getHosts() {
        return hosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cluster cluster = (Cluster) o;
        return Objects.equal(name, cluster.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
