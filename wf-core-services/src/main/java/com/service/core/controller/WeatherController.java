package com.service.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.core.entity.Forecast;
import com.service.core.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${weather.api.key}")
    private String weatherKey;

    @Value("${weather.api.url}")
    private String weatherAPI;

    @GetMapping("/weather/countries/{country}/cities/{city}")
    public Forecast getWeatherByCityAndCountry(@PathVariable String country,
                                               @PathVariable String city) throws JsonProcessingException, ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String weatherUrl = String.format(weatherAPI, weatherKey, country, city);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(weatherUrl, HttpMethod.GET, requestEntity, String.class);
        return forecastService.getForecast(response.getBody());
    }
}
