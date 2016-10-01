package pl.com.b2d.cloning.application;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.com.b2d.cloning.configuration.Application;
import pl.com.b2d.cloning.configuration.Cluster;
import pl.com.b2d.cloning.configuration.ConfigurationDataSource;
import pl.com.b2d.cloning.configuration.Host;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Repository
public class ApplicationRepository {

    private final ConfigurationDataSource dataSource;

    @Autowired
    public ApplicationRepository(final ConfigurationDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<Application> findAll() {
        final List<Cluster> clusters = this.dataSource.read().getClusters();
        if (clusters == null) {
            return Collections.emptyList();
        }

        final List<Host> hosts = Lists.newArrayList();
        for (final Cluster cluster : clusters) {
            hosts.addAll(cluster.getHosts());
        }

        final List<Application> applications = Lists.newArrayList();
        for (Host host : hosts) {
            applications.addAll(host.getApplications());
        }
        return applications;
    }

    public Optional<Application> findOne(final String fullName) {
        final List<Application> applications = (List<Application>) findAll();

        if (applications.stream().filter(h -> h.getContextPath().equals(fullName)).count() > 1) {
            throw new RuntimeException("Found more than one record by fullName " + fullName);
        }
        return applications.stream().filter(h -> h.getContextPath().equals(fullName)).findFirst();
    }

    public Application save(final Application host) {
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
