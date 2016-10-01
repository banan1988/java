package pl.com.b2d.cloning.xxx.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import pl.com.b2d.cloning.MonitorController;
import pl.com.b2d.cloning.xxx.Monitor;

/**
 * Created by Łukasz Kucharski on 2016-10-01.
 */
@Component
public class MonitorAssembler extends ResourceAssemblerSupport<Monitor, MonitorResource> {

    public MonitorAssembler() {
        super(MonitorController.class, MonitorResource.class);
    }

    @Override
    public MonitorResource toResource(final Monitor monitor) {
        return createResourceWithId(monitor.getName(), monitor);
    }

    @Override
    protected MonitorResource instantiateResource(final Monitor entity) {
        return new MonitorResource(entity);
    }
}
