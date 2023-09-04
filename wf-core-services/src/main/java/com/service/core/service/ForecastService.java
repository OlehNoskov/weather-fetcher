package com.service.core.service;

import com.service.core.entity.Forecast;

import java.text.ParseException;

public interface ForecastService {
    Forecast getForecast(String country, String city) throws ParseException;
}
