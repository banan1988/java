package com.example.nest;

/**
 * Created by Łukasz Kucharski on 2017-01-16.
 */
public class NestException extends Exception {
    public NestException(String s) {
        super(s);
    }

    public NestException(String s, Throwable ex) {
        super(s, ex);
    }
}
