package com.weather.fetcher.repo;


import com.weather.fetcher.repo.model.Forecast;

public interface Repository {
    void saveForecast(Forecast forecast);
}
