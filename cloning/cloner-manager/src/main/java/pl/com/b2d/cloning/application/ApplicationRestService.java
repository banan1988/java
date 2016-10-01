package pl.com.b2d.cloning.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
@Service
public class ApplicationRestService {

    private final ApplicationService applicationService;
    private final ApplicationAssembler applicationAssembler;

    @Autowired
    public ApplicationRestService(ApplicationService applicationService, ApplicationAssembler applicationAssembler) {
        this.applicationService = applicationService;
        this.applicationAssembler = applicationAssembler;
    }

    public List<ApplicationResource> getApplicationResources() {
        return applicationAssembler.toResources(applicationService.getApplications());
    }

    public ApplicationResource getApplicationResource(String name) {
        return applicationAssembler.toResource(applicationService.getApplication(name));
    }

}
