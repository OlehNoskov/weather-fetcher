package com.service.core.impl;

import com.service.core.enums.OverallWeather;
import com.service.core.enums.Scale;
import com.service.core.mapper.ForecastModelToForecastEntity;
import com.service.core.mapper.TemperatureModelToTemperatureEntity;
import com.service.core.mapper.WeatherModelToWeatherEntity;
import com.service.core.model.Forecast;
import com.service.core.model.Temperature;
import com.service.core.model.Weather;
import com.service.core.service.ForecastService;
import com.service.core.api.WeatherClient;
import com.service.core.response.ForecastResponse;
import com.service.core.persistence.impl.repository.Repository;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
public class ForecastServiceImpl implements ForecastService {
    private final WeatherClient weatherClient;
    private final Repository repository;

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @Override
    public Forecast getForecast(String country, String city) throws ParseException, IOException, URISyntaxException, InterruptedException {
        ForecastResponse forecastResponse = weatherClient.getForecast(country, city);

        Forecast forecast = Forecast.builder()
                .country(forecastResponse.getLocationResponse().getCountry())
                .city(forecastResponse.getLocationResponse().getCity())
                .date(getDate(forecastResponse))
                .weather(getWeather(forecastResponse))
                .build();

        com.service.core.persistence.impl.entity.Forecast forecastEntity = ForecastModelToForecastEntity.convertToForecastEntity(forecast);

        repository.saveForecast(forecastEntity);

        return forecast;
    }

    private Weather getWeather(ForecastResponse forecast) {
        Temperature temperature = Temperature
                .builder()
                .scale(Scale.CELSIUS)
                .degrees((int) Math.round(Double.parseDouble(forecast.getWeatherResponse().getTemperature())))
                .build();

        com.service.core.persistence.impl.entity.Temperature temperatureEntity = TemperatureModelToTemperatureEntity.convertToTemperatureEntity(temperature);

        repository.saveTemperature(temperatureEntity);

        Weather weather = Weather
                .builder()
                .overall(OverallWeather.getFromString(forecast
                        .getWeatherResponse()
                        .getOverallWeatherResponse()
                        .getOverallWeather()))
                .temperature(temperature)
                .build();

        com.service.core.persistence.impl.entity.Weather weatherEntity = WeatherModelToWeatherEntity.convertToWeatherEntity(weather);

        repository.saveWeather(weatherEntity);

        return weather;
    }

    private Date getDate(ForecastResponse forecastResponse) throws ParseException {
        return DATE_FORMAT.parse(forecastResponse.getWeatherResponse().getUpdateDate());
    }
}
