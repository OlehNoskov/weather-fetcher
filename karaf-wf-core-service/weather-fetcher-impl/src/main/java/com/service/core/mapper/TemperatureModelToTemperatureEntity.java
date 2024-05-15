package com.service.core.mapper;

import com.service.core.persistence.impl.Scale;
import com.service.core.persistence.impl.entity.Temperature;

public class TemperatureModelToTemperatureEntity {

    public static Temperature convertToTemperatureEntity(com.service.core.model.Temperature temperatureModel) {
        return Temperature.builder()
                .scale(Scale.CELSIUS)
                .degrees(temperatureModel.getDegrees())
                .build();
    }
}
