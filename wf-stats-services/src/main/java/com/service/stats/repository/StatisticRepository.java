package com.service.stats.repository;

import com.service.stats.entity.Forecast;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StatisticRepository extends CrudRepository<Forecast, Long> {
    @Query(value = "SELECT country as data, count(*) as count FROM forecast GROUP BY country ORDER by count desc LIMIT 5",
            nativeQuery = true)
    List<Statistic> getCountriesStatistic();

    @Query(value = "SELECT city as data, count(*) as count FROM forecast GROUP BY city ORDER by count desc LIMIT 5",
            nativeQuery = true)
    List<Statistic> getCitiesStatistic();

    @Query(value = "SELECT city as data, count(*) as count FROM forecast WHERE country =:country " +
            "GROUP BY city ORDER by count desc LIMIT 5", nativeQuery = true)
    List<Statistic> getCitiesByCountryStatistic(@Param("country") String country);

    @Query(value = "SELECT country as data, count(*) as count FROM forecast WHERE date >=:startDate " +
            "and date <=:finishDate  GROUP BY country ORDER by count desc LIMIT 5", nativeQuery = true)
    List<Statistic> getCountriesByDateBetweenStatistic(@Param("startDate") Date startDate, @Param("finishDate") Date finishDate);

    @Query(value = "SELECT city as data, count(*) as count FROM forecast WHERE date >=:startDate " +
            "and date <=:finishDate GROUP BY city ORDER by count desc LIMIT 5", nativeQuery = true)
    List<Statistic> getCitiesByDateBetweenStatistic(Date startDate, Date finishDate);

    @Query(value = "SELECT city as data, count(*) as count FROM forecast WHERE country =:country AND date >=:startDate " +
            "and date <=:finishDate GROUP BY city ORDER by count desc LIMIT 5", nativeQuery = true)
    List<Statistic> getCitiesByCountryAndDateBetweenStatistic(@Param("country") String country, Date startDate, Date finishDate);

    interface Statistic {
        String getData();
        Integer getCount();
    }
}
