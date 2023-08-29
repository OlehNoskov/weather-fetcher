package com.service.core;

import com.service.core.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class WfCoreServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WfCoreServicesApplication.class, args);
    }
}
