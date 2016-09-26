package pl.com.b2d.cloning;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.b2d.cloning.xxx.Cluster;
import pl.com.b2d.cloning.xxx.Configuration;
import pl.com.b2d.cloning.xxx.Host;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by Łukasz Kucharski on 2016-09-24.
 */
@RestController
@RequestMapping("/clusters")
public class ClusterController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File fileConfiguration = new File(Resources.getResource("configuration.json").getFile());

    @GetMapping
    public Set<Cluster> clusters() throws IOException {
        final Configuration configuration = objectMapper.readValue(fileConfiguration, Configuration.class);
        return configuration.getClusters();
    }

    @GetMapping("{name}")
    public Cluster cluster(@PathVariable("name") final String name) throws IOException {
        final Configuration configuration = objectMapper.readValue(fileConfiguration, Configuration.class);
        Stream<Cluster> clusterStream = configuration.getClusters().stream().filter(cluster -> cluster.getName().equals(name));
        Cluster cluster = clusterStream.findAny().get();
        return cluster;
    }

    @GetMapping("{clusterName}/hosts")
    public Set<Host> hostsOfCluster(@PathVariable("clusterName") String clusterName) throws IOException {
        final Configuration configuration = objectMapper.readValue(fileConfiguration, Configuration.class);
        Stream<Cluster> clusterStream = configuration.getClusters().stream().filter(cluster -> cluster.getName().equals(clusterName));
        Cluster cluster = clusterStream.findAny().get();
        return cluster.getHosts();
    }

}
