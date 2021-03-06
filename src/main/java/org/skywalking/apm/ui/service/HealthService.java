package org.skywalking.apm.ui.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skywalking.apm.ui.creator.UrlCreator;
import org.skywalking.apm.ui.tools.HttpClientTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pengys5
 */
@Service
public class HealthService {

    private Logger logger = LogManager.getFormatterLogger(HealthService.class);

    private Gson gson = new GsonBuilder().serializeNulls().create();

    @Autowired
    private UrlCreator UrlCreator;

    public JsonObject loadInstances(long timeBucket, String[] applicationIds) throws IOException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("timeBucket", String.valueOf(timeBucket)));
        for (String applicationId : applicationIds) {
            params.add(new BasicNameValuePair("applicationIds", applicationId));
        }

        String instancesLoadUrl = UrlCreator.compound("instance/health/applicationId");
        String instancesResponse = HttpClientTools.INSTANCE.get(instancesLoadUrl, params);

        logger.debug("load instances data: %s", instancesResponse);

        return gson.fromJson(instancesResponse, JsonObject.class);
    }

}
