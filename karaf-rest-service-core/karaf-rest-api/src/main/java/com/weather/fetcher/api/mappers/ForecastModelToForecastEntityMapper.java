package com.weather.fetcher.api.mappers;


import com.weather.fetcher.api.model.ForecastModel;
import com.weather.fetcher.repository.entity.Forecast;

public class ForecastModelToForecastEntityMapper {

    public static Forecast getForecastEntityFromForecastModel(ForecastModel forecastModel) {
        return Forecast
                .builder()
                .country(forecastModel.getCity())
                .city(forecastModel.getCity())
                .date(forecastModel.getDate())
                .weather(WeatherModelToWeatherEntityMapper.getWeatherEntityFromWeatherModel(forecastModel.getWeatherModel()))
                .build();
    }
}
