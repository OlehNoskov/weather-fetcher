package com.weather.fetcher.persistence;

import com.weather.fetcher.api.model.Forecast;
import com.weather.fetcher.api.model.Temperature;
import com.weather.fetcher.api.model.Weather;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class RepositoryImpl implements Repository {

    @PersistenceContext(unitName = "weather-hibernate")
    private EntityManager entityManager;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveForecast(Forecast forecast) {
        System.out.println("Save Forecast!");
        entityManager.persist(forecast);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveWeather(Weather weather) {
        System.out.println("Save Weather!");
        entityManager.persist(weather);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveTemperature(Temperature temperature) {
        System.out.println("Save Temperature!");
        entityManager.persist(temperature);
    }
}
