package com.service.core.service;

import com.service.core.entity.Forecast;

public interface ForecastService {
    Forecast getForecast(String country, String city);
}
