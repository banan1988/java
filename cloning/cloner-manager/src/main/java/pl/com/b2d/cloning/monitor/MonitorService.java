package pl.com.b2d.cloning.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.b2d.cloning.NotFoundException;
import pl.com.b2d.cloning.configuration.Monitor;

import java.util.List;
import java.util.Optional;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
@Service
public class MonitorService {

    private final MonitorRepository monitorRepository;

    @Autowired
    public MonitorService(final MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public List<Monitor> getMonitors() {
        return (List<Monitor>) monitorRepository.findAll();
    }

    public Monitor getMonitor(String name) {
        final Optional<Monitor> monitor = monitorRepository.findOne(name);
        if (monitor.isPresent()) {
            return monitor.get();
        }
        throw new NotFoundException("Not found monitor by name " + name);
    }

}
