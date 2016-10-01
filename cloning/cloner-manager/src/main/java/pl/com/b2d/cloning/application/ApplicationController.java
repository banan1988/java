package pl.com.b2d.cloning.application;

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
@RequestMapping("/v2/applications")
public class ApplicationController {

    private final ApplicationRestService applicationRestService;

    @Autowired
    public ApplicationController(ApplicationRestService applicationRestService) {
        this.applicationRestService = applicationRestService;
    }

    @RequestMapping(method = GET)
    public List<ApplicationResource> getApplicationResources() {
        return applicationRestService.getApplicationResources();
    }

    @RequestMapping(method = GET, path = "{name}")
    public ApplicationResource getApplicationResource(@PathVariable String name) {
        return applicationRestService.getApplicationResource(name);
    }

}
