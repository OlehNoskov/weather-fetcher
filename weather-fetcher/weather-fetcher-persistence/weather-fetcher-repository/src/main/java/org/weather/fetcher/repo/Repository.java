package org.weather.fetcher.repo;

import org.weather.fetcher.repo.model.Forecast;

public interface Repository {
    void saveForecast(Forecast forecast);
}
