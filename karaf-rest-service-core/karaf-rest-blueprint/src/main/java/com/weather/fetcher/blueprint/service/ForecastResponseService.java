package com.weather.fetcher.blueprint.service;

import com.weather.fetcher.api.model.Forecast;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public interface ForecastResponseService {
    Forecast getForecast(String country, String city) throws ParseException, IOException, URISyntaxException, InterruptedException;
}
