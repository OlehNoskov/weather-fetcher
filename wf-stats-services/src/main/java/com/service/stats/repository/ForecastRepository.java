package com.service.stats.repository;

import com.service.stats.entity.Forecast;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ForecastRepository extends CrudRepository<Forecast, Long> {
    List<Forecast> getForecastsByCountry(String country);

    List<Forecast> getForecastsByCity(String city);

    List<Forecast> getForecastsByCountryAndCity(String country, String city);

    List<Forecast> getForecastsByDateBetween(Date startDate, Date finishDate);

    List<Forecast> getForecastsByDateBefore(Date date);
    List<Forecast> getForecastsByDateAfter(Date date);
}
