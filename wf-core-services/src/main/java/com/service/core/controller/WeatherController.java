package com.service.core.controller;

import com.service.core.entity.Forecast;
import com.service.core.service.ForecastService;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.text.ParseException;

@RequiredArgsConstructor
public class WeatherController {
    private final ForecastService forecastService;

    @GET
    @Path("/weather/countries/{country}/cities/{city}")
    public Forecast getWeatherByCityAndCountry(@PathParam("country") String country,
                                               @PathParam("city") String city) throws ParseException {
        return forecastService.getForecast(country, city);
    }
}
