package com.example;

import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Łukasz Kucharski on 2016-11-04.
 */
@RestController
@RequestMapping("/slow")
public class SlowController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlowController.class);

    @Value("${slow.sleep.max}")
    private long sleepMax;

    @GetMapping
    public String main() {

        try {
            LOGGER.info("start");
            for (int i = 0; i < 10000; i++) {
                search();
            }
            Utils.sleepSeconds(1, sleepMax);
            return "ok slow";
        } catch (Exception e) {
            LOGGER.warn("Error", e);
            return "error - " + e.getMessage() + "\n" + Throwables.getStackTraceAsString(e);
        } finally {
            LOGGER.info("end");
        }
    }

    private void search() throws InterruptedException {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (Thread.currentThread().isInterrupted()) {
                LOGGER.warn("Interrupted from the outside");
                throw new InterruptedException("Interrupted from the outside");
            }
            stackTraceElement.getClassName().matches("AnyClass");
        }
    }

}
