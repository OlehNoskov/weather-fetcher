package com.service.stats.service.impl;

import com.service.stats.entity.response.StatisticResponse;
import com.service.stats.repository.StatisticRepository;
import com.service.stats.service.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;
    private static final String COMMA = ",";
    private static final int INDEX_DATA = 0;
    private static final int INDEX_COUNT = 1;

    @Override
    public List<StatisticResponse> getCountriesStatistic() {
        List<StatisticResponse> statisticCountries = new LinkedList<>();
        statisticRepository.getCountriesStatistic().forEach(s -> statisticCountries.add(getStatisticResponse(s)));

        return statisticCountries;
    }

    @Override
    public List<StatisticResponse> getCitiesStatistic() {
        List<StatisticResponse> statisticCities = new LinkedList<>();
        statisticRepository.getCitiesStatistic().forEach(s -> statisticCities.add(getStatisticResponse(s)));

        return statisticCities;
    }

    @Override
    public List<StatisticResponse> getCitiesByCountryStatistic(String country) {
        List<StatisticResponse> statisticCities = new LinkedList<>();
        statisticRepository.getCitiesByCountryStatistic(country)
                .forEach(s -> statisticCities.add(getStatisticResponse(s)));

        return statisticCities;
    }

    @Override
    public List<StatisticResponse> getCountriesByDateBetweenStatistic(Date startDate, Date finishDate) {
        List<StatisticResponse> statisticCountries = new LinkedList<>();
        statisticRepository.getCountriesByDateBetweenStatistic(getSqlDate(startDate), getSqlDate(finishDate))
                .forEach(s -> statisticCountries.add(getStatisticResponse(s)));

        return statisticCountries;
    }

    @Override
    public List<StatisticResponse> getCitiesByDateBetweenStatistic(Date startDate, Date finishDate) {
        List<StatisticResponse> statisticCities = new LinkedList<>();
        statisticRepository.getCitiesByDateBetweenStatistic(getSqlDate(startDate), getSqlDate(finishDate))
                .forEach(s -> statisticCities.add(getStatisticResponse(s)));

        return statisticCities;
    }

    @Override
    public List<StatisticResponse> getCitiesByCountryAndDateBetweenStatistic(String country, Date startDate, Date finishDate) {
        List<StatisticResponse> statisticCities = new LinkedList<>();
        statisticRepository.getCitiesByCountryAndDateBetweenStatistic(country, getSqlDate(startDate), getSqlDate(finishDate))
                .forEach(s -> statisticCities.add(getStatisticResponse(s)));

        return statisticCities;
    }


    private StatisticResponse getStatisticResponse(String data) {
        return StatisticResponse.builder().data(data.split(COMMA)[INDEX_DATA])
                .count(Integer.parseInt(data.split(COMMA)[INDEX_COUNT])).build();
    }

    private java.sql.Date getSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }
}
