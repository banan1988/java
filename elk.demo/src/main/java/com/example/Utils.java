package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Łukasz Kucharski on 2016-11-04.
 */
public final class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringController.class);

    private Utils() {
    }

    public static void sleepSeconds(long from, long to) {
        final long l = randomInRange(from, to);
        LOGGER.info("go sleepSeconds for {} seconds", l);
        try {
            Thread.sleep(l * 1000);
        } catch (InterruptedException e) {
            LOGGER.warn("{}", e.getMessage());
        }
    }

    public static long randomInRange(long from, long to) {
        return ThreadLocalRandom.current().nextLong(from, to);
    }

}
