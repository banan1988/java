package pl.com.b2d.cloning;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.b2d.cloning.service.HostService;
import pl.com.b2d.cloning.xxx.Application;
import pl.com.b2d.cloning.xxx.Cluster;
import pl.com.b2d.cloning.xxx.Configuration;
import pl.com.b2d.cloning.xxx.Host;
import pl.com.b2d.cloning.xxx.resource.HostAssembler;
import pl.com.b2d.cloning.xxx.resource.HostResource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Łukasz Kucharski on 2016-09-26.
 */
@RestController
@RequestMapping("/hosts")
public class HostController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File fileConfiguration = new File(Resources.getResource("configuration.json").getFile());

    private final HostService hostService;
    private final HostAssembler hostAssembler;

    @Autowired
    public HostController(final HostService hostService, final HostAssembler hostAssembler) {
        this.hostService = hostService;
        this.hostAssembler = hostAssembler;
    }

    @RequestMapping(method = GET)
    public List<HostResource> getHostResources() {
        return hostAssembler.toResources(hostService.getHosts());
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
