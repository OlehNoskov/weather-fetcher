package com.service.stats.service;

import com.service.stats.entity.Statistic;

import java.util.List;

public interface StatisticService {
    List<Statistic>  getCountriesStatistic(String interval);

    List<Statistic> getCitiesByCountryStatistic(String country, String interval);
}
