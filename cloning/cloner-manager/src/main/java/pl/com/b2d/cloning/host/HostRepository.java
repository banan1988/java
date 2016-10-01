package pl.com.b2d.cloning.host;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.com.b2d.cloning.configuration.Cluster;
import pl.com.b2d.cloning.configuration.Configuration;
import pl.com.b2d.cloning.configuration.ConfigurationDataSource;
import pl.com.b2d.cloning.configuration.Host;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Repository
public class HostRepository {

    private final ConfigurationDataSource dataSource;

    @Autowired
    public HostRepository(final ConfigurationDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<Host> findAll() {
        final List<Cluster> clusters = this.dataSource.read().getClusters();
        if (clusters == null) {
            return Collections.emptyList();
        }

        final List<Host> hosts = Lists.newArrayList();
        for (final Cluster cluster : clusters) {
            hosts.addAll(cluster.getHosts());
        }
        return hosts;
    }

    public Optional<Host> findOne(final String fullName) {
        final List<Host> hosts = (List<Host>) findAll();

        if (hosts.stream().filter(h -> h.getFullName().equals(fullName)).count() > 1) {
            throw new RuntimeException("Found more than one record by fullName " + fullName);
        }
        return hosts.stream().filter(h -> h.getFullName().equals(fullName)).findFirst();
    }

    public Host save(final Host host) {
        return null;
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
