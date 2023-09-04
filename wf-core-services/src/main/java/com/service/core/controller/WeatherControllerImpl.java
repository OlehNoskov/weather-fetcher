package com.service.core.controller;

import com.service.core.entity.Forecast;
import com.service.core.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class WeatherControllerImpl implements WeatherController {
    private final ForecastService forecastService;

    //    @Override
//    public Forecast getWeatherByCityAndCountry(String country, String city) throws ParseException {
//        return forecastService.getForecast(country, city);
//    }
    @Override
    public Forecast getWeatherByCityAndCountry() throws ParseException {
        Forecast forecast = new Forecast();
        forecast.setCountry("Ukraine");
        return forecast;
    }
}
