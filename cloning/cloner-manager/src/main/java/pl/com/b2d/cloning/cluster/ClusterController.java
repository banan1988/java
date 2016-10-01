package pl.com.b2d.cloning.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
@RestController
@RequestMapping("/v2/clusters")
public class ClusterController {

    private final ClusterRestService clusterRestService;

    @Autowired
    public ClusterController(ClusterRestService clusterRestService) {
        this.clusterRestService = clusterRestService;
    }

    @RequestMapping(method = GET)
    public List<ClusterResource> getClusterResources() {
        return clusterRestService.getClusterResources();
    }

    @RequestMapping(method = GET, path = "{name}")
    public ClusterResource getClusterResource(@PathVariable String name) {
        return clusterRestService.getClusterResource(name);
    }

}
