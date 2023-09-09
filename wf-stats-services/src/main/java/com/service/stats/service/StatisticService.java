package com.service.stats.service;

import com.service.stats.entity.response.StatisticResponse;

import java.util.Date;
import java.util.List;

public interface StatisticService {
    List<StatisticResponse> getCountriesStatistic();

    List<StatisticResponse> getCitiesStatistic();

    List<StatisticResponse> getCitiesByCountryStatistic(String country);

    List<StatisticResponse> getCountriesByDateBetweenStatistic(Date startDate, Date finishDate);

    List<StatisticResponse> getCitiesByDateBetweenStatistic(Date startDate, Date finishDate);

    List<StatisticResponse> getCitiesByCountryAndDateBetweenStatistic(String country, Date startDate, Date finishDate);

}
