package com.service.stats.service.impl;

import com.service.stats.entity.Forecast;
import com.service.stats.entity.response.StatisticResponse;
import com.service.stats.repository.ForecastRepository;
import com.service.stats.service.ForecastService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class ForecastServiceImpl implements ForecastService {
    private final ForecastRepository forecastRepository;
    private static final String COMMA = ",";
    private static final int INDEX_DATA = 0;
    private static final int INDEX_COUNT = 1;

    @Override
    public List<StatisticResponse> getCountries() {
        List<StatisticResponse> statisticCountries = new LinkedList<>();
        forecastRepository.getCountries().forEach(s -> statisticCountries.add(getStatisticResponse(s)));

        return statisticCountries;
    }

    @Override
    public List<StatisticResponse> getCities() {
        List<StatisticResponse> statisticCities = new LinkedList<>();
        forecastRepository.getCities().forEach(s -> statisticCities.add(getStatisticResponse(s)));

        return statisticCities;
    }

    @Override
    public List<StatisticResponse> getCitiesByCountry(String country) {
        List<StatisticResponse> statisticCities = new LinkedList<>();
        forecastRepository.getCitiesByCountry(country).forEach(s -> statisticCities.add(getStatisticResponse(s)));

        return statisticCities;
    }

    @Override
    public List<Forecast> getForecastsByDateBetween(Date startDate, Date finishDate) {
        return forecastRepository.getForecastsByDateBetween(startDate, finishDate);
    }

    @Override
    public List<Forecast> getForecastsByDateBefore(Date date) {
        return forecastRepository.getForecastsByDateBefore(date);
    }

    @Override
    public List<Forecast> getForecastsByDateAfter(Date date) {
        return forecastRepository.getForecastsByDateAfter(date);
    }

    private StatisticResponse getStatisticResponse(String data) {
        return StatisticResponse.builder().data(data.split(COMMA)[INDEX_DATA])
                .count(Integer.parseInt(data.split(COMMA)[INDEX_COUNT])).build();
    }
}
