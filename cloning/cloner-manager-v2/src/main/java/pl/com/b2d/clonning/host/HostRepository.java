package pl.com.b2d.clonning.host;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Łukasz Kucharski on 2016-12-20.
 */
public interface HostRepository extends MongoRepository<Host, String> {

    Host findByHostDomain(HostDomain hostDomain);

}
