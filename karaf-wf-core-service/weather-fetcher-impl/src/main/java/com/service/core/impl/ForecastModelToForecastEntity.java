package com.service.core.impl;

import com.service.core.persistence.impl.entity.Forecast;

public class ForecastModelToForecastEntity {

    public static Forecast convertToForecastEntity(com.service.core.model.Forecast forecastModel) {
        return Forecast.builder()
                .country(forecastModel.getCountry())
                .city(forecastModel.getCity())
                .date(forecastModel.getDate())
                .weather(WeatherModelToWeatherEntity.convertToWeatherEntity(forecastModel.getWeather()))
                .build();
    }
}
