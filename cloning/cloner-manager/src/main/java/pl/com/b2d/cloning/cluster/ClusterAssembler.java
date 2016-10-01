package pl.com.b2d.cloning.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import pl.com.b2d.cloning.configuration.Cluster;
import pl.com.b2d.cloning.configuration.Monitor;
import pl.com.b2d.cloning.host.HostAssembler;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Component
public class ClusterAssembler extends ResourceAssemblerSupport<Cluster, ClusterResource> {

    private final HostAssembler hostAssembler;

    @Autowired
    public ClusterAssembler(final HostAssembler hostAssembler) {
        super(ClusterController.class, ClusterResource.class);
        this.hostAssembler = hostAssembler;
    }

    @Override
    public ClusterResource toResource(final Cluster monitor) {
        return createResourceWithId(monitor.getName(), monitor);
    }

    @Override
    protected ClusterResource instantiateResource(final Cluster entity) {
        return new ClusterResource(entity, hostAssembler.toResources(entity.getHosts()));
    }
}
