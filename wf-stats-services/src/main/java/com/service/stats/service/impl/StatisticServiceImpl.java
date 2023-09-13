package com.service.stats.service.impl;

import com.service.stats.repository.StatisticRepository;
import com.service.stats.service.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;

    @Override
    public List<StatisticRepository.Statistic> getCountriesStatistic() {
        return statisticRepository.getCountriesStatistic();
    }

    @Override
    public List<StatisticRepository.Statistic> getCitiesStatistic() {
        return statisticRepository.getCitiesStatistic();
    }

    @Override
    public List<StatisticRepository.Statistic> getCitiesByCountryStatistic(String country) {
        return statisticRepository.getCitiesByCountryStatistic(country);
    }

    @Override
    public List<StatisticRepository.Statistic> getCountriesByDateBetweenStatistic(Date startDate, Date finishDate) {
        return statisticRepository.getCountriesByDateBetweenStatistic(startDate, finishDate);
    }

    @Override
    public List<StatisticRepository.Statistic> getCitiesByDateBetweenStatistic(Date startDate, Date finishDate) {
        return statisticRepository.getCitiesByDateBetweenStatistic(startDate, finishDate);
    }

    @Override
    public List<StatisticRepository.Statistic> getCitiesByCountryAndDateBetweenStatistic(String country, Date startDate, Date finishDate) {
        return statisticRepository.getCitiesByCountryAndDateBetweenStatistic(country, startDate, finishDate);
    }
}
