package org.skywalking.apm.ui.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skywalking.apm.ui.service.ApplicationService;
import org.skywalking.apm.ui.web.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengys5
 */
@RestController
public class ApplicationController extends ControllerBase {

    private Logger logger = LogManager.getFormatterLogger(HealthController.class);

    @Autowired
    private ApplicationService service;

    @GetMapping("/applications")
    public void load(@ModelAttribute("timeBucketType") String timeBucketType,
        @ModelAttribute("startTime") long startTime, @ModelAttribute("endTime") long endTime,
        HttpServletResponse response) throws IOException {

        logger.info("load applications, timeBucketType: %s, startTime: %s, endTime: %s", timeBucketType, startTime, endTime);
        reply(service.load(timeBucketType, startTime, endTime).toString(), response);
    }
}
