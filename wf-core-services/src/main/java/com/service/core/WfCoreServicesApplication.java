package com.service.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WfCoreServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WfCoreServicesApplication.class, args);
    }
}
