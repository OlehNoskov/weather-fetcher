package com.service.stats.service;

import com.service.stats.entity.Statistic;

import java.util.List;

public interface StatisticService {
    List<Statistic> getStatistic(String field);

    List<Statistic> getStatisticByInterval(String field, String interval);

    List<Statistic> getCitiesByCountryStatistic(String country);

    List<Statistic> getCitiesByCountryAndIntervalStatistic(String country, String interval);
}
