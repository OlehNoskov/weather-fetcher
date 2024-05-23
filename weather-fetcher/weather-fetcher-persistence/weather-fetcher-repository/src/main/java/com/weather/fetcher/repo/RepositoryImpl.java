package com.weather.fetcher.repo;

import com.weather.fetcher.repo.model.Forecast;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class RepositoryImpl implements Repository {

    @Getter
    @PersistenceContext(unitName = "weather-hibernate")
    private EntityManager entityManager;

    public void saveForecast(Forecast forecast) {
        System.out.println("Save Forecast!");
        entityManager.persist(forecast);
    }
}
