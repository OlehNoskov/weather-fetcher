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

    @GetMapping("/countries")
    public List<Statistic> getCountriesStatistic(@RequestParam(required = false) String interval) {
        return statisticService.getCountriesStatistic(interval);
    }

    @GetMapping("/cities")
    public List<Statistic> getCitiesStatistic(@RequestParam(required = false) String country,
                                              @RequestParam(required = false) String interval) {
        return statisticService.getCitiesByCountryStatistic(country, interval);
    }
}
