package pl.com.b2d.cloning.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import pl.com.b2d.cloning.application.ApplicationAssembler;
import pl.com.b2d.cloning.configuration.Host;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Component
public class HostAssembler extends ResourceAssemblerSupport<Host, HostResource> {

    private final ApplicationAssembler applicationAssembler;

    @Autowired
    public HostAssembler(ApplicationAssembler applicationAssembler) {
        super(HostController.class, HostResource.class);
        this.applicationAssembler = applicationAssembler;
    }

    @Override
    public HostResource toResource(final Host host) {
        return createResourceWithId(host.getFullName(), host);
    }

    @Override
    protected HostResource instantiateResource(final Host entity) {
        return new HostResource(entity, applicationAssembler.toResources(entity.getApplications()));
    }
}
