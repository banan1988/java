package pl.com.b2d.cloning.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
@Service
public class MonitorRestService {

    private final MonitorService monitorService;
    private final MonitorAssembler monitorAssembler;

    @Autowired
    public MonitorRestService(MonitorService monitorService, MonitorAssembler monitorAssembler) {
        this.monitorService = monitorService;
        this.monitorAssembler = monitorAssembler;
    }

    public List<MonitorResource> getMonitorResources() {
        return monitorAssembler.toResources(monitorService.getMonitors());
    }

    public MonitorResource getMonitorResource(String name) {
        return monitorAssembler.toResource(monitorService.getMonitor(name));
    }

}
