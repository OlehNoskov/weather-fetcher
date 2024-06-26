package com.service.core.service.impl;


import com.service.core.dto.response.ForecastResponse;
import com.service.core.entity.Forecast;
import com.service.core.entity.Temperature;
import com.service.core.entity.Weather;
import com.service.core.repository.ForecastRepository;
import com.service.core.repository.TemperatureRepository;
import com.service.core.repository.WeatherRepository;
import com.service.core.service.ForecastService;
import com.service.core.service.WeatherClient;
import com.service.core.util.enums.OverallWeather;
import com.service.core.util.enums.Scale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService {
    private final WeatherClient weatherClient;
    private final ForecastRepository forecastRepository;
    private final WeatherRepository weatherRepository;
    private final TemperatureRepository temperatureRepository;

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @Override
    public Forecast getForecast(String country, String city) throws ParseException {
        ForecastResponse forecastResponse = weatherClient.getForecast(country, city);
        Forecast forecast = Forecast.builder()
                .country(forecastResponse.getLocationResponse().getCountry())
                .city(forecastResponse.getLocationResponse().getCity())
                .date(getDate(forecastResponse))
                .weather(getWeather(forecastResponse))
                .build();

        return forecastRepository.save(forecast);
    }

    private Weather getWeather(ForecastResponse forecast) {
        Temperature temperature = Temperature
                .builder()
                .scale(Scale.CELSIUS)
                .degrees((int) Math.round(Double.parseDouble(forecast.getWeatherResponse().getTemperature())))
                .build();

        temperatureRepository.save(temperature);

        Weather weather = Weather
                .builder()
                .overall(OverallWeather.getFromString(forecast
                        .getWeatherResponse()
                        .getOverallWeatherResponse()
                        .getOverallWeather()))
                .temperature(temperature)
                .build();

        return weatherRepository.save(weather);
    }

    private Date getDate(ForecastResponse forecastResponse) throws ParseException {
        return DATE_FORMAT.parse(forecastResponse.getWeatherResponse().getUpdateDate());
    }
}
