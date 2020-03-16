package pl.com.b2d.clonning.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by BaNaN on 2015-09-04.
 */
@RestController
@RequestMapping("/v2/hosts")
public class HostController {

    private final HostService hostService;

    @Autowired
    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @RequestMapping
    public List<Host> listHosts() {
        return hostService.listHosts();
    }

    @RequestMapping("{hostDomain:.+}")
    public Host getHost(@PathVariable("hostDomain") String hostDomain) {
        return hostService.getHost(hostDomain);
    }

}
