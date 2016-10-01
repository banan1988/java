package pl.com.b2d.cloning.service;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.b2d.cloning.repository.ConfigurationRepository;
import pl.com.b2d.cloning.xxx.Cluster;
import pl.com.b2d.cloning.xxx.Host;

import java.util.Set;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Service
public class HostService {

    private final ConfigurationRepository configurationRepository;

    @Autowired
    public HostService(final ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public Set<Host> getHosts() {
        Set<Host> hosts = Sets.newHashSet();
        for (Cluster cluster : configurationRepository.read().getClusters()) {
            hosts.addAll(cluster.getHosts());
        }
        return hosts;
    }

}
