package com.weather.fetcher.blueprint.service.impl;

//import com.weather.fetcher.persistence.Repository;
import com.weather.fetcher.api.enums.OverallWeather;
import com.weather.fetcher.api.enums.Scale;
import com.weather.fetcher.api.model.Forecast;
import com.weather.fetcher.api.model.Temperature;
import com.weather.fetcher.api.model.Weather;
import com.weather.fetcher.blueprint.response.ForecastResponse;
import com.weather.fetcher.blueprint.service.ForecastResponseService;
import com.weather.fetcher.blueprint.service.WeatherClientService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class ForecastResponseServiceImpl implements ForecastResponseService {
//    private final Repository repository;

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public Forecast getForecast(String country, String city) throws ParseException, IOException, URISyntaxException, InterruptedException {
        ForecastResponse forecastResponse = WeatherClientService.getForecast(country, city);
        Forecast forecast = Forecast.builder()
                .country(forecastResponse.getLocationResponse().getCountry())
                .city(forecastResponse.getLocationResponse().getCity())
                .date(getDate(forecastResponse))
                .weather(getWeather(forecastResponse))
                .build();

//        repository.saveForecast(forecast);

        return forecast;
    }

    private Weather getWeather(ForecastResponse forecast) {
        Temperature temperature = Temperature
                .builder()
                .scale(Scale.CELSIUS)
                .degrees((int) Math.round(Double.parseDouble(forecast.getWeatherResponse().getTemperature())))
                .build();

//        repository.saveTemperature(temperature);

        Weather weather = Weather
                .builder()
                .overall(OverallWeather.getFromString(forecast
                        .getWeatherResponse()
                        .getOverallWeatherResponse()
                        .getOverallWeather()))
                .temperature(temperature)
                .build();

//        repository.saveWeather(weather);

        return weather;
    }

    private Date getDate(ForecastResponse forecastResponse) throws ParseException {
        return DATE_FORMAT.parse(forecastResponse.getWeatherResponse().getUpdateDate());
    }
}
