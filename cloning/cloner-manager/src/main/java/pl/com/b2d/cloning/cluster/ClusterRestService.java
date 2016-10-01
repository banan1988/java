package pl.com.b2d.cloning.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
@Service
public class ClusterRestService {

    private final ClusterService clusterService;
    private final ClusterAssembler clusterAssembler;

    @Autowired
    public ClusterRestService(ClusterService clusterService, ClusterAssembler clusterAssembler) {
        this.clusterService = clusterService;
        this.clusterAssembler = clusterAssembler;
    }

    public List<ClusterResource> getClusterResources() {
        return clusterAssembler.toResources(clusterService.getClusters());
    }

    public ClusterResource getClusterResource(String name) {
        return clusterAssembler.toResource(clusterService.getCluster(name));
    }

}
