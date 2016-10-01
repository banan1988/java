package pl.com.b2d.cloning.xxx.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import pl.com.b2d.cloning.HostController;
import pl.com.b2d.cloning.xxx.Host;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Component
public class HostAssembler extends ResourceAssemblerSupport<Host, HostResource> {

    public HostAssembler() {
        super(HostController.class, HostResource.class);
    }

    @Override
    public HostResource toResource(final Host Host) {
        return createResourceWithId(Host.getFullName(), Host);
    }

    @Override
    protected HostResource instantiateResource(final Host entity) {
        return new HostResource(entity);
    }
}
