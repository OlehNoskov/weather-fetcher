package com.service.stats.service.impl;

import com.service.stats.repository.StatisticRepository;
import com.service.stats.service.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;

    @Override
    public List<StatisticRepository.Statistic> getCountriesStatistic(String interval) {
        if (Objects.equals(interval, "")) {
            return statisticRepository.getCountriesStatistic();
        }
        String sqlInterval = interval.split(" ")[1];
        int count = Integer.parseInt(interval.split(" ")[0]);

        return sqlInterval.equals("WEEK") ? statisticRepository.getCountriesWeekStatistic(count)
                : statisticRepository.getCountriesMonthStatistic(count);
    }

    @Override
    public List<StatisticRepository.Statistic> getCitiesByCountryStatistic(String country, String interval) {
        if (Objects.equals(country, null)) {
            return statisticRepository.getCitiesStatistic();
        }
        return statisticRepository.getCitiesByCountryStatistic(country);
    }
}
