package com.service.stats.service.impl;

import com.service.stats.entity.Forecast;
import com.service.stats.repository.ForecastRepository;
import com.service.stats.service.ForecastService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ForecastServiceImpl implements ForecastService {
    private final ForecastRepository forecastRepository;

    @Override
    public List<Forecast> getForecastsByCountry(String country) {
        return forecastRepository.getForecastsByCountry(country);
    }

    @Override
    public List<Forecast> getForecastsByCity(String city) {
        return forecastRepository.getForecastsByCity(city);
    }

    @Override
    public List<Forecast> getForecastsByCountryAndCity(String country, String city) {
        return forecastRepository.getForecastsByCountryAndCity(country, city);
    }

    @Override
    public List<Forecast> getForecastsByDateBetween(Date startDate, Date finishDate) {
        return forecastRepository.getForecastsByDateBetween(startDate, finishDate);
    }

    @Override
    public List<Forecast> getForecastsByDateBefore(Date date) {
        return forecastRepository.getForecastsByDateBefore(date);
    }

    @Override
    public List<Forecast> getForecastsByDateAfter(Date date) {
        return forecastRepository.getForecastsByDateAfter(date);
    }
}
