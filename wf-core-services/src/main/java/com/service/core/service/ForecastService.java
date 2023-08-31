package com.service.core.service;

import com.service.core.entity.Forecast;
import jakarta.jws.WebService;

import java.text.ParseException;

@WebService
public interface ForecastService {
    Forecast getForecast(String country, String city) throws ParseException;
}
