package com.service.stats.service;

import com.service.stats.entity.Forecast;

import java.util.Date;
import java.util.List;

public interface ForecastService {
    List<Forecast> getForecastsByCountry(String country);

    List<Forecast> getForecastsByCity(String city);

    List<Forecast> getForecastsByCountryAndCity(String country, String city);

    List<Forecast> getForecastsByDateBetween(Date startDate, Date finishDate);

    List<Forecast> getForecastsByDateBefore(Date date);

    List<Forecast> getForecastsByDateAfter(Date date);
}
