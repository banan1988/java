package pl.com.b2d.clonning.cluster;

import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.com.b2d.clonning.host.Host;

import java.util.List;

/**
 * Created by Łukasz Kucharski on 2016-10-16.
 */
@Document(collection = "clusters")
public class Cluster {

    @Id
    private ObjectId id;
    @Version
    private Long version;

    @Indexed(unique = true)
    private String name;
    private boolean alwaysRunning;
    private boolean updaterEnabled;

    @DBRef
    private List<Host> hosts = Lists.newArrayList();

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAlwaysRunning() {
        return alwaysRunning;
    }

    public boolean isUpdaterEnabled() {
        return updaterEnabled;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void addHost(Host host) {
        hosts.add(host);
    }
}
