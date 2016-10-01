package pl.com.b2d.cloning.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.com.b2d.cloning.configuration.Configuration;
import pl.com.b2d.cloning.configuration.ConfigurationDataSource;
import pl.com.b2d.cloning.configuration.Monitor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Repository
public class MonitorRepository {

    private final ConfigurationDataSource dataSource;

    @Autowired
    public MonitorRepository(final ConfigurationDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterable<Monitor> findAll() {
        final List<Monitor> monitors = this.dataSource.read().getMonitors();
        if (monitors == null) {
            return Collections.emptyList();
        }
        return monitors;
    }

    public Optional<Monitor> findOne(final String name) {
        final List<Monitor> monitors = this.dataSource.read().getMonitors();

        if (monitors.stream().filter(m -> m.getName().equals(name)).count() > 1) {
            throw new RuntimeException("Found more than one record by name " + name);
        }
        return monitors.stream().filter(m -> m.getName().equals(name)).findFirst();
    }

    public Monitor save(final Monitor monitor) {
        final List<Monitor> monitors = this.dataSource.read().getMonitors();
        if (monitors.contains(monitor)) {
            monitors.remove(monitor);
        }
        monitors.add(monitor);

        final Configuration oldConfiguration = this.dataSource.read();
        this.dataSource.save(new Configuration(oldConfiguration.getClusters(), monitors));

        return monitor;
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
