package pl.com.b2d.cloning.manager;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

/**
 * Created by Łukasz Kucharski on 2016-09-22.
 */
public class Main {

    public static void main(String[] args) {
        // Configure Spark
        port(8080);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);

        get("/hello", (req, res) -> "Hello World");
    }

}
