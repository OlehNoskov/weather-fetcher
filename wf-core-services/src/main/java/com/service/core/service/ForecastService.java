package com.service.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.core.entity.Forecast;

import java.text.ParseException;

public interface ForecastService {
    Forecast getForecast(String response) throws JsonProcessingException, ParseException;
}
