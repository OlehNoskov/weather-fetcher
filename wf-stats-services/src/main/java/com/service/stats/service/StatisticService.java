package com.service.stats.service;

import com.service.stats.repository.StatisticRepository;

import java.util.Date;
import java.util.List;

public interface StatisticService {
    List<StatisticRepository.Statistic> getCountriesStatistic();

    List<StatisticRepository.Statistic> getCitiesStatistic();

    List<StatisticRepository.Statistic> getCitiesByCountryStatistic(String country);

    List<StatisticRepository.Statistic> getCountriesByDateBetweenStatistic(Date startDate, Date finishDate);

    List<StatisticRepository.Statistic> getCitiesByDateBetweenStatistic(Date startDate, Date finishDate);

    List<StatisticRepository.Statistic> getCitiesByCountryAndDateBetweenStatistic(String country, Date startDate, Date finishDate);

}
