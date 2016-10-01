package pl.com.b2d.cloning.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import pl.com.b2d.cloning.configuration.Application;

import java.util.Set;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationResource extends ResourceSupport {

    private final String contextPath;
    private final int listenPort;
    private final Set<String> allowUrlPaths;
    private final Set<String> disallowUrlPaths;
    private final Set<String> rewriteUrlPaths;
    private final double trafficRate;

    public ApplicationResource(Application application) {
        this(application.getContextPath(), application.getListenPort(),
                application.getAllowUrlPaths(), application.getDisallowUrlPaths(), application.getRewriteUrlPaths(),
                application.getTrafficRate());
    }

    @JsonCreator
    public ApplicationResource(
            @JsonProperty final String contextPath,
            @JsonProperty final int listenPort,
            @JsonProperty final Set<String> allowUrlPaths,
            @JsonProperty final Set<String> disallowUrlPaths,
            @JsonProperty final Set<String> rewriteUrlPaths,
            @JsonProperty final double trafficRate) {
        this.contextPath = contextPath;
        this.listenPort = listenPort;
        this.allowUrlPaths = allowUrlPaths;
        this.disallowUrlPaths = disallowUrlPaths;
        this.rewriteUrlPaths = rewriteUrlPaths;
        this.trafficRate = trafficRate;
    }

    public String getContextPath() {
        return contextPath;
    }

    public int getListenPort() {
        return listenPort;
    }

    public Set<String> getAllowUrlPaths() {
        return allowUrlPaths;
    }

    public Set<String> getDisallowUrlPaths() {
        return disallowUrlPaths;
    }

    public Set<String> getRewriteUrlPaths() {
        return rewriteUrlPaths;
    }

    public double getTrafficRate() {
        return trafficRate;
    }
}
