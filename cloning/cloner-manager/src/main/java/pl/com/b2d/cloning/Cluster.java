package pl.com.b2d.cloning;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.UUID;

/**
 * Created by Łukasz Kucharski on 2016-09-24.
 */
public class Cluster {

    private String name = UUID.randomUUID().toString();
    private Set<Host> hosts = Sets.newHashSet(new Host());

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Host> getHosts() {
        return hosts;
    }

    public void setHosts(Set<Host> hosts) {
        this.hosts = hosts;
    }
}
