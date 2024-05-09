package com.service.core.impl;

import com.service.core.persistence.impl.entity.Weather;
import com.service.core.persistence.impl.enums.OverallWeather;

public class WeatherModelToWeatherEntity {

    public static Weather convertToWeatherEntity(com.service.core.model.Weather weatherModel) {
        OverallWeather overallWeather = OverallWeather.getFromString(weatherModel.getOverall().name());

        return Weather.builder()
                .overall(overallWeather)
                .temperature(TemperatureModelToTemperatureEntity.convertToTemperatureEntity(weatherModel.getTemperature()))
                .build();
    }
}
