package pl.com.b2d.cloning.xxx;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;

/**
 * Created by Łukasz Kucharski on 2016-09-26.
 */
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {

    public static final String CLUSTERS = "clusters";
    public static final String MONITORS = "monitors";

    private final Set<Cluster> clusters;
    private final Set<Monitor> monitors;

    @JsonCreator
    public Configuration(
            @JsonProperty(value = CLUSTERS, required = true) final Set<Cluster> clusters,
            @JsonProperty(value = MONITORS, required = true) final Set<Monitor> monitors) {
        this.clusters = clusters;
        this.monitors = monitors;
    }

    @JsonProperty(CLUSTERS)
    public Set<Cluster> getClusters() {
        return clusters;
    }

    @JsonProperty(MONITORS)
    public Set<Monitor> getMonitors() {
        return monitors;
    }
}
