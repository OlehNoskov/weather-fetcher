package com.service.core.service.impl;

import com.service.core.dto.request.ForecastRequest;
import com.service.core.dto.response.ForecastResponse;
import com.service.core.dto.response.TemperatureResponse;
import com.service.core.dto.response.WeatherResponse;
import com.service.core.entity.Forecast;
import com.service.core.repository.ForecastRepository;
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
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @Override
    public ForecastResponse getForecast(String country, String city) throws ParseException {
        ForecastRequest forecastRequest = weatherClient.getWeather(country, city);
        Forecast forecast = Forecast.builder()
                .country(forecastRequest.getLocationRequest().getCountry())
                .city(forecastRequest.getLocationRequest().getCity())
                .date(getDate(forecastRequest)).build();
        forecastRepository.save(forecast);

        return ForecastResponse
                .builder()
                .country(forecastRequest.getLocationRequest().getCountry())
                .city(forecastRequest.getLocationRequest().getCity())
                .date(getDate(forecastRequest))
                .weather(getWeather(forecastRequest))
                .build();
    }

    private WeatherResponse getWeather(ForecastRequest forecast) {
        TemperatureResponse temperature = TemperatureResponse
                .builder()
                .scale(Scale.CELSIUS)
                .degrees((int) Math.round(Double.parseDouble(forecast.getWeatherRequest().getTemperature())))
                .build();

        return WeatherResponse
                .builder()
                .overall(OverallWeather.getFromString(forecast
                        .getWeatherRequest()
                        .getOverallWeatherRequest()
                        .getOverallWeather()))
                .temperature(temperature)
                .build();
    }

    private Date getDate(ForecastRequest forecastRequest) throws ParseException {
        return DATE_FORMAT.parse(forecastRequest.getWeatherRequest().getUpdateDate());
    }
}
