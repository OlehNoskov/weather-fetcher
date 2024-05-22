package com.weather.fetcher.blueprint.mappers;

import com.weather.fetcher.api.entity.Temperature;
import com.weather.fetcher.api.model.TemperatureModel;

public class TemperatureModelToTemperatureEntityMapper {

    public static Temperature getTemperatureEntityFromTemperatureModel(TemperatureModel temperatureModel) {
        return Temperature.builder()
//                .scale(temperatureModel.getScale())
                .degrees(temperatureModel.getDegrees())
                .build();
    }
}
