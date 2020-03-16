package pl.com.b2d.clonning.host;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;

/**
 * Created by Łukasz Kucharski on 2016-10-16.
 */
@Document
public class Monitor {

    @Id
    private ObjectId id;
    @Version
    private Long version;

    private URL url;
    private String backendName;
    private String format;

    public URL getUrl() {
        return url;
    }

    public String getBackendName() {
        return backendName;
    }

    public String getFormat() {
        return format;
    }

}
