package com.service.stats.controller;

import com.service.stats.entity.Forecast;
import com.service.stats.entity.response.StatisticResponse;
import com.service.stats.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forecast")
public class ForecastController {
    private final ForecastService forecastService;

    @GetMapping("/countries")
    public List<StatisticResponse> getAllForecastsByCountry() {
        return forecastService.getCountries();
    }

    @GetMapping("/cities")
    public List<StatisticResponse> getAllForecastsByCity() {
        return forecastService.getCities();
    }

    @GetMapping("/country/{country}")
    public List<StatisticResponse> getCitiesByCountry(@PathVariable String country) {
        return forecastService.getCitiesByCountry(country);
    }

    @GetMapping("/date/between")
    public List<Forecast> getAllForecastsBetweenDates(@RequestParam("startDate")
                                                      @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate,
                                                      @RequestParam("finishDate")
                                                      @DateTimeFormat(pattern = "dd.MM.yyyy") Date finishDate) {
        return forecastService.getForecastsByDateBetween(startDate, finishDate);
    }

    @GetMapping("/date/before")
    public List<Forecast> getAllForecastsByDateBefore(@RequestParam("date")
                                                      @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        return forecastService.getForecastsByDateBefore(date);
    }

    @GetMapping("/date/after")
    public List<Forecast> getAllForecastsByDateAfter(@RequestParam("date")
                                                     @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        return forecastService.getForecastsByDateAfter(date);
    }
}
