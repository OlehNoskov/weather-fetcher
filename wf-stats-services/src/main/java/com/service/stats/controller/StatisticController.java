package com.service.stats.controller;

import com.service.stats.entity.Statistic;
import com.service.stats.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic")
public class StatisticController {
    private final StatisticService statisticService;
    private static final String COUNTRY_FIELD = "country";
    private static final String CITY_FIELD = "city";

    @GetMapping("/countries")
    public List<Statistic> getCountriesStatistic() {
        return statisticService.getStatistic(COUNTRY_FIELD);
    }

    @GetMapping("/countries/interval")
    public List<Statistic> getCountriesByIntervalStatistic(@RequestParam String interval) {
        return statisticService.getStatisticByInterval(COUNTRY_FIELD, interval);
    }

    @GetMapping("/cities")
    public List<Statistic> getCitiesStatistic() {
        return statisticService.getStatistic(CITY_FIELD);
    }

    @GetMapping("/cities/interval")
    public List<Statistic> getCitiesByIntervalStatistic(@RequestParam String interval) {
        return statisticService.getStatisticByInterval(CITY_FIELD, interval);
    }

    @GetMapping("/cities/country")
    public List<Statistic> getCitiesByCountryStatistic(@RequestParam String country) {
        return statisticService.getCitiesByCountryStatistic(country);
    }

    @GetMapping("/cities/country/interval")
    public List<Statistic> getCitiesByCountryAndIntervalStatistic(@RequestParam String country,
                                                                  @RequestParam String interval) {
        return statisticService.getCitiesByCountryAndIntervalStatistic(country, interval);
    }
}
