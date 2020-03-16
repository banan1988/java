package pl.com.b2d.clonning.cluster;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Łukasz Kucharski on 2016-10-16.
 */
@RepositoryRestResource(path = "clustersV2")
public interface ClusterRepository extends MongoRepository<Cluster, String> {

    Cluster findByName(@Param("name") String name);

}
