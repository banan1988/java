package pl.com.b2d.cloning;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import pl.com.b2d.cloning.service.ClusterService;
import pl.com.b2d.cloning.xxx.Cluster;
import pl.com.b2d.cloning.xxx.Configuration;
import pl.com.b2d.cloning.xxx.Host;
import pl.com.b2d.cloning.xxx.resource.ClusterAssembler;
import pl.com.b2d.cloning.xxx.resource.ClusterResource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

/**
 * Created by Łukasz Kucharski on 2016-09-24.
 */
@RestController
@ExposesResourceFor(ClusterResource.class)
@RequestMapping("/clusters")
public class ClusterController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File fileConfiguration = new File(Resources.getResource("configuration.json").getFile());

    private final ClusterService clusterService;
    private final ClusterAssembler clusterAssembler;

    @Autowired
    public ClusterController(final ClusterService clusterService, final ClusterAssembler clusterAssembler) {
        this.clusterService = clusterService;
        this.clusterAssembler = clusterAssembler;
    }

    @RequestMapping(method = GET)
    public List<ClusterResource> getClusterResources() {
        return clusterAssembler.toResources(clusterService.getClusters());
    }

    @RequestMapping(method = GET, path = "{name}")
    public ClusterResource getClusterResource(@PathVariable("name") final String name) {
        return clusterAssembler.toResource(clusterService.getCluster(name));
    }

    @GetMapping("{clusterName}/hosts")
    public Set<Host> hostsOfCluster(@PathVariable("clusterName") String clusterName) throws IOException {
        final Configuration configuration = objectMapper.readValue(fileConfiguration, Configuration.class);
        Stream<Cluster> clusterStream = configuration.getClusters().stream().filter(cluster -> cluster.getName().equals(clusterName));
        Cluster cluster = clusterStream.findAny().get();
        return cluster.getHosts();
    }

    @PostMapping("{clusterName}/hosts")
    public ResponseEntity<Void> addHostToCluster(@RequestBody Host host) throws IOException {
        final Host saved = new Host("newFullName.domain.com", "monitor2", Sets.newHashSet());

        final URI location = MvcUriComponentsBuilder.
                fromMethodCall(
                        on(HostController.class).host(saved.getFullName())
                ).build().toUri();

        return ResponseEntity.created(location).build();
    }

}
