package com.example;


/**
 * Created by Łukasz Kucharski on 2017-01-16.
 */
public class MonitoringException extends RuntimeException {

    public MonitoringException(String s, Throwable ex) {
        super(s, ex);
    }
}