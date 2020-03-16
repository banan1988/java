package pl.com.b2d.clonning;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by Łukasz Kucharski on 2016-12-20.
 */
@JsonPropertyOrder(alphabetic = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionBody {

    private long timestamp;
    private int status;
    private String error;
    private String exception;
    private String message;
    private String path;

}
