package com.service.stats.service;

import com.service.stats.entity.Statistic;

import java.util.List;

public interface StatisticService {
    List<Statistic> getWeatherStatistic(String field, String country, String interval);
}
