package pl.com.b2d.cloning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.b2d.cloning.repository.MonitorRepository;
import pl.com.b2d.cloning.xxx.Monitor;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Łukasz Kucharski on 2016-09-27.
 */
@Service
public class MonitorService {

    private final MonitorRepository monitorRepository;

    @Autowired
    public MonitorService(final MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public Set<Monitor> getMonitors() {
        return monitorRepository.getMonitors();
    }

    public Monitor getMonitor(final String name) {
        final Optional<Monitor> monitor = monitorRepository.getMonitor(name);
        if (monitor.isPresent()) {
            return monitor.get();
        }
        throw new NotFoundException("Not found monitor by name " + name + ".");
    }

    public Monitor save(final Monitor monitor) {
        return monitorRepository.save(monitor);
    }

}
