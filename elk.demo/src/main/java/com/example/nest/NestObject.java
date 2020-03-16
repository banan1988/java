package com.example.nest;

/**
 * Created by Łukasz Kucharski on 2017-01-16.
 */
public class NestObject {

    private String nullField = null;

    public String nest(long randomNumber) throws NestException {
        try {
            return nestDeeper(randomNumber);
        } catch (Throwable ex) {
            throw new NestException("Nest Exception", ex);
        }
    }

    String nestDeeper(long randomNumber) throws NestException {
        if (randomNumber % 6 == 0 || randomNumber % 9 == 0) {
            return nullField.toLowerCase();
        }
        throw new NestException("Random exception " + randomNumber);
    }

}
