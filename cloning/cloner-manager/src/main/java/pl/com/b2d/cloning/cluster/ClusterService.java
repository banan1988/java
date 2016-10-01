package pl.com.b2d.cloning.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.b2d.cloning.NotFoundException;
import pl.com.b2d.cloning.configuration.Cluster;

import java.util.List;
import java.util.Optional;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
@Service
public class ClusterService {

    private final ClusterRepository clusterRepository;

    @Autowired
    public ClusterService(final ClusterRepository clusterRepository) {
        this.clusterRepository = clusterRepository;
    }

    public List<Cluster> getClusters() {
        return (List<Cluster>) clusterRepository.findAll();
    }

    public Cluster getCluster(String name) {
        final Optional<Cluster> cluster = clusterRepository.findOne(name);
        if (cluster.isPresent()) {
            return cluster.get();
        }
        throw new NotFoundException("Not found cluster by name " + name);
    }

}
