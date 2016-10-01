package pl.com.b2d.cloning.monitor;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import pl.com.b2d.cloning.configuration.Monitor;

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
