package com.service.core.persistence.impl.repository;

import com.service.core.persistence.impl.entity.Forecast;
import com.service.core.persistence.impl.entity.Temperature;
import com.service.core.persistence.impl.entity.Weather;

public interface Repository {
    void saveForecast(Forecast forecast);

    void saveTemperature(Temperature temperature);

    void saveWeather(Weather weather);
}
