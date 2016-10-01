package pl.com.b2d.cloning.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
@Service
public class HostRestService {

    private final HostService hostService;
    private final HostAssembler hostAssembler;

    @Autowired
    public HostRestService(HostService hostService, HostAssembler hostAssembler) {
        this.hostService = hostService;
        this.hostAssembler = hostAssembler;
    }

    public List<HostResource> getHostResources() {
        return hostAssembler.toResources(hostService.getHosts());
    }

    public HostResource getHostResource(String name) {
        return hostAssembler.toResource(hostService.getHost(name));
    }

}
