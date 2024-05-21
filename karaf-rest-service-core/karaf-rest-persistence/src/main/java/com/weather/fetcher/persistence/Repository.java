package com.weather.fetcher.persistence;

import com.weather.fetcher.api.entity.Forecast;
import com.weather.fetcher.api.entity.Temperature;
import com.weather.fetcher.api.entity.Weather;

public interface Repository {
    void saveForecast(Forecast forecast);

    void saveWeather(Weather weather);

    void saveTemperature(Temperature temperature);
}
