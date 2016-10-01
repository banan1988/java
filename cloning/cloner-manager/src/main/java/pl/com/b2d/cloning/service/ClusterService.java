package pl.com.b2d.cloning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.b2d.cloning.repository.ConfigurationRepository;
import pl.com.b2d.cloning.xxx.Cluster;

import java.util.Set;

/**
 * Created by Łukasz Kucharski on 2016-09-27.
 */
@Service
public class ClusterService {

    private final ConfigurationRepository configurationRepository;

    @Autowired
    public ClusterService(final ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public Set<Cluster> getClusters() {
        return configurationRepository.read().getClusters();
    }

    public Cluster getCluster(final String name) {
        final Cluster cluster = configurationRepository.read().getClusters() //
                .stream().filter(c -> c.getName().equals(name)) //
                .findFirst().get();
        if (cluster == null) {
            throw new NotFoundException("Not found cluster by name: " + name);
        }
        return cluster;
    }

}
