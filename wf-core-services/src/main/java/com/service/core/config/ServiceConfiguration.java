package com.service.core.config;

import com.service.core.service.ForecastService;
import com.service.core.service.WeatherClient;
import com.service.core.service.impl.ForecastServiceImpl;
import jakarta.xml.ws.Endpoint;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.bus.spring.SpringBus;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServiceConfiguration {
    private final WeatherClient weatherClient;

    @Bean
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), new ForecastServiceImpl(weatherClient));
        endpoint.publish("http://localhost:8080/weather");
        return endpoint;
    }
}
