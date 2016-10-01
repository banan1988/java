package pl.com.b2d.cloning.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.b2d.cloning.NotFoundException;
import pl.com.b2d.cloning.configuration.Application;

import java.util.List;
import java.util.Optional;

/**
 * Created by Łukasz Kucharski on 2016-10-02.
 */
@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(final ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> getApplications() {
        return (List<Application>) applicationRepository.findAll();
    }

    public Application getApplication(String name) {
        final Optional<Application> cluster = applicationRepository.findOne(name);
        if (cluster.isPresent()) {
            return cluster.get();
        }
        throw new NotFoundException("Not found host by name " + name);
    }

}
