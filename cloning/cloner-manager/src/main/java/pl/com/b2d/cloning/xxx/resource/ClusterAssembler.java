package pl.com.b2d.cloning.xxx.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import pl.com.b2d.cloning.ClusterController;
import pl.com.b2d.cloning.xxx.Cluster;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Component
public class ClusterAssembler extends ResourceAssemblerSupport<Cluster, ClusterResource> {

    private final HostAssembler hostAssembler;

    @Autowired
    public ClusterAssembler(HostAssembler hostAssembler) {
        super(ClusterController.class, ClusterResource.class);
        this.hostAssembler = hostAssembler;
    }

    @Override
    public ClusterResource toResource(final Cluster cluster) {
        return  createResourceWithId(cluster.getName(), cluster);
    }

    @Override
    protected ClusterResource instantiateResource(final Cluster entity) {
        return new ClusterResource(entity, hostAssembler.toResources(entity.getHosts()));
    }
}
