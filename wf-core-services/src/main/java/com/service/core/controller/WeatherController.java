package com.service.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.core.config.ConfigProperties;
import com.service.core.entity.Forecast;
import com.service.core.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final ForecastService forecastService;
    private final ConfigProperties configProperties;

    @GetMapping("/weather/countries/{country}/cities/{city}")
    public Forecast getWeatherByCityAndCountry(@PathVariable String country,
                                               @PathVariable String city) throws JsonProcessingException, ParseException {
        String path = "/v1/current.json?key=%s&q=%s&q=%s";
        String weatherUrl =
                String.format(configProperties.getApi() + path, configProperties.getKey(), country, city);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(weatherUrl, HttpMethod.GET, requestEntity, String.class);

        return forecastService.getForecast(response.getBody());
    }
}
