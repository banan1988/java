package pl.com.b2d.cloning.xxx.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import pl.com.b2d.cloning.xxx.Cluster;

import java.util.List;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClusterResource extends ResourceSupport {

    private final String name;
    private final boolean alwaysRunning;
    private final boolean updaterEnabled;
    private final List<HostResource> hosts;

    public ClusterResource(final Cluster cluster, List<HostResource> hosts) {
        this(cluster.getName(), cluster.isAlwaysRunning(), cluster.isUpdaterEnabled(), hosts);
    }

    @JsonCreator
    public ClusterResource(
            @JsonProperty final String name,
            @JsonProperty final boolean alwaysRunning,
            @JsonProperty final boolean updaterEnabled,
            @JsonProperty final List<HostResource> hosts) {
        this.name = name;
        this.alwaysRunning = alwaysRunning;
        this.updaterEnabled = updaterEnabled;
        this.hosts = hosts;
    }

    public String getName() {
        return name;
    }

    public boolean isAlwaysRunning() {
        return alwaysRunning;
    }

    public boolean isUpdaterEnabled() {
        return updaterEnabled;
    }

    public List<HostResource> getHosts() {
        return hosts;
    }
}
