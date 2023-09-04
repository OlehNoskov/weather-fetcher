package com.service.core.config;

import com.service.core.controller.WeatherControllerImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class WebClientConfiguration {
    @Bean
    public Server jaxrsServer(Bus bus, WeatherControllerImpl weatherController) {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setServiceBeans(Collections.singletonList(weatherController));
        endpoint.setAddress("/");
        return endpoint.create();
    }
}
