package org.kumar.primestarter.rest;

import org.kumar.primestarter.dto.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @Autowired
    private Environment env;

    @GetMapping("/ping")
    Metadata ping() {
        String instanceId = env.getProperty("instance.id");
        Metadata metadata = new Metadata();
        metadata.setInstanceId(instanceId == null || instanceId.isEmpty() ? "NOT DEFINED" : instanceId);
        return metadata;
    }

}
