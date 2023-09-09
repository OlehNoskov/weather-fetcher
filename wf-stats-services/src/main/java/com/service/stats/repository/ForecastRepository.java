package com.service.stats.repository;

import com.service.stats.entity.Forecast;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ForecastRepository extends CrudRepository<Forecast, Long> {
    @Query(value = "SELECT country, count(*) as count FROM forecast GROUP BY country ORDER by count desc LIMIT 5",
            nativeQuery = true)
    List<String> getCountries();

    @Query(value = "SELECT city, count(*) as count FROM forecast GROUP BY city ORDER by count desc LIMIT 5",
            nativeQuery = true)
    List<String> getCities();

    @Query(value = "SELECT city, count(*) as count FROM forecast WHERE country =:country " +
            "GROUP BY city ORDER by count desc LIMIT 5", nativeQuery = true)
    List<String> getCitiesByCountry(@Param("country") String country);

    List<Forecast> getForecastsByDateBetween(Date startDate, Date finishDate);

    List<Forecast> getForecastsByDateBefore(Date date);

    List<Forecast> getForecastsByDateAfter(Date date);
}
