package pl.com.b2d.cloning.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
@RestController
@RequestMapping("/v2/monitors")
public class MonitorController {

    private final MonitorRestService monitorRestService;

    @Autowired
    public MonitorController(MonitorRestService monitorRestService) {
        this.monitorRestService = monitorRestService;
    }

    @RequestMapping(method = GET)
    public List<MonitorResource> getMonitorResources() {
        return monitorRestService.getMonitorResources();
    }

    @RequestMapping(method = GET, path = "{name}")
    public MonitorResource getMonitorResource(@PathVariable String name) {
        return monitorRestService.getMonitorResource(name);
    }

}
