package pl.com.b2d.cloning.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.b2d.cloning.NotFoundException;
import pl.com.b2d.cloning.configuration.Host;

import java.util.List;
import java.util.Optional;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
@Service
public class HostService {

    private final HostRepository hostRepository;

    @Autowired
    public HostService(final HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public List<Host> getHosts() {
        return (List<Host>) hostRepository.findAll();
    }

    public Host getHost(String name) {
        final Optional<Host> cluster = hostRepository.findOne(name);
        if (cluster.isPresent()) {
            return cluster.get();
        }
        throw new NotFoundException("Not found host by name " + name);
    }

}
