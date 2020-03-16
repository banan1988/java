package com.example;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Łukasz Kucharski on 2016-11-04.
 */
@RestController
@RequestMapping("/post")
public class PostController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseObject main(@RequestBody RequestObject requestObject) {

        try {
            LOGGER.info("start");
            Utils.sleepSeconds(1, 5);
            String result = "ok post";
            if (!Strings.isNullOrEmpty(requestObject.getValue())) {
                result += " - " + requestObject.getValue();
            }
            return new ResponseObject(result);
        } catch (Exception e) {
            LOGGER.warn("{}", e.getMessage());
            throw e;
        } finally {
            LOGGER.info("end");
        }

    }

}
