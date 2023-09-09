package com.service.stats.service;

import com.service.stats.entity.Forecast;
import com.service.stats.entity.response.StatisticResponse;

import java.util.Date;
import java.util.List;

public interface ForecastService {
    List<StatisticResponse> getCountries();

    List<StatisticResponse> getCities();

    List<StatisticResponse> getCitiesByCountry(String country);

    List<Forecast> getForecastsByDateBetween(Date startDate, Date finishDate);

    List<Forecast> getForecastsByDateBefore(Date date);

    List<Forecast> getForecastsByDateAfter(Date date);
}
