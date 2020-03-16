package com.example;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Łukasz Kucharski on 2016-11-04.
 */
@RestController
@RequestMapping("/fast")
public class FastController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastController.class);

    @GetMapping
    public String main(@RequestParam(value = "queryParam", required = false) String queryParam) {
        try {
            LOGGER.info("start");
            Utils.sleepSeconds(1, 5);
            String result = "ok fast";
            if (!Strings.isNullOrEmpty(queryParam)) {
                result += "?" + queryParam;
            }
            return result;
        } catch (Exception e) {
            LOGGER.warn("{}", e.getMessage());
            throw e;
        } finally {
            LOGGER.info("end");
        }

    }

}
