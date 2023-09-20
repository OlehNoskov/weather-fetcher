package com.service.stats.repository;

import com.service.stats.entity.Forecast;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticRepository extends CrudRepository<Forecast, Long> {
    @Query(value = "SELECT country as data, count(*) as count FROM forecast GROUP BY country ORDER by count desc LIMIT 5",
            nativeQuery = true)
    List<Statistic> getCountriesStatistic();

    @Query(value = "SELECT country as data, count(*) as count FROM forecast WHERE DATE(date) >= CURDATE() - INTERVAL ? WEEK " +
            "GROUP BY country ORDER by count desc LIMIT 5", nativeQuery = true)
    List<Statistic> getCountriesWeekStatistic(int week);

    @Query(value = "SELECT country as data, count(*) as count FROM forecast WHERE DATE(date) >= CURDATE() - INTERVAL ? MONTH " +
            "GROUP BY country ORDER by count desc LIMIT 5", nativeQuery = true)
    List<Statistic> getCountriesMonthStatistic(int month);

    @Query(value = "SELECT city as data, count(*) as count FROM forecast GROUP BY city ORDER by count desc LIMIT 5",
            nativeQuery = true)
    List<Statistic> getCitiesStatistic();

    @Query(value = "SELECT city as data, count(*) as count FROM forecast WHERE country =:country " +
            "GROUP BY city ORDER by count desc LIMIT 5", nativeQuery = true)
    List<Statistic> getCitiesByCountryStatistic(@Param("country") String country);

    interface Statistic {
        String getData();

        Integer getCount();
    }
}
