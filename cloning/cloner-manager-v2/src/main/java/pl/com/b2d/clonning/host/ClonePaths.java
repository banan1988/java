package pl.com.b2d.clonning.host;

import com.google.common.collect.Sets;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * Created by Łukasz Kucharski on 2016-12-29.
 */
@Document
public class ClonePaths {

    private Set<String> allow = Sets.newHashSet();
    private Set<String> disallow = Sets.newHashSet();
    private Set<String> rewrite = Sets.newHashSet();

    public Set<String> getAllow() {
        return allow;
    }

    public Set<String> getDisallow() {
        return disallow;
    }

    public Set<String> getRewrite() {
        return rewrite;
    }
}
