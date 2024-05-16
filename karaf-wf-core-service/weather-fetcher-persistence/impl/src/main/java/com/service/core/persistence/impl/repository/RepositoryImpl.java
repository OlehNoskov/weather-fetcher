package com.service.core.persistence.impl.repository;

import com.service.core.persistence.impl.entity.Forecast;
import com.service.core.persistence.impl.entity.Temperature;
import com.service.core.persistence.impl.entity.Weather;

import lombok.Getter;

import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Getter
@Transactional
public class RepositoryImpl implements Repository {

    @PersistenceContext(unitName = "weather-hibernate")
    private EntityManager entityManager;

    @Override
    public void saveForecast(Forecast forecast) {
        entityManager.persist(forecast);
    }

    @Override
    public void saveTemperature(Temperature temperature) {
        entityManager.persist(temperature);
    }

    @Override
    public void saveWeather(Weather weather) {
        entityManager.persist(weather);
    }
}
