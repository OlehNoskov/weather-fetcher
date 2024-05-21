package com.weather.fetcher.blueprint.service.impl;

import com.weather.fetcher.blueprint.mappers.ForecastModelToForecastEntityMapper;
import com.weather.fetcher.blueprint.mappers.TemperatureModelToTemperatureEntityMapper;
import com.weather.fetcher.blueprint.mappers.WeatherModelToWeatherEntityMapper;
import com.weather.fetcher.api.enums.OverallWeather;
import com.weather.fetcher.api.enums.Scale;
import com.weather.fetcher.api.model.ForecastModel;
import com.weather.fetcher.api.model.TemperatureModel;
import com.weather.fetcher.api.model.WeatherModel;
import com.weather.fetcher.blueprint.response.ForecastResponse;
import com.weather.fetcher.blueprint.service.ForecastResponseService;
import com.weather.fetcher.blueprint.service.WeatherClientService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class ForecastResponseServiceImpl implements ForecastResponseService {
//    private final Repository repository;

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public ForecastModel getForecast(String country, String city) throws ParseException, IOException, URISyntaxException, InterruptedException {
        ForecastResponse forecastResponse = WeatherClientService.getForecast(country, city);
        ForecastModel forecastModel = ForecastModel.builder()
                .country(forecastResponse.getLocationResponse().getCountry())
                .city(forecastResponse.getLocationResponse().getCity())
                .date(getDate(forecastResponse))
                .weatherModel(getWeather(forecastResponse))
                .build();

//        repository.saveForecast(ForecastModelToForecastEntityMapper.getForecastEntityFromForecastModel(forecastModel));

        return forecastModel;
    }

    private WeatherModel getWeather(ForecastResponse forecast) {
        TemperatureModel temperatureModel = TemperatureModel
                .builder()
                .scale(Scale.CELSIUS)
                .degrees((int) Math.round(Double.parseDouble(forecast.getWeatherResponse().getTemperature())))
                .build();

//        repository.saveTemperature(TemperatureModelToTemperatureEntityMapper.getTemperatureEntityFromTemperatureModel(temperatureModel));

        WeatherModel weatherModel = WeatherModel
                .builder()
                .overall(OverallWeather.getFromString(forecast
                        .getWeatherResponse()
                        .getOverallWeatherResponse()
                        .getOverallWeather()))
                .temperatureModel(temperatureModel)
                .build();

//        repository.saveWeather(WeatherModelToWeatherEntityMapper.getWeatherEntityFromWeatherModel(weatherModel));

        return weatherModel;
    }

    private Date getDate(ForecastResponse forecastResponse) throws ParseException {
        return DATE_FORMAT.parse(forecastResponse.getWeatherResponse().getUpdateDate());
    }
}
