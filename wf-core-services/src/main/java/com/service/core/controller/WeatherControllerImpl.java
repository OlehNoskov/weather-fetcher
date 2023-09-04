package com.service.core.controller;

import com.service.core.entity.Forecast;
import com.service.core.service.ForecastService;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class WeatherControllerImpl implements WeatherController {
    private final ForecastService forecastService;

    @Override
    public Forecast getWeatherByCityAndCountry(String country, String city) throws ParseException {
        return forecastService.getForecast(country, city);
    }
}
