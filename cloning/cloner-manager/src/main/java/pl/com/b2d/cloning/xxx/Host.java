package pl.com.b2d.cloning.xxx;

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
public class Host {

    public static final String FULL_NAME = "fullName";
    public static final String MONITOR_NAME = "monitorName";
    public static final String APPLICATIONS = "applications";

    private final String fullName;
    private final String monitorName;
    private final Set<Application> applications;

    @JsonCreator
    public Host(
            @JsonProperty(value = FULL_NAME, required = true) final String fullName,
            @JsonProperty(value = MONITOR_NAME, required = true) final String monitorName,
            @JsonProperty(value = APPLICATIONS, required = true) final Set<Application> applications) {
        this.fullName = fullName;
        this.monitorName = monitorName;
        this.applications = applications;
    }

    @JsonProperty(FULL_NAME)
    public String getFullName() {
        return fullName;
    }

    @JsonProperty(MONITOR_NAME)
    public String getMonitorName() {
        return monitorName;
    }

    @JsonProperty(APPLICATIONS)
    public Set<Application> getApplications() {
        return applications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return Objects.equal(fullName, host.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fullName);
    }
}
