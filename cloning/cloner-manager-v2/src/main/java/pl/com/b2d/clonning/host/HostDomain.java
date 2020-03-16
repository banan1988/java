package pl.com.b2d.clonning.host;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Łukasz Kucharski on 2016-12-20.
 */
public class HostDomain implements Serializable {

    private String host;
    private String domain;

    public HostDomain() {
    }

    public HostDomain(String host, String domain) {
        this.host = host;
        this.domain = domain;
    }

    public String get() {
        return host + "." + domain;
    }

    public String getHost() {
        return host;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HostDomain that = (HostDomain) o;
        return Objects.equals(host, that.host) &&
                Objects.equals(domain, that.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, domain);
    }
}
