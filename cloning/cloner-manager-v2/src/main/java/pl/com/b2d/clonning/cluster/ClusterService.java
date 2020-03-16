package pl.com.b2d.clonning.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Łukasz Kucharski on 2016-10-20.
 */
@Service
@Transactional
public class ClusterService {

    private final ClusterRepository clusterRepository;

    @Autowired
    public ClusterService(ClusterRepository clusterRepository) {
        this.clusterRepository = clusterRepository;
    }

    public List<Cluster> listClusters() {
        return clusterRepository.findAll();
    }

    public Cluster getCluster(final String name) {
        Cluster cluster = clusterRepository.findByName(name);
        if (cluster == null) {
            throw new RuntimeException("Not found Cluster by name: " + name);
        }
        return cluster;
    }

    public Cluster save(final Cluster cluster) {
        return clusterRepository.save(cluster);
    }
}
