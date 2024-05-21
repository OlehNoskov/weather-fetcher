package com.weather.fetcher.api.service;

import com.weather.fetcher.api.model.ForecastModel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public interface ForecastService {
    ForecastModel getForecast(String country, String city) throws ParseException, IOException, URISyntaxException, InterruptedException;
}
