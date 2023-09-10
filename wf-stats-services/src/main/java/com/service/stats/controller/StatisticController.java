package com.service.stats.controller;

import com.service.stats.entity.response.StatisticResponse;
import com.service.stats.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic")
public class StatisticController {
    private final StatisticService statisticService;
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String START_DATE = "startDate";
    private static final String FINISH_DATE = "finishDate";

    @GetMapping("/countries")
    public List<StatisticResponse> getCountriesStatistic() {
        return statisticService.getCountriesStatistic();
    }

    @GetMapping("/cities")
    public List<StatisticResponse> getCitiesStatistic() {
        return statisticService.getCitiesStatistic();
    }

    @GetMapping("/cities/{country}")
    public List<StatisticResponse> getCitiesByCountryStatistic(@PathVariable String country) {
        return statisticService.getCitiesByCountryStatistic(country);
    }

    @GetMapping("/countries/date/between")
    public List<StatisticResponse> getCountriesBetweenDatesStatistic(@RequestParam(START_DATE)
                                                                     @DateTimeFormat(pattern = DATE_PATTERN) Date startDate,
                                                                     @RequestParam(FINISH_DATE)
                                                                     @DateTimeFormat(pattern = DATE_PATTERN) Date finishDate) {
        return statisticService.getCountriesByDateBetweenStatistic(startDate, finishDate);
    }

    @GetMapping("/cities/date/between")
    public List<StatisticResponse> getCitiesBetweenDatesStatistic(@RequestParam(START_DATE)
                                                                  @DateTimeFormat(pattern = DATE_PATTERN) Date startDate,
                                                                  @RequestParam(FINISH_DATE)
                                                                  @DateTimeFormat(pattern = DATE_PATTERN) Date finishDate) {
        return statisticService.getCitiesByDateBetweenStatistic(startDate, finishDate);
    }

    @GetMapping("/cities/{country}/date/between")
    public List<StatisticResponse> getCitiesByCountryAndBetweenDatesStatistic(@PathVariable String country,
                                                                              @RequestParam(START_DATE)
                                                                              @DateTimeFormat(pattern = DATE_PATTERN) Date startDate,
                                                                              @RequestParam(FINISH_DATE)
                                                                              @DateTimeFormat(pattern = DATE_PATTERN) Date finishDate) {
        return statisticService.getCitiesByCountryAndDateBetweenStatistic(country, startDate, finishDate);
    }
}
