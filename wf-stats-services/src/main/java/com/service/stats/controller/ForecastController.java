package com.service.stats.controller;

import com.service.stats.entity.Forecast;
import com.service.stats.repository.ForecastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forecast")
public class ForecastController {
    private final ForecastRepository forecastRepository;

    @GetMapping("/country/{country}")
    public List<Forecast> getAllForecastsByCountry(@PathVariable String country) {
        return forecastRepository.getForecastsByCountry(country);
    }

    @GetMapping("/city/{city}")
    public List<Forecast> getAllForecastsByCity(@PathVariable String city) {
        return forecastRepository.getForecastsByCity(city);
    }

    @GetMapping("/country/{country}/city/{city}")
    public List<Forecast> getAllForecastsByCountryAndCity(@PathVariable String country,
                                                          @PathVariable String city) {
        return forecastRepository.getForecastsByCountryAndCity(country, city);
    }

    @GetMapping("/date/between")
    public List<Forecast> getAllForecastsBetweenDates(@RequestParam("startDate")
                                                      @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate,
                                                      @RequestParam("finishDate")
                                                      @DateTimeFormat(pattern = "dd.MM.yyyy") Date finishDate) {
        return forecastRepository.getForecastsByDateBetween(startDate, finishDate);
    }

    @GetMapping("/date/before")
    public List<Forecast> getAllForecastsByDateBefore(@RequestParam("date")
                                                      @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        return forecastRepository.getForecastsByDateBefore(date);
    }

    @GetMapping("/date/after")
    public List<Forecast> getAllForecastsByDateAfter(@RequestParam("date")
                                                     @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        return forecastRepository.getForecastsByDateAfter(date);
    }

    @GetMapping("/all")
    public List<Forecast> getAllForecasts() {
        return (List<Forecast>) forecastRepository.findAll();
    }
}
