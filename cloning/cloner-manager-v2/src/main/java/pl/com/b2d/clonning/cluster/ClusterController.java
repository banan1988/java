package pl.com.b2d.clonning.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import pl.com.b2d.clonning.host.Host;
import pl.com.b2d.clonning.host.HostService;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

/**
 * Created by BaNaN on 2015-09-04.
 */
@RestController
@RequestMapping("/v2/clusters")
public class ClusterController {

    private final ClusterService clusterService;
    private final HostService hostService;

    @Autowired
    public ClusterController(ClusterService clusterService, HostService hostService) {
        this.clusterService = clusterService;
        this.hostService = hostService;
    }

    @RequestMapping
    public List<Cluster> listClusters() {
        return clusterService.listClusters();
    }

    @RequestMapping("{name}")
    public Cluster getCluster(@PathVariable("name") String name) {
        return clusterService.getCluster(name);
    }

    @RequestMapping("{name}/hosts")
    public List<Host> getHosts(@PathVariable("name") String name) {
        return clusterService.getCluster(name).getHosts();
    }

    @RequestMapping(value = "{name}/hosts", method = RequestMethod.POST)
    public ResponseEntity<Void> addHosts(@PathVariable("name") String name, @RequestBody Host host) {
        Host savedHost = hostService.save(host);

        Cluster cluster = clusterService.getCluster(name);
        cluster.addHost(savedHost);
        Cluster saved = clusterService.save(cluster);

        final URI location = MvcUriComponentsBuilder.
                fromMethodCall(
                        on(ClusterController.class).getHosts(saved.getName())
                ).build().toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addCluster(@RequestBody Cluster cluster) {
        final Cluster saved = clusterService.save(cluster);

        final URI location = MvcUriComponentsBuilder.
                fromMethodCall(
                        on(ClusterController.class).getCluster(saved.getName())
                ).build().toUri();

        return ResponseEntity.created(location).build();
    }

}
