package com.weather.fetcher.api.mappers;

import com.weather.fetcher.api.model.WeatherModel;
import com.weather.fetcher.repository.entity.Weather;

public class WeatherModelToWeatherEntityMapper {

    public static Weather getWeatherEntityFromWeatherModel(WeatherModel weatherModel) {
        return Weather.builder()
//                .overall(weatherModel.getOverall())
                .temperature(TemperatureModelToTemperatureEntityMapper.getTemperatureEntityFromTemperatureModel(weatherModel.getTemperatureModel()))
                .build();
    }
}
