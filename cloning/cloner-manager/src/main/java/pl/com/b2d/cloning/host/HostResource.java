package pl.com.b2d.cloning.host;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import pl.com.b2d.cloning.application.ApplicationResource;
import pl.com.b2d.cloning.configuration.Host;

import java.util.List;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostResource extends ResourceSupport {

    private final String fullName;
    private final String monitorName;
    private final List<ApplicationResource> applications;

    public HostResource(Host host, List<ApplicationResource> applicationResources) {
        this(host.getFullName(), host.getMonitorName(), applicationResources);
    }

    @JsonCreator
    public HostResource(
            @JsonProperty final String fullName,
            @JsonProperty final String monitorName,
            @JsonProperty final List<ApplicationResource> applications) {
        this.fullName = fullName;
        this.monitorName = monitorName;
        this.applications = applications;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public List<ApplicationResource> getApplications() {
        return applications;
    }
}
