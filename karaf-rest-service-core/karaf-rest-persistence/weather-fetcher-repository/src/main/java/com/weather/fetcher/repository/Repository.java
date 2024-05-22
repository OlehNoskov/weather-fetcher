package com.weather.fetcher.repository;

import com.weather.fetcher.entity.Forecast;
import com.weather.fetcher.entity.Temperature;
import com.weather.fetcher.entity.Weather;

public interface Repository {
    void saveForecast(Forecast forecast);

    void saveWeather(Weather weather);

    void saveTemperature(Temperature temperature);
}
