package pl.com.b2d.cloning.xxx.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import pl.com.b2d.cloning.xxx.Host;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Relation(value = "host", collectionRelation = "hosts")
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostResource extends ResourceSupport {

    private final String fullName;

    protected HostResource(final Host host) {
        this(host.getFullName());
    }

    @JsonCreator
    public HostResource(
            @JsonProperty final String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
