package com.service.core.config;

import com.service.core.controller.WeatherControllerImpl;
import com.service.core.service.provider.ForecastMessageBodyWriter;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {

    @Bean
    public Server jaxrsServer(Bus bus, WeatherControllerImpl weatherController) {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setServiceBeans(Collections.singletonList(weatherController));
        endpoint.setAddress("/");
        endpoint.setProviders(List.of(new ForecastMessageBodyWriter()));
        return endpoint.create();
    }
}
