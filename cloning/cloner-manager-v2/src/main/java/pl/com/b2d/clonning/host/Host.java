package pl.com.b2d.clonning.host;

import com.google.common.collect.Sets;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.util.Set;

/**
 * Created by Łukasz Kucharski on 2016-10-16.
 */
@Document(collection = "hosts")
public class Host {

    @Id
    private ObjectId id;
    @Version
    private Long version;

    @Indexed(unique = true)
    private HostDomain hostDomain;

    @Indexed(unique = true)
    private Application application = new Application();
    private ClonePaths clonePaths = new ClonePaths();
    private double trafficRate = 0.1;
    private Set<URL> targetHosts = Sets.newHashSet();

    private Monitor monitor;

    public ObjectId getId() {
        return id;
    }

    public HostDomain getHostDomain() {
        return hostDomain;
    }

    public Application getApplication() {
        return application;
    }

    public ClonePaths getClonePaths() {
        return clonePaths;
    }

    public double getTrafficRate() {
        return trafficRate;
    }

    public Set<URL> getTargetHosts() {
        return targetHosts;
    }

    public Monitor getMonitor() {
        return monitor;
    }
}