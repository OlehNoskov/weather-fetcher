package com.weather.fetcher.api.mappers;

import com.weather.fetcher.api.model.ForecastModel;
import com.weather.fetcher.repo.model.Forecast;

public class ForecastModelToForecastEntityMapper {

    public static Forecast getForecastEntityFromForecastModel(ForecastModel forecastModel) {
        return Forecast
                .builder()
                .country(forecastModel.getCity())
                .city(forecastModel.getCity())
                .date(forecastModel.getDate())
                .overall(forecastModel.getOverall())
                .scale(forecastModel.getScale())
                .degrees(forecastModel.getDegrees())
                .build();
    }
}
