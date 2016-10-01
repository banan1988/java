package pl.com.b2d.cloning.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.com.b2d.cloning.xxx.Configuration;
import pl.com.b2d.cloning.xxx.Monitor;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Łukasz Kucharski on 2016-09-27.
 */
@Repository
public class MonitorRepository {

    private final ConfigurationRepository dataSource;

    @Autowired
    public MonitorRepository(final ConfigurationRepository dataSource) {
        this.dataSource = dataSource;
    }

    public Set<Monitor> getMonitors() {
        return dataSource.read().getMonitors();
    }

    public Optional<Monitor> getMonitor(final String name) {
        final Optional<Monitor> monitor = dataSource.read().getMonitors() //
                .stream().filter(m -> m.getName().equals(name)) //
                .findFirst();
        return monitor;
    }

    public Monitor save(final Monitor monitor) {
        final Configuration configuration = dataSource.read();
        final Set<Monitor> monitors = configuration.getMonitors();
        monitors.add(monitor);
        dataSource.save(new Configuration(configuration.getClusters(), monitors));
        return monitor;
    }

}
