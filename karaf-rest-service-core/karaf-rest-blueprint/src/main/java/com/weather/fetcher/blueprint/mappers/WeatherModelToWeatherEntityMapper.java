package com.weather.fetcher.blueprint.mappers;

import com.weather.fetcher.api.entity.Weather;
import com.weather.fetcher.api.model.WeatherModel;

public class WeatherModelToWeatherEntityMapper {

    public static Weather getWeatherEntityFromWeatherModel(WeatherModel weatherModel) {
        return Weather.builder()
                .overall(weatherModel.getOverall())
                .temperature(TemperatureModelToTemperatureEntityMapper.getTemperatureEntityFromTemperatureModel(weatherModel.getTemperatureModel()))
                .build();
    }
}
