package pl.com.b2d.cloning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import pl.com.b2d.cloning.service.MonitorService;
import pl.com.b2d.cloning.xxx.Monitor;
import pl.com.b2d.cloning.xxx.resource.MonitorAssembler;
import pl.com.b2d.cloning.xxx.resource.MonitorResource;

import java.net.URI;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

/**
 * Created by Łukasz Kucharski on 2016-09-27.
 */
@RestController
@RequestMapping("/monitors")
public class MonitorController {

    private final MonitorService monitorService;
    private final MonitorAssembler monitorAssembler;

    @Autowired
    public MonitorController(final MonitorService monitorService, final MonitorAssembler monitorAssembler) {
        this.monitorService = monitorService;
        this.monitorAssembler = monitorAssembler;
    }

    @RequestMapping(method = GET)
    public List<MonitorResource> getMonitorResources() {
        return monitorAssembler.toResources(this.monitorService.getMonitors());
    }

    @RequestMapping(method = GET, path = "{name}")
    public MonitorResource getMonitorResource(@PathVariable("name") final String name) {
        return monitorAssembler.toResource(this.monitorService.getMonitor(name));
    }

    @PostMapping
    public ResponseEntity<Void> addMonitor(@RequestBody Monitor monitor) {
        final Monitor saved = monitorService.save(monitor);

        final URI location = MvcUriComponentsBuilder.
                fromMethodCall(
                        on(MonitorController.class).getMonitorResource(saved.getName())
                ).build().toUri();

        return ResponseEntity.created(location).build();
    }

}
