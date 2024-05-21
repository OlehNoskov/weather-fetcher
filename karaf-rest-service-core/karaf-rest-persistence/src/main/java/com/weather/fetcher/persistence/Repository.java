package com.weather.fetcher.persistence;

import com.weather.fetcher.api.model.Forecast;
import com.weather.fetcher.api.model.Temperature;
import com.weather.fetcher.api.model.Weather;

public interface Repository {
    void saveForecast(Forecast forecast);

    void saveWeather(Weather weather);

    void saveTemperature(Temperature temperature);
}
