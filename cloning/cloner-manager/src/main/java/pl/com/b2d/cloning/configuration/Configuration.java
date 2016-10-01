package pl.com.b2d.cloning.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by Łukasz Kucharski on 2016-09-26.
 */
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {

    public static final String CLUSTERS = "clusters";
    public static final String MONITORS = "monitors";

    private final List<Cluster> clusters;
    private final List<Monitor> monitors;

    @JsonCreator
    public Configuration(
            @JsonProperty(value = CLUSTERS, required = true) final List<Cluster> clusters,
            @JsonProperty(value = MONITORS, required = true) final List<Monitor> monitors) {
        this.clusters = clusters;
        this.monitors = monitors;
    }

    @JsonProperty(CLUSTERS)
    public List<Cluster> getClusters() {
        return clusters;
    }

    @JsonProperty(MONITORS)
    public List<Monitor> getMonitors() {
        return monitors;
    }
}
