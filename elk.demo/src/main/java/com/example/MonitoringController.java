package com.example;

import com.example.nest.NestException;
import com.example.nest.NestObject;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static com.example.Utils.randomInRange;
import static com.example.Utils.sleepSeconds;

/**
 * Created by Łukasz Kucharski on 2016-11-04.
 */
@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    private static final Logger LOGGER = LoggerFactory.getLogger("monitoring");

    @Value("${monitoring.randomly_throw_exception}")
    private boolean randomlyThrowException;

    @GetMapping("{name}")
    public String main(@PathVariable("name") String name) {
        Stopwatch mainTimer = Stopwatch.createStarted();
        String status = "OK";
        try {
            LOGGER.info("MONITORING_START;{}", name);

            taskToDo(name, 0);
            taskToDo(name, 1);

            randomlyThrowException();

            taskToDo(name, 2);
            taskToDo(name, 3);
            taskToDo(name, 4);

            jobToDo(name);
        } catch (Exception e) {
            LOGGER.warn("{}", e.getMessage());
            status = "ERROR";
            throw e;
        } finally {
            LOGGER.info("MONITORING_END;{};{};{}", name, status, mainTimer.stop().elapsed(TimeUnit.MILLISECONDS));
        }
        return status;
    }

    private void taskToDo(String name, int taskId) {
        Stopwatch timer = Stopwatch.createStarted();
        String taskName = "TaskName-" + taskId;
        try {
            LOGGER.info("MONITORING_TASK_TO_DO_START;{};{}", name, taskName);
            sleepSeconds(1, 10);
        } finally {
            LOGGER.info("MONITORING_TASK_TO_DO_END;{};{};{}", name, taskName, timer.stop().elapsed(TimeUnit.MILLISECONDS));
        }
    }

    private void jobToDo(String name) {
        Stopwatch timer = Stopwatch.createStarted();
        try {
            LOGGER.info("MONITORING_JOB_TO_DO_START;{}", name);
            sleepSeconds(5, 15);
        } finally {
            LOGGER.info("MONITORING_JOB_TO_DO_END;{};{}", name, timer.stop().elapsed(TimeUnit.MILLISECONDS));
        }

    }

    private void randomlyThrowException() {
        if (!randomlyThrowException)
            return;

        try {
            long randomNumber = randomInRange(1, 100);
            if (randomNumber % 3 == 0) {
                new NestObject().nest(randomNumber);
            }
        } catch (NestException ex) {
            throw new MonitoringException("Monitoring Exception", ex);
        }
    }

}
