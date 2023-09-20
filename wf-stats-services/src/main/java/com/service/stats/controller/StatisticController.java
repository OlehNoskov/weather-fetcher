package com.service.stats.controller;

import com.service.stats.repository.StatisticRepository;
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
    public List<StatisticRepository.Statistic> getCountriesStatistic(@RequestParam(required = false) String interval) {
        return statisticService.getCountriesStatistic(interval);
    }

    @GetMapping("/cities")
    public List<StatisticRepository.Statistic> getCitiesStatistic(@RequestParam(required = false) String country,
                                                                  @RequestParam(required = false) String interval) {
        return statisticService.getCitiesByCountryStatistic(country, interval);
    }
}
