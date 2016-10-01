package pl.com.b2d.cloning.host;

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
@RequestMapping("/v2/hosts")
public class HostController {

    private final HostRestService hostRestService;

    @Autowired
    public HostController(HostRestService hostRestService) {
        this.hostRestService = hostRestService;
    }

    @RequestMapping(method = GET)
    public List<HostResource> getHostResources() {
        return hostRestService.getHostResources();
    }

    @RequestMapping(method = GET, path = "{name:.+}")
    public HostResource getHostResource(@PathVariable String name) {
        return hostRestService.getHostResource(name);
    }

}
