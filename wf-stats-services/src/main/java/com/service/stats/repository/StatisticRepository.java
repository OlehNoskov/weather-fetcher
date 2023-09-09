package com.service.stats.repository;

import com.service.stats.entity.Forecast;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StatisticRepository extends CrudRepository<Forecast, Long> {
    @Query(value = "SELECT country, count(*) as count FROM forecast GROUP BY country ORDER by count desc LIMIT 5",
            nativeQuery = true)
    List<String> getCountriesStatistic();

    @Query(value = "SELECT city, count(*) as count FROM forecast GROUP BY city ORDER by count desc LIMIT 5",
            nativeQuery = true)
    List<String> getCitiesStatistic();

    @Query(value = "SELECT city, count(*) as count FROM forecast WHERE country =:country " +
            "GROUP BY city ORDER by count desc LIMIT 5", nativeQuery = true)
    List<String> getCitiesByCountryStatistic(@Param("country") String country);

    @Query(value = "SELECT country, count(*) as count FROM forecast GROUP BY country ORDER by count desc LIMIT 5",
            nativeQuery = true)
    List<String> getCountriesByDateBetweenStatistic(Date startDate, Date finishDate);

    @Query(value = "SELECT country, count(*) as count FROM forecast GROUP BY country ORDER by count desc LIMIT 5",
            nativeQuery = true)
    List<String> getCitiesByDateBetweenStatistic(Date startDate, Date finishDate);

    @Query(value = "SELECT city, count(*) as count FROM forecast WHERE country =:country " +
            "GROUP BY city ORDER by count desc LIMIT 5",
            nativeQuery = true)
    List<String> getCitiesByCountryAndDateBetweenStatistic(@Param("country") String country, Date startDate, Date finishDate);
}
