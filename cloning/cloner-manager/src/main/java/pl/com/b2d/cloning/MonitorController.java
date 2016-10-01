package pl.com.b2d.cloning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import pl.com.b2d.cloning.service.MonitorService;
import pl.com.b2d.cloning.xxx.Monitor;

import java.net.URI;
import java.util.Set;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

/**
 * Created by Łukasz Kucharski on 2016-09-27.
 */
@RestController
@RequestMapping("/monitors")
public class MonitorController {

    private final MonitorService monitorService;

    @Autowired
    public MonitorController(final MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @GetMapping
    public Set<Monitor> monitors() {
        return this.monitorService.getMonitors();
    }

    @GetMapping("{name}")
    public Monitor monitor(@PathVariable("name") final String name) {
        return this.monitorService.getMonitor(name);
    }

    @PostMapping
    public ResponseEntity<Void> addMonitor(@RequestBody Monitor monitor) {
        final Monitor saved = monitorService.save(monitor);

        final URI location = MvcUriComponentsBuilder.
                fromMethodCall(
                        on(MonitorController.class).monitor(saved.getName())
                ).build().toUri();

        return ResponseEntity.created(location).build();
    }

}
