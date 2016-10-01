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
public class Application {

    public static final String CONTEXT_PATH = "contextPath";
    public static final String LISTEN_PORT = "listenPort";
    public static final String ALLOW_URL_PATHS = "allowUrlPaths";
    public static final String DISALLOW_URL_PATHS = "disallowUrlPaths";
    public static final String REWRITE_URL_PATHS = "rewriteUrlPaths";
    public static final String TRAFFIC_RATE = "trafficRate";

    private final String contextPath;
    private final int listenPort;
    private final Set<String> allowUrlPaths;
    private final Set<String> disallowUrlPaths;
    private final Set<String> rewriteUrlPaths;
    private final double trafficRate;

    @JsonCreator
    public Application(
            @JsonProperty(value = CONTEXT_PATH, required = true) final String contextPath,
            @JsonProperty(value = LISTEN_PORT, required = true) final int listenPort,
            @JsonProperty(value = ALLOW_URL_PATHS, required = true) final Set<String> allowUrlPaths,
            @JsonProperty(value = DISALLOW_URL_PATHS, required = true) final Set<String> disallowUrlPaths,
            @JsonProperty(value = REWRITE_URL_PATHS, required = true) final Set<String> rewriteUrlPaths,
            @JsonProperty(value = TRAFFIC_RATE, required = true) final double trafficRate) {
        this.contextPath = contextPath;
        this.listenPort = listenPort;
        this.allowUrlPaths = allowUrlPaths;
        this.disallowUrlPaths = disallowUrlPaths;
        this.rewriteUrlPaths = rewriteUrlPaths;
        this.trafficRate = trafficRate;
    }

    @JsonProperty(CONTEXT_PATH)
    public String getContextPath() {
        return contextPath;
    }

    @JsonProperty(LISTEN_PORT)
    public int getListenPort() {
        return listenPort;
    }

    @JsonProperty(ALLOW_URL_PATHS)
    public Set<String> getAllowUrlPaths() {
        return allowUrlPaths;
    }

    @JsonProperty(DISALLOW_URL_PATHS)
    public Set<String> getDisallowUrlPaths() {
        return disallowUrlPaths;
    }

    @JsonProperty(REWRITE_URL_PATHS)
    public Set<String> getRewriteUrlPaths() {
        return rewriteUrlPaths;
    }

    @JsonProperty(TRAFFIC_RATE)
    public double getTrafficRate() {
        return trafficRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return listenPort == that.listenPort &&
                Objects.equal(contextPath, that.contextPath);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(contextPath, listenPort);
    }
}
