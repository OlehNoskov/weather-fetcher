package org.weather.fetcher.repo;

import lombok.Getter;
import org.weather.fetcher.repo.model.Forecast;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class RepositoryImpl implements Repository {

    @Getter
    @PersistenceContext(unitName = "weather-hibernate")
    private EntityManager entityManager;

    public void saveForecast(Forecast forecast) {
        entityManager.persist(forecast);
    }
}
