package org.weather.fetcher.repo;

import org.weather.fetcher.repo.model.WeatherStat;

import java.util.List;

public interface WeatherStatRepo {

    void save(WeatherStat ws);

    List<WeatherStat> getAllStats();

}
