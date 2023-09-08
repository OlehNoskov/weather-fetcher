package com.service.core.service;

import com.service.core.dto.response.ForecastResponse;

import java.text.ParseException;

public interface ForecastService {
    ForecastResponse getForecast(String country, String city) throws ParseException;
}
