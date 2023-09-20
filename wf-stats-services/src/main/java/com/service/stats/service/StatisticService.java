package com.service.stats.service;

import com.service.stats.repository.StatisticRepository;

import java.util.List;

public interface StatisticService {
    List<StatisticRepository.Statistic> getCountriesStatistic(String interval);

    List<StatisticRepository.Statistic> getCitiesByCountryStatistic(String country, String interval);
}
