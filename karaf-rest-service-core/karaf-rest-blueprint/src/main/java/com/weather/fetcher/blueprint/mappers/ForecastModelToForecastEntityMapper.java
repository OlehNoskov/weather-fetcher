package com.weather.fetcher.blueprint.mappers;

import com.weather.fetcher.api.entity.Forecast;
import com.weather.fetcher.api.model.ForecastModel;

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
