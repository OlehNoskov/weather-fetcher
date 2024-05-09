package com.service.core.impl;

import com.service.core.api.WeatherController;

import com.service.core.model.Forecast;
import com.service.core.service.ForecastService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class WeatherControllerImpl implements WeatherController {
    private final ForecastService forecastService;

    @Override
    public Forecast getWeatherByCityAndCountry(String country, String city) throws ParseException, IOException, URISyntaxException, InterruptedException {
        return forecastService.getForecast(country, city);
    }
}
