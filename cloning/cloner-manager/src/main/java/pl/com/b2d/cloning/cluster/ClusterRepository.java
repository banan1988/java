package pl.com.b2d.cloning.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.com.b2d.cloning.configuration.Cluster;
import pl.com.b2d.cloning.configuration.Configuration;
import pl.com.b2d.cloning.configuration.ConfigurationDataSource;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Repository
public class ClusterRepository {

    private final ConfigurationDataSource dataSource;

    @Autowired
    public ClusterRepository(final ConfigurationDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<Cluster> findAll() {
        final List<Cluster> clusters = this.dataSource.read().getClusters();
        if (clusters == null) {
            return Collections.emptyList();
        }
        return clusters;
    }

    public Optional<Cluster> findOne(final String name) {
        final List<Cluster> clusters = this.dataSource.read().getClusters();

        if (clusters.stream().filter(m -> m.getName().equals(name)).count() > 1) {
            throw new RuntimeException("Found more than one record by name " + name);
        }
        return clusters.stream().filter(m -> m.getName().equals(name)).findFirst();
    }

    public Cluster save(final Cluster cluster) {
        final List<Cluster> clusters = this.dataSource.read().getClusters();
        if (clusters.contains(cluster)) {
            clusters.remove(cluster);
        }
        clusters.add(cluster);

        final Configuration oldConfiguration = this.dataSource.read();
        this.dataSource.save(new Configuration(clusters, oldConfiguration.getMonitors()));

        return cluster;
    }

//    <S extends T> S save(S entity);
//    (2)
//    T findOne(ID primaryKey);
//    (3)
//    Iterable<T> findAll();
//
//    Long count();
//    (4)
//    void delete(T entity);
//    (5)
//    boolean exists(ID primaryKey);

}
