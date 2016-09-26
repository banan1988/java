package pl.com.b2d.cloning;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.b2d.cloning.xxx.Application;
import pl.com.b2d.cloning.xxx.Cluster;
import pl.com.b2d.cloning.xxx.Configuration;
import pl.com.b2d.cloning.xxx.Host;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by Łukasz Kucharski on 2016-09-26.
 */
@RestController
@RequestMapping("/hosts")
public class HostController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File fileConfiguration = new File(Resources.getResource("configuration.json").getFile());

    @GetMapping
    public Set<Host> hosts() throws IOException {
        final Configuration configuration = objectMapper.readValue(fileConfiguration, Configuration.class);
        Set<Host> hosts = Sets.newHashSet();
        for (Cluster cluster : configuration.getClusters()) {
            hosts.addAll(cluster.getHosts());
        }
        return hosts;
    }

    @GetMapping("{fullName:.+}")
    public Host host(@PathVariable("fullName") String fullName) throws IOException {
        final Configuration configuration = objectMapper.readValue(fileConfiguration, Configuration.class);
        Set<Host> hosts = Sets.newHashSet();
        for (Cluster cluster : configuration.getClusters()) {
            hosts.addAll(cluster.getHosts());
        }
        Stream<Host> hostStream = hosts.stream().filter(host -> host.getFullName().equals(fullName));
        Host host = hostStream.findAny().get();
        return host;
    }

    @GetMapping("{fullName:.+}/applications")
    public Set<Application> applicationsOfHost(@PathVariable("fullName") String fullName) throws IOException {
        final Configuration configuration = objectMapper.readValue(fileConfiguration, Configuration.class);
        Set<Host> hosts = Sets.newHashSet();
        for (Cluster cluster : configuration.getClusters()) {
            hosts.addAll(cluster.getHosts());
        }
        Stream<Host> hostStream = hosts.stream().filter(host -> host.getFullName().equals(fullName));
        Host host = hostStream.findAny().get();
        return host.getApplications();
    }

}
