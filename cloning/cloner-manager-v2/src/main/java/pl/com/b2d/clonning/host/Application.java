package pl.com.b2d.clonning.host;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Łukasz Kucharski on 2016-12-20.
 */
public class Application implements Serializable {

    private String contextPath;
    private int listenPort;

    public Application() {
    }

    public Application(String path) {
        String[] split = path.split(":");
        this.contextPath = split[0];
        this.listenPort = Integer.valueOf(split[1]);
    }

    public Application(String contextPath, int listenPort) {
        this.contextPath = contextPath;
        this.listenPort = listenPort;
    }

    public String get() {
        return contextPath + ":" + listenPort;
    }

    public String getContextPath() {
        return contextPath;
    }

    public int getListenPort() {
        return listenPort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application application = (Application) o;
        return listenPort == application.listenPort &&
                Objects.equals(contextPath, application.contextPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contextPath, listenPort);
    }
}
