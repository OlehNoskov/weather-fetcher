package com.service.core.service.impl;

import com.service.core.dto.response.ForecastDto;
import com.service.core.entity.Forecast;
import com.service.core.entity.Temperature;
import com.service.core.entity.Weather;
import com.service.core.service.ForecastService;
import com.service.core.service.WeatherClient;
import com.service.core.util.enums.OverallWeather;
import com.service.core.util.enums.Scale;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService {
    private final WeatherClient weatherClient;
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @Override
    @SneakyThrows
    public Forecast getForecast(String country, String city) {
        ForecastDto forecastDto = weatherClient.getWeather(country, city);

        return Forecast
                .builder()
                .country(forecastDto.getLocationDto().getCountry())
                .city(forecastDto.getLocationDto().getCity())
                .date(DATE_FORMAT.parse(forecastDto.getWeatherDto().getUpdateDate()))
                .weather(getWeather(forecastDto))
                .build();
    }

    private Weather getWeather(ForecastDto forecastDto) {
        Temperature temperature = Temperature
                .builder()
                .scale(Scale.CELSIUS)
                .degrees((int) Math.round(Double.parseDouble(forecastDto.getWeatherDto().getTemperature())))
                .build();

        return Weather
                .builder()
                .overall(OverallWeather.getFromString(forecastDto
                        .getWeatherDto()
                        .getOverallWeatherDto()
                        .getOverallWeather()))
                .temperature(temperature)
                .build();
    }
}
