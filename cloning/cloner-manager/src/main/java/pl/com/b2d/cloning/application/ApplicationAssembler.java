package pl.com.b2d.cloning.application;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import pl.com.b2d.cloning.configuration.Application;
import pl.com.b2d.cloning.configuration.Host;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Component
public class ApplicationAssembler extends ResourceAssemblerSupport<Application, ApplicationResource> {

    public ApplicationAssembler() {
        super(ApplicationController.class, ApplicationResource.class);
    }

    @Override
    public ApplicationResource toResource(final Application application) {
        return createResourceWithId(application.getContextPath()+application.getListenPort(), application);
    }

    @Override
    protected ApplicationResource instantiateResource(final Application entity) {
        return new ApplicationResource(entity);
    }
}
