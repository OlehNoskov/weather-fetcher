package com.service.core;

import com.service.core.controller.WeatherControllerImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
@EnableFeignClients
public class WfCoreServicesApplication {

    @Bean
    public Server jaxrsServer(Bus bus, WeatherControllerImpl weatherController) {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setServiceBeans(Collections.singletonList(weatherController));
        endpoint.setAddress("/");
        return endpoint.create();
    }

    public static void main(String[] args) {
        SpringApplication.run(WfCoreServicesApplication.class, args);
    }
}
