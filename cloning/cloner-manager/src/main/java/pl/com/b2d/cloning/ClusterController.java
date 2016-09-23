package pl.com.b2d.cloning;

import com.google.common.collect.Sets;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Łukasz Kucharski on 2016-09-24.
 */
@RestController
@RequestMapping("/clusters")
public class ClusterController {

    @GetMapping
    public Set<Cluster> getClusters() {
        return Sets.newHashSet(new Cluster(), new Cluster());
    }

}
