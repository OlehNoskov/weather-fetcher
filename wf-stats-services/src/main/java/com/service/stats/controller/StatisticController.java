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
    public List<Statistic> getCountriesStatistic(@RequestParam(required = false) String interval) {
        return statisticService.getWeatherStatistic(COUNTRY_FIELD, "", interval);
    }

    @GetMapping("/cities")
    public List<Statistic> getCitiesStatistic(@RequestParam(required = false) String interval,
                                              @RequestParam(required = false) String country) {
        return statisticService.getWeatherStatistic(CITY_FIELD, country, interval);
    }
}
