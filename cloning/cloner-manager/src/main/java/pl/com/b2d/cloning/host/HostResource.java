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
    private final List<ApplicationResource> applications;

    public HostResource(Host host, List<ApplicationResource> applicationResources) {
        this(host.getFullName(), applicationResources);
    }

    @JsonCreator
    public HostResource(
            @JsonProperty final String fullName,
            @JsonProperty final List<ApplicationResource> applications) {
        this.fullName = fullName;
        this.applications = applications;
    }

    public String getFullName() {
        return fullName;
    }

    public List<ApplicationResource> getApplications() {
        return applications;
    }
}
