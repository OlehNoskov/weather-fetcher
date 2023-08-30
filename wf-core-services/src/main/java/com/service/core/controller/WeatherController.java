package com.service.core.controller;

import com.service.core.entity.Forecast;
import com.service.core.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final ForecastService forecastService;

    @GetMapping("/weather/countries/{country}/cities/{city}")
    public Forecast getWeatherByCityAndCountry(@PathVariable String country,
                                               @PathVariable String city) {
        return forecastService.getForecast(country, city);
    }
}
