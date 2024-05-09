package com.service.core.service;

import com.service.core.model.Forecast;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public interface ForecastService {
    Forecast getForecast(String country, String city) throws ParseException, IOException, URISyntaxException, InterruptedException;
}
