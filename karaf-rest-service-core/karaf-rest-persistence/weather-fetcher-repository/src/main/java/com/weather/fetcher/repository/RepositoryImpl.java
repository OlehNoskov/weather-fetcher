package com.weather.fetcher.repository;


import com.weather.fetcher.entity.Forecast;
import com.weather.fetcher.entity.Temperature;
import com.weather.fetcher.entity.Weather;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class RepositoryImpl implements Repository {

    @PersistenceContext(unitName = "weather-hibernate")
    private EntityManager entityManager;

    public void saveForecast(Forecast forecast) {
        System.out.println("Save Forecast!");
        entityManager.persist(forecast);
    }

    public void saveWeather(Weather weather) {
        System.out.println("Save Weather!");
        entityManager.persist(weather);
    }

    public void saveTemperature(Temperature temperature) {
        System.out.println("Save Temperature!");
        entityManager.persist(temperature);
    }
}
